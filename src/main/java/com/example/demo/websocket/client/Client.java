//package com.example.demo.websocket.client;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.nio.ByteBuffer;
//import java.nio.channels.NotYetConnectedException;
//import java.util.Map;
//
//import org.java_websocket.client.WebSocketClient;
//import org.java_websocket.drafts.Draft;
//import org.java_websocket.handshake.ServerHandshake;
//
//
//
//
//public class Client extends WebSocketClient {
//
//    public Client(URI serverUri, Draft protocolDraft, Map<String, String> httpHeaders, int connectTimeout) {
//        super(serverUri, protocolDraft, httpHeaders, connectTimeout);
//    }
//
//    @Override
//    public void onOpen(ServerHandshake arg0) {
//        System.out.println("打开链接");
//        WebSocketClientTest.heartbeat=1;
//    }
//
//    @Override
//    public void onMessage(String arg0) {
//        if(arg0!=null){
//            System.out.println("收到消息" + arg0);
//            //一般在这执行你需要的业务,通常返回JSON串然后解析做其他操作
//        }
//
//    }
//
//    @Override
//    public void onError(Exception arg0) {
//        System.out.println("发生错误已关闭,正在重连");
//        WebSocketClientTest.heartbeat=0;
//    }
//
//    @Override
//    public void onClose(int arg0, String arg1, boolean arg2) {
//        System.out.println("链接已关闭,正在重连");
//        WebSocketClientTest.heartbeat=0;
//    }
//
//    @Override
//    public void onMessage(ByteBuffer bytes) {
//        try {
//            System.out.println(new String(bytes.array(), "utf-8"));
//        } catch (UnsupportedEncodingException e) {
//            System.out.println("出现异常");
//            WebSocketClientTest.heartbeat=0;
//        }
//    }
//}