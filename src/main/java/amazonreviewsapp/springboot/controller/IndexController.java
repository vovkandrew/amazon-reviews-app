package amazonreviewsapp.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping
    @RequestMapping("/")
    public String index(){
        return "Hello, this is my Amazon Reviews App with Spring Boot";
    }
}
