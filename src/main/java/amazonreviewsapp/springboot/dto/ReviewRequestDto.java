package amazonreviewsapp.springboot.dto;

public class ReviewRequestDto {
    private String productId;
    private String reviewSummary;
    private String text;

    public ReviewRequestDto() {
    }

    public ReviewRequestDto(String productId, String reviewSummary, String text) {
        this.productId = productId;
        this.reviewSummary = reviewSummary;
        this.text = text;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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
