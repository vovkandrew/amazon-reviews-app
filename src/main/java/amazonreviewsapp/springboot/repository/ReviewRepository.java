package amazonreviewsapp.springboot.repository;

import amazonreviewsapp.springboot.dto.MostCommentedReviewDto;
import amazonreviewsapp.springboot.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = "select r.productId, count (r.productId) as num_of_rev from Review r "
            + "group by r.productId order by num_of_rev desc ")
    List<Object[]> findMostCommentedProducts();
}

