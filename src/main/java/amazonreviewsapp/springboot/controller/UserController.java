package amazonreviewsapp.springboot.controller;

import amazonreviewsapp.springboot.dto.MostActiveUserDto;
import amazonreviewsapp.springboot.dto.ReviewResponseDto;
//import amazonreviewsapp.springboot.jwt.JwtTokenProvider;
import amazonreviewsapp.springboot.mapper.ReviewMapper;
import amazonreviewsapp.springboot.mapper.UserMapper;
import amazonreviewsapp.springboot.model.User;
import amazonreviewsapp.springboot.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private ReviewMapper mapper;

    /*@Autowired
    private JwtTokenProvider provider;*/

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    @RequestMapping("/most-active")
    public List<MostActiveUserDto> mostActiveUsers(@RequestParam String limit) {
        return userService.findMostActiveUsers().stream()
                .map(el -> userMapper.getMostActiveUserDtoFromObject(el))
                .limit(Long.parseLong(limit))
                .collect(Collectors.toList());
    }

    @GetMapping
    @RequestMapping("/all-reviews")
    public List<ReviewResponseDto> getAllUserReviews(HttpServletRequest request) {
        User user = getUserFromRequest(request);
        return user.getUserReviews().stream()
                .map(review -> mapper.getReviewResponseDtoFromReview(review))
                .collect(Collectors.toList());
    }

    private User getUserFromRequest(HttpServletRequest request) {
        /*String token = provider.resolveToken(request);
        return userService.findUserByProfileName(provider.getUserNameByToken(token)).get();*/
        return new User();
    }
}
