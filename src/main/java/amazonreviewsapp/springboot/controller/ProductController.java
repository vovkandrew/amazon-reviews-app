package amazonreviewsapp.springboot.controller;

import amazonreviewsapp.springboot.service.ReviewService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/most-commented")
    public List<Object> mostCommentedProducts(@RequestParam String limit) {
        return reviewService.findMostCommentedProducts().stream()
                .limit(Long.parseLong(limit))
                .collect(Collectors.toList());
    }
}