package amazonreviewsapp.springboot.controller;

import amazonreviewsapp.springboot.dto.AuthRequestDto;
import amazonreviewsapp.springboot.jwt.JwtTokenProvider;
import amazonreviewsapp.springboot.model.Role;
import amazonreviewsapp.springboot.model.User;
import amazonreviewsapp.springboot.service.RoleService;
import amazonreviewsapp.springboot.service.UserService;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider provider;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        newUser.setProfilePassword(passwordEncoder.encode("password"));
        newUser.setUserId("superuserid");
        newUser.setUserRoles(Set.of(user, admin));
        newUser.setUserReviews(Set.of());
        userService.save(newUser);
    }

    @PostMapping
    public ResponseEntity authenticate(@RequestBody AuthRequestDto authRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequestDto.getUsername(), authRequestDto.getPassword()));
        Optional<User> user = userService.findUserByProfileName(authRequestDto.getUsername());
        if(user.isEmpty()) {
            throw new UsernameNotFoundException("No user with current name");
        }
        String token = provider.createToken(authRequestDto.getUsername(), user.get().getUserRoles());
        Map<String, String> response = new HashMap<>();
        response.put("username", authRequestDto.getUsername());
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}
