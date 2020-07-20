package amazonreviewsapp.springboot.service;

import amazonreviewsapp.springboot.dto.MostCommentedReviewDto;
import amazonreviewsapp.springboot.dto.MostUsedWordResponseDto;
import amazonreviewsapp.springboot.mapper.ReviewMapper;
import amazonreviewsapp.springboot.model.Review;
import amazonreviewsapp.springboot.model.User;
import amazonreviewsapp.springboot.repository.ReviewRepository;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewMapper mapper;

    public void save(Review review) {
        reviewRepository.save(review);
    }

    public List<Object[]> findMostCommentedProducts() {
        return reviewRepository.findMostCommentedProducts();
    };

    public List<MostUsedWordResponseDto> findMostUsedWordsFromReviews() {
        List<String> texts =
                reviewRepository.findAll().stream()
                        .map(Review::getText)
                        .collect(Collectors.toList());
        HashMap<String, Integer> rating = new HashMap<>();
        for (String text: texts) {
            iterateOverReview(text, rating);
        }
        return sortHashMap(rating);
    }

    private void iterateOverReview(String string, HashMap<String, Integer> hashMap) {
        String[] split = string.toLowerCase()
                        .replaceAll("[^a-zA-Z0-9\\s]", "")
                        .split(" ");
        for (String s: split) {
            if (hashMap.containsKey(s)) {
                int number = hashMap.get(s);
                number = number + 1;
                hashMap.remove(s);
                hashMap.put(s, number);
            } else {
                hashMap.put(s, 1);
            }
        }
    }

    private List<MostUsedWordResponseDto> sortHashMap(HashMap<String, Integer> hashMap) {
        return (List<MostUsedWordResponseDto>) hashMap.entrySet().stream()
                .map(e -> mapper.getMostUsedWordDtoFromObjectArr(e))
                .sorted((Comparator) (o1, o2) -> ((MostUsedWordResponseDto) o2).getOccurrences()
                        .compareTo(((MostUsedWordResponseDto) o1).getOccurrences()))
                .collect(Collectors.toList());
        /*return (List<MostUsedWordResponseDto>) sorted.stream()
                .sorted((Comparator) (o1, o2) -> ((MostUsedWordResponseDto) o2).getOccurrences()
                        .compareTo(((MostUsedWordResponseDto) o1).getOccurrences()))
                .collect(Collectors.toList());*/
    }

    public void deleteReviewById(String id) {
        reviewRepository.deleteById(Long.parseLong(id));
    }

    public Optional<Review> findReviewById(String id) {
        return reviewRepository.findById(Long.parseLong(id));
    }
}
