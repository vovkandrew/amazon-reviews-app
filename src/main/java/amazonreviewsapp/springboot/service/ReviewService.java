package amazonreviewsapp.springboot.service;

import amazonreviewsapp.springboot.model.Review;
import amazonreviewsapp.springboot.repository.ReviewRepository;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public void save(Review review) {
        reviewRepository.save(review);
    }

    public List<Object> findMostCommentedProducts() {
        return reviewRepository.findMostCommentedProducts();
    };

    public Object[] findMostUsedWordsFromReviews() {
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
        String[] split =
                string.toLowerCase()
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

    private Object[] sortHashMap(HashMap<String, Integer> hashMap) {
        Object[] sorted = hashMap.entrySet().toArray();
        Arrays.sort(sorted, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Map.Entry<String, Integer>) o2).getValue()
                        .compareTo(((Map.Entry<String, Integer>) o1).getValue());
            }
        });
        return sorted;
    }


}
