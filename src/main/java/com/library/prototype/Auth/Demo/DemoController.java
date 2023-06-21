package com.library.prototype.Auth.Demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public String demoMethod(){
        return "This is secure endpoint";
    }

}
