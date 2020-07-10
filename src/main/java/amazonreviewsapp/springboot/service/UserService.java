package amazonreviewsapp.springboot.service;

import amazonreviewsapp.springboot.model.User;
import amazonreviewsapp.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean existsById(String id) {
        return userRepository.existsById(id);
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    public void delete(User user){
        userRepository.delete(user);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public List<Object> findMostActiveUsers() {
        return userRepository.findMostActiveUsers();
    }
}
