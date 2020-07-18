package amazonreviewsapp.springboot.controller;

import amazonreviewsapp.springboot.dto.AuthRequestDto;
import amazonreviewsapp.springboot.model.Role;
import amazonreviewsapp.springboot.model.User;
import amazonreviewsapp.springboot.service.RoleService;
import amazonreviewsapp.springboot.service.UserService;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

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
        User newUser = new User();
        newUser.setProfileName("superuser");
        newUser.setProfilePassword("password");
        newUser.setUserId("superuserid");
        newUser.setUserRoles(Set.of(user, admin));
        newUser.setUserReviews(Set.of());
        userService.save(newUser);
    }

    @PostMapping
    public ResponseEntity authenticate(@RequestBody AuthRequestDto authRequestDto) {
        User user = userService.findUserByProfileName(authRequestDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("No user with the name "
                + authRequestDto.getUsername()
                + " was not found in the database"));
        String token = "token";
        Map<String, String> response = new HashMap<>();
        response.put("username", authRequestDto.getUsername());
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}
