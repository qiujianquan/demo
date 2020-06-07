//package com.example.demo.websocketServer.STOMPWebSocket.web;
//
//import com.alibaba.fastjson.JSONObject;
//import com.example.demo.websocketServer.STOMPWebSocket.utils.MyWebSocketClient;
//import org.springframework.web.util.UriBuilder;
//
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.net.URL;
//
//public class Test {
//    public static void main(String[] args) throws URISyntaxException {
//        URI uri=new URI("ws://wstest.asptest.top:80");
//
//        JSONObject jsonObject=new JSONObject();
//        jsonObject.put("topic","market.depth");
//        jsonObject.put("period","60");
//        jsonObject.put("symbol","700012");
//        jsonObject.put("id","xc3z21aw5z32x1233321x");
//        jsonObject.put("event","sub");
//        MyWebSocketClient myWebSocketClient=new MyWebSocketClient(uri);
//        myWebSocketClient.send(jsonObject.toJSONString());
//    }
//}
