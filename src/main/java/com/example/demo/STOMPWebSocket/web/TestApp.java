package com.example.demo.STOMPWebSocket.web;


import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class TestApp {

    public static void main(String[] args) {
        try {
            // 这里用的binance的socket接口，国内调用需要VPN，使用换成你的就行
//            String url = "wss://stream.binance.com:9443/ws/ethbtc@ticker";
//            String url = "wss://stream.binance.com:9443/ws/ethbtc@depth20";
            String url = "wss://real.OKEx.com:8443/ws/v3";
            URI uri = new URI(url);
            WebSocketClient mWs = new WebSocketClient(uri){
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    System.out.println(serverHandshake.getHttpStatusMessage());

                    System.out.println(1);
                }

                @Override
                public void onMessage(String s) {
                    System.out.println(2);

                    System.out.println(s);
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    System.out.println(3);
                    System.out.println(s);

                }

                @Override
                public void onError(Exception e) {
                    System.out.println(e);
                    System.out.println(4);


                }
            };
            mWs.connect();
            System.out.println("haha");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


