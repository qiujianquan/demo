package com.example.demo.aspx;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

class DelayExecution implements Runnable{

    public DelayExecution(Channel channel) {
        this.channel = channel;
    }
    private Channel channel = null;
    @Override
    public void run() {

        while(true) {
            try {
                TextWebSocketFrame frame = new TextWebSocketFrame("{\"event\":\"ping\"}") ;
                channel.writeAndFlush(frame);
                Thread.sleep(10000);

            }catch (InterruptedException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }

   }
    
}