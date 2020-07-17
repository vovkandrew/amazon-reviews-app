package amazonreviewsapp.springboot.mapper;

import amazonreviewsapp.springboot.dto.EditReviewRequestDto;
import amazonreviewsapp.springboot.dto.MostCommentedReviewDto;
import amazonreviewsapp.springboot.dto.MostUsedWordResponseDto;
import amazonreviewsapp.springboot.dto.ReviewRequestDto;
import amazonreviewsapp.springboot.dto.ReviewResponseDto;
import amazonreviewsapp.springboot.model.Review;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    private static final Long DEFAULT_HELP_VALUE = 0L;
    private static final Long DEFAULT_SCORE = 0L;
    private static final int TIMESTAMP_MULTIPLIER = 1000;

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

    public MostUsedWordResponseDto getMostUsedWordDtoFromObjectArr(Map.Entry<String, Integer> entry) {
        return new MostUsedWordResponseDto((String) entry.getKey(), (Integer) entry.getValue());
    }

    public void editReviewFromEditReviewRequestDto(EditReviewRequestDto reviewRequestDto, Review review) {
        review.setReviewSummary(reviewRequestDto.getReviewSummary());
        review.setText(reviewRequestDto.getText());
    }
}
