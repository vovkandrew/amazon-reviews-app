package amazonreviewsapp.springboot.controller;

import amazonreviewsapp.springboot.service.ReviewService;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/most-used-words")
public class MostUsedWordsFromReviews {
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    @RequestMapping("/{limit}")
    public List<Object> findMostUsedWords(@PathVariable String limit) {
        Object[] words = reviewService.findMostUsedWordsFromReviews();
        return Arrays.stream(words)
                .limit(Long.parseLong(limit))
                .collect(Collectors.toList());
    }
}
