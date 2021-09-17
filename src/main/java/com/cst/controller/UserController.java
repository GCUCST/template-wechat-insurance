package com.cst.controller;


import com.cst.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/user")
public class UserController {


    @GetMapping("/test")
    public Mono<String> test() {
        return Mono.just("hello");
    }


    public static Map<String, User> map = new HashMap<>();
    static  {
        map.put("1",new User());
        map.put("2",new User());
        map.put("3",new User());
        map.put("4",new User());
    }

    public Flux<User> list(){
        Collection<User>  list =  map.values();
        return Flux.fromIterable(list);
    }

    @GetMapping("/find")
    public Mono<User> getById(String id){
        return Mono.justOrEmpty(map.get(id));
    }
    @GetMapping("/delay")
    public Mono<User> delay(String id){
        return Mono.justOrEmpty(map.get(id)).delayElement(Duration.ofSeconds(3));
    }

    public Mono<User> del(String id){
        return Mono.justOrEmpty(map.remove(id));
    }


    @RequestMapping(value = "/ssePage")
    public String ssePage(String id){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "sse";
    }
    @RequestMapping(value = "/sse",produces = "text/event-stream")
    @ResponseBody
    public String sse(String id){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "data:"+Math.random();
    }



}
