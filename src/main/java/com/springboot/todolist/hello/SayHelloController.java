package com.springboot.todolist.hello;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SayHelloController {

    @RequestMapping("say-hello")
    @ResponseBody
    public String hello(){
        return "fuck you bloody :D";
    }

    @RequestMapping("say-hello-html")
    @ResponseBody
    public String helloHtml(){
        StringBuffer sb= new StringBuffer();
        sb.append("<div>Something</div>");
        return sb.toString();
    }
    @RequestMapping("say-hello-jsp")
    public String sayHelloJsp(){
        return "sayHello";
    }
}
