package amazonreviewsapp.springboot.mapper;

import amazonreviewsapp.springboot.dto.MostCommentedReviewDto;
import amazonreviewsapp.springboot.dto.ReviewRequestDto;
import amazonreviewsapp.springboot.dto.ReviewResponseDto;
import amazonreviewsapp.springboot.model.Review;
import amazonreviewsapp.springboot.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ReviewMapper {
    private static final Long DEFAULT_HELP_VALUE = 0L;
    private static final Long DEFAULT_SCORE = 0L;
    private static final int TIMESTAMP_MULTIPLIER = 1000;

    @Autowired
    private ReviewService reviewService;

    public Review getReviewFromReviewRequestDto(ReviewRequestDto reviewRequestDto) {
        Review review = new Review();
        review.setReviewId((long) Math.abs(reviewRequestDto.getText().hashCode()));
        review.setProductId(reviewRequestDto.getProductId());
        review.setHelpNum(DEFAULT_HELP_VALUE);
        review.setHelpDenum(DEFAULT_HELP_VALUE);
        review.setScore(DEFAULT_SCORE);
        review.setTime(String.valueOf(new Date(System.currentTimeMillis()).getTime()/TIMESTAMP_MULTIPLIER));
        review.setReviewSummary(reviewRequestDto.getReviewSummary());
        review.setText(reviewRequestDto.getText());
        return review;
    }

    public ReviewResponseDto getReviewResponseDtoFromReview(Review review) {
        ReviewResponseDto reviewResponseDto = new ReviewResponseDto();
        reviewResponseDto.setReviewId(review.getReviewId());
        reviewResponseDto.setProductId(review.getProductId());
        reviewResponseDto.setHelpNum(review.getHelpNum());
        reviewResponseDto.setHelpDenum(review.getHelpDenum());
        reviewResponseDto.setScore(review.getScore());
        reviewResponseDto.setTime(review.getTime());
        reviewResponseDto.setReviewSummary(review.getReviewSummary());
        reviewResponseDto.setText(review.getText());
        return reviewResponseDto;
    }

    public MostCommentedReviewDto getMostCommentedReviewDtoFromObjectArr(Object[] arr) {
        return new MostCommentedReviewDto((String) arr[0], (Long) arr[1]);
    }
}
