package be.vutg.sandbox.jenkinssandbox.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Value("${vutg.url}")
    private String vutgUrl;

    @RequestMapping(value = "/")
    public String home() {
        return "Hello World, from jenkins-sandbox. Automation rules!!! Trigger Jenkins" + vutgUrl;
    }
}
