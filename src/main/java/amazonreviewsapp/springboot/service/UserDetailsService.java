package amazonreviewsapp.springboot.service;

import amazonreviewsapp.springboot.model.User;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String profileName) throws UsernameNotFoundException {
        Optional<User> user = userService.findUserByProfileName(profileName);
        UserBuilder builder = null;
        if (user.isPresent()) {
            builder = org.springframework.security.core.userdetails.User.withUsername(user.get().getProfileName());
            builder.password(user.get().getProfilePassword());
            builder.roles(user.get().getUserRoles().stream()
                    .map(r -> r.getRoleName().name())
                    .collect(Collectors.joining()));
        } else {
            throw new UsernameNotFoundException(
                    "Your username is invalid, please check and provide the correct profile name");
        }
        return builder.build();
    }
}
