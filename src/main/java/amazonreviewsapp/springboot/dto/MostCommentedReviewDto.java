package amazonreviewsapp.springboot.dto;

public class MostCommentedReviewDto {
    private String productId;
    private Long numOfReviews;

    public MostCommentedReviewDto(String productId, Long numOfReviews) {
        this.productId = productId;
        this.numOfReviews = numOfReviews;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Long getNumOfReviews() {
        return numOfReviews;
    }

    public void setNumOfReviews(Long numOfReviews) {
        this.numOfReviews = numOfReviews;
    }
}
