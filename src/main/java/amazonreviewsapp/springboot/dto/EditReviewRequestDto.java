package amazonreviewsapp.springboot.dto;

import javax.persistence.Column;

public class EditReviewRequestDto {
    private Long reviewId;
    private String reviewSummary;
    private String text;

    public EditReviewRequestDto(Long reviewId, String reviewSummary, String text) {
        this.reviewId = reviewId;
        this.reviewSummary = reviewSummary;
        this.text = text;
    }

    public EditReviewRequestDto() {
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewSummary() {
        return reviewSummary;
    }

    public void setReviewSummary(String reviewSummary) {
        this.reviewSummary = reviewSummary;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
