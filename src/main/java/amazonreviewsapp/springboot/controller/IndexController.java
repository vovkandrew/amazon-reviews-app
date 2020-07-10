package amazonreviewsapp.springboot.controller;

import amazonreviewsapp.springboot.model.Role;
import amazonreviewsapp.springboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class IndexController {
    @Autowired
    private RoleService roleService;

    @PostConstruct
    private void addRoles() {
        Role user = new Role();
        user.setRoleName(Role.RoleName.USER);
        roleService.save(user);
        Role admin = new Role();
        admin.setRoleName(Role.RoleName.ADMIN);
        roleService.save(admin);

    }

    @GetMapping
    @RequestMapping("/")
    public String index(){
        return "Hello, this is my Amazon Reviews App with Spring Boot";
    }
}
