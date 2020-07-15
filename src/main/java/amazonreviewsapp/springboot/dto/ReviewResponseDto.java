package amazonreviewsapp.springboot.dto;

import javax.persistence.Column;

public class ReviewResponseDto {
    private Long reviewId;
    private String productId;
    private Long helpNum;
    private Long helpDenum;
    private Long score;
    private String time;
    private String reviewSummary;
    private String text;

    public ReviewResponseDto() {
    }

    public ReviewResponseDto(Long reviewId, String productId, Long helpNum, Long helpDenum, Long score,
                             String time, String reviewSummary, String text) {
        this.reviewId = reviewId;
        this.productId = productId;
        this.helpNum = helpNum;
        this.helpDenum = helpDenum;
        this.score = score;
        this.time = time;
        this.reviewSummary = reviewSummary;
        this.text = text;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Long getHelpNum() {
        return helpNum;
    }

    public void setHelpNum(Long helpNum) {
        this.helpNum = helpNum;
    }

    public Long getHelpDenum() {
        return helpDenum;
    }

    public void setHelpDenum(Long helpDenum) {
        this.helpDenum = helpDenum;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
