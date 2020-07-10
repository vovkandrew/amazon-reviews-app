package amazonreviewsapp.springboot.controller;

import amazonreviewsapp.springboot.service.ReviewService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/most-commented-products")
public class MostCommentedProductsController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{limit}")
    public List<Object> mostCommentedProducts(@PathVariable String limit) {
        return reviewService.findMostCommentedProducts().stream()
                .limit(Long.parseLong(limit))
                .collect(Collectors.toList());
    }
}
