package amazonreviewsapp.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    private Long reviewId;
    private String productId;
    private Long helpNum;
    private Long helpDenum;
    private Long score;
    private String time;
    private String reviewSummary;
    @Column(length = 30000) //max length 65000+ but for the whole raw colums
    private String text;

    public Review() {
    }

    public Review(Long reviewId, String productId, Long helpNum, Long helpDenum,
                  Long score, String time, String reviewSummary, String text) {
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
