package com.example.demo.websocketServer.STOMPWebSocket.web;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by hui.yunfei@qq.com on 2019/5/31
 */
@Controller

public class TopicController {

   @RequestMapping("asd")
    public String subscribe() {
        return "topic";

    }






}




