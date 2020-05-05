//package com.example.demo.websocket.client;
//
//import java.net.URI;
//import java.util.Date;
//import java.util.Timer;
//import java.util.TimerTask;
//
//import org.java_websocket.WebSocket.READYSTATE;
//import org.java_websocket.client.WebSocketClient;
//import org.java_websocket.drafts.Draft_6455;
//
//
//public class WebSocketClientTest {
//    public static WebSocketClient client;
//
//    public static int heartbeat = 0;// 0代表链接断开或者异常 1代表链接中.2代表正在连接
//    public static String url="";//请求的路径地址包括端口
//    public static void main(String[] args) throws Exception {
//        Timer timer = new Timer();
//        Task task = new Task();
//        timer.schedule(task, new Date(), 5000);
//    }
//
//    public static void connect() throws Exception {
//        client = new Client(new URI(url), new Draft_6455(), null, 0);
//        client.connect();
//        int count = 0;
//        heartbeat=2;
//        while (!client.getReadyState().equals(READYSTATE.OPEN)) {
//            count++;
//            if (count % 1000000000 == 0) {
//                System.out.println("还没有打开");
//            }
//        }
//        client.send("发送给服务器端的信息");
//    }
//
//    public static void reconnect() throws Exception {
//        Thread.currentThread().sleep(15000);// 毫秒
//        System.out.println("再次启动尝试连接");
//        connect();
//    }
//
//    public static void send(byte[] bytes) {
//        client.send(bytes);
//    }
//}
//
//class Task extends TimerTask {
//
//    @Override
//    public void run() {
//        try {
//            System.out.println("心跳检测:"+((Client.heartbeat == 1)?"连接中":"未连接中"));
//            if (Client.heartbeat ==0 ) {
//                Client.connect();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//    }
//}