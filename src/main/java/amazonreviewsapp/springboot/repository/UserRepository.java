package amazonreviewsapp.springboot.repository;

import amazonreviewsapp.springboot.model.Role;
import amazonreviewsapp.springboot.model.User;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "select u.profileName, count (r) as num_of_rev "
            + "from User u left join u.userReviews r "
            + "group by u.profileName order by num_of_rev desc")
    List<Object[]> findMostActiveUsers();

    @Query(value = "select u from User u left join fetch u.userRoles r where u.profileName = ?1")
    Optional<User> findUserByProfileName(String profileName);

    @Query(value = "select u.userId from User u left join u.userReviews r where r.reviewId = ?1")
    String getUserIdByReviewId(Long reviewId);
}
