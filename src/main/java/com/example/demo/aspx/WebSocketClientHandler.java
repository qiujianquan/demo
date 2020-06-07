package com.example.demo.aspx;
import io.netty.channel.*;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;


import java.lang.reflect.Method;
//这里的泛型我用的是object,也可以用TextWebSocketFrame
public class WebSocketClientHandler  extends SimpleChannelInboundHandler<Object> {
    WebSocketClientHandshaker handshaker;
    ChannelPromise handshakeFuture;
    public void handlerAdded(ChannelHandlerContext ctx) {
        this.handshakeFuture = ctx.newPromise();
    }
    public WebSocketClientHandshaker getHandshaker() {
        return handshaker;
    }

    public void setHandshaker(WebSocketClientHandshaker handshaker) {
        this.handshaker = handshaker;
    }

    public ChannelPromise getHandshakeFuture() {
        return handshakeFuture;
    }

    public void setHandshakeFuture(ChannelPromise handshakeFuture) {
        this.handshakeFuture = handshakeFuture;
    }

    public ChannelFuture handshakeFuture() {
        return this.handshakeFuture;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel ch = ctx.channel();
        FullHttpResponse response;
        //判断接收的请求是否是牵手
        if (!this.handshaker.isHandshakeComplete()) {
            try {
                response = (FullHttpResponse) msg;
                //握手协议返回，设置结束握手
                this.handshaker.finishHandshake(ch, response);
                //设置成功
                this.handshakeFuture.setSuccess();
                System.out.println("牵手成功!");

            } catch (WebSocketHandshakeException var7) {
                FullHttpResponse res = (FullHttpResponse) msg;
                String errorMsg = String.format("WebSocket客户端连接失败，状态为:%s", res.status());
                this.handshakeFuture.setFailure(new Exception(errorMsg));
            }
        } else if (msg instanceof FullHttpResponse) {
            response = (FullHttpResponse) msg;
            //可以吧字符码转为指定类型
            //this.listener.onFail(response.status().code(), response.content().toString(CharsetUtil.UTF_8));
            throw new IllegalStateException("未预料的错误(getStatus=" + response.status() + ", content=" + response.content().toString(CharsetUtil.UTF_8) + ')');
        } else {//如果不是牵手
            WebSocketFrame frame = (WebSocketFrame) msg;
            if (frame instanceof TextWebSocketFrame) {
                TextWebSocketFrame textFrame = (TextWebSocketFrame) frame;
                //这里我用了一个返回自己的格式的类型和一个json字符串转为对象的方法
                
                String message = ((TextWebSocketFrame) frame).text();


                System.out.println("收到消息:"+textFrame.text());
            } else if (frame instanceof BinaryWebSocketFrame) {
                BinaryWebSocketFrame binFrame = (BinaryWebSocketFrame) frame;
                System.out.println("二进制WebSocketFrame");
            } else if (frame instanceof PongWebSocketFrame) {
                //返回心跳监测
                //System.out.println("WebSocket客户端接收到pong");
            } else if (frame instanceof CloseWebSocketFrame) {
                System.out.println("接收关闭贞");
                //this.listener.onClose(((CloseWebSocketFrame)frame).statusCode(), ((CloseWebSocketFrame)frame).reasonText());
                ch.close();
            }

        }
    }
    /*发生异常直接关闭客户端*/
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("发生异常"+cause.getMessage());
        ctx.close();
    }
}