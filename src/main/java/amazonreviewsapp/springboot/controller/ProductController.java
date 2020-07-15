package amazonreviewsapp.springboot.controller;

import amazonreviewsapp.springboot.dto.MostCommentedReviewDto;
import amazonreviewsapp.springboot.mapper.ReviewMapper;
import amazonreviewsapp.springboot.model.Review;
import amazonreviewsapp.springboot.service.ReviewService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewMapper mapper;

    @GetMapping("/most-commented")
    public List<MostCommentedReviewDto> mostCommentedProducts(@RequestParam String limit) {
        return reviewService.findMostCommentedProducts().stream()
                .map(el -> mapper.getMostCommentedReviewDtoFromObjectArr(el))
                .limit(Long.parseLong(limit))
                .collect(Collectors.toList());
    }
}
