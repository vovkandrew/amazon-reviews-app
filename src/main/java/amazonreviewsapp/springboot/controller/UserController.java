package amazonreviewsapp.springboot.controller;

import amazonreviewsapp.springboot.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    @RequestMapping("/most-active")
    public List<Object> mostActiveUsers(@RequestParam String limit) {
        return userService.findMostActiveUsers().stream()
                .limit(Long.parseLong(limit))
                .collect(Collectors.toList());
    }
}
