package amazonreviewsapp.springboot.controller;

import amazonreviewsapp.springboot.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/most-active-users")
public class MostActiveUsersController {
    @Autowired
    private UserService userService;

    @GetMapping
    @RequestMapping("/{limit}")
    public List<Object> mostActiveUsers(@PathVariable String limit) {
        return userService.findMostActiveUsers().stream()
                .limit(Long.parseLong(limit))
                .collect(Collectors.toList());
    }
}
