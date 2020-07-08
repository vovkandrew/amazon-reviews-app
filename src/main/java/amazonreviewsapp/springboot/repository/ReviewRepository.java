package amazonreviewsapp.springboot.repository;

import amazonreviewsapp.springboot.model.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
}
