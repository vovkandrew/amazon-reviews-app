package amazonreviewsapp.springboot.repository;

import amazonreviewsapp.springboot.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "select u.profileName, count (r) as num_of_rev "
            + "from User u left join u.userReviews r "
            + "group by u.profileName order by num_of_rev desc")
    List<Object> findMostActiveUsers();
}
