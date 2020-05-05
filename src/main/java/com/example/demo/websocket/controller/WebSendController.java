package com.example.demo.websocket.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import com.example.demo.websocket.server.WebSocketServer1;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(value="/websocket")
public class WebSendController {

    @RequestMapping(value="/sendToOne", produces = {"application/json; charset=utf-8"},method=RequestMethod.POST)
    public void sendToOne(HttpServletRequest request,String message){
        String str = message;
        try {
            WebSocketServer1.sendInfo(str);
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

