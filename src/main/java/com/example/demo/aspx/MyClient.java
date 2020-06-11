package com.example.demo.aspx;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;



//客户端
public class MyClient  {
    public static void main(String[] args){
        EventLoopGroup group=new NioEventLoopGroup();
        Bootstrap boot=new Bootstrap();
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        boolean zhen=true;
        try{
            boot.option(ChannelOption.SO_KEEPALIVE,true)
                    .group(group)
                    .channel(NioSocketChannel.class)

                    //引用自己的协议
                    .handler(new ChannelInitializer());

             //ws协议类型 
            //URI websocketURI = new URI("ws://47.244.56.4:9688");
            //URI websocketURI = new URI("ws://172.16.16.50:9688");
            URI websocketURI = new URI("ws://wstest.asptest.top:80");
          	//URI websocketURI = new URI("wss://ws.asproex-club.com:80");
            HttpHeaders httpHeaders = new DefaultHttpHeaders();
            //进行握手
            WebSocketClientHandshaker handshaker = WebSocketClientHandshakerFactory.newHandshaker(websocketURI, WebSocketVersion.V13, (String)null, true,httpHeaders);
            //需要协议的host和port
            Channel channel=boot.connect(websocketURI.getHost(),websocketURI.getPort()).sync().channel();
            WebSocketClientHandler handler = (WebSocketClientHandler)channel.pipeline().get("hookedHandler");
            handler.setHandshaker(handshaker);
            handshaker.handshake(channel);
            //阻塞等待是否握手成功
            handler.handshakeFuture().sync();
            System.out.println("成功!");
            //让线程睡眠1秒,以免数据收回慢
            Thread.sleep(1000);
            try{
//                TextWebSocketFrame frameSub = new TextWebSocketFrame("{\"event\":\"sub\",\"topic\":\"market.prices\",\"symbol\":\"700036,700012\"}") ;
//                
//                channel.writeAndFlush(frameSub);
//
//                TextWebSocketFrame frameSub2 = new TextWebSocketFrame("{\"event\":\"sub\",\"topic\":\"market.ranking\",\"symbol\":\"700036,700012\"}") ;
//                channel.writeAndFlush(frameSub2);

//                TextWebSocketFrame frameSub3 = new TextWebSocketFrame("{\"event\":\"sub\",\"topic\":\"market.realtime\",\"symbol\":\"700036\"}") ;
//                channel.writeAndFlush(frameSub3);
//
                TextWebSocketFrame frameSub4 = new TextWebSocketFrame("{\"event\":\"sub\",\"topic\":\"market.depth\",\"symbol\":\"700065\"}") ;
                System.out.println(JSON.toJSONString(frameSub4));
                channel.writeAndFlush(frameSub4);
                
//                TextWebSocketFrame frameUnSub4 = new TextWebSocketFrame("{\"event\":\"unsub\",\"topic\":\"market.depth\",\"symbol\":\"700036\"}") ;
//                System.out.println(JSON.toJSONString(frameUnSub4));
//                channel.writeAndFlush(frameUnSub4);
                
//                TextWebSocketFrame frameSub5 = new TextWebSocketFrame("{\"event\":\"sub\",\"topic\":\"market.kline\",\"period\":\"60\",\"symbol\":\"700036\"}") ;
//                System.out.println(JSON.toJSONString(frameSub5));
//                channel.writeAndFlush(frameSub5);
//
//                TextWebSocketFrame frameSub9 = new TextWebSocketFrame("{\"event\":\"sub\",\"topic\":\"market.newPrice\",\"symbol\":\"700036\"}") ;
//                System.out.println(JSON.toJSONString(frameSub9));
//                channel.writeAndFlush(frameSub9);
//                
//              TextWebSocketFrame frameSub10 = new TextWebSocketFrame("{\"event\":\"sub\",\"topic\":\"market.klinehead\",\"symbol\":\"700036,700012\"}") ;
//              channel.writeAndFlush(frameSub10);
                
                Thread thread = new Thread(new DelayExecution(channel));
                thread.start();
                while (zhen){
                    System.out.print("请输入操作:");
                    String zhi=br.readLine();
                    //发送textwebsocketframe格式的请求
                    //TextWebSocketFrame 可以任意转换
                    TextWebSocketFrame frame = new TextWebSocketFrame(zhi+"\r\n");
                    channel.writeAndFlush(frame);
                }
            }catch(Exception e){
                br.close();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            zhen=false;
             try{
                br.close();
            }catch(Exception e1){
                System.out.println(e1.getMessage());
            }
        }finally {
        	//优雅关闭
            group.shutdownGracefully();
        }
    }

}