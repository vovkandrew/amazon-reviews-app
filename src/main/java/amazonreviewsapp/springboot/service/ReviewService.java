package amazonreviewsapp.springboot.service;

import amazonreviewsapp.springboot.model.Review;
import amazonreviewsapp.springboot.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public void save(Review review) {
        reviewRepository.save(review);
    }


}
