package amazonreviewsapp.springboot.parser;

import amazonreviewsapp.springboot.model.Review;
import amazonreviewsapp.springboot.model.Role;
import amazonreviewsapp.springboot.model.User;
import amazonreviewsapp.springboot.repository.ReviewRepository;
import amazonreviewsapp.springboot.repository.UserRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Component
public class CSVFileParser {
    private static final int TIMESTAMP_MULTIPLIER = 1000;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public void parseCsvFile(String filePath) {
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(filePath));
            reader.readNext();
            String[] line;
            while ((line = reader.readNext()) != null) {
                if (userRepository.existsById(line[2])) {
                    User userToUpdate = userRepository.findById(line[2]).get();
                    userRepository.delete(userToUpdate);
                    Set<Review> userReviews = userToUpdate.getUserReviews();
                    Review userReview = getReviewFromReader(line);
                    reviewRepository.save(userReview);
                    System.out.println(userReview.getReviewId() + " has been added to the db");
                    userReviews.add(userReview);
                    userToUpdate.setUserReviews(userReviews);
                    userRepository.save(userToUpdate);
                    System.out.println(userToUpdate.getUserId() + " has been updated in the db");
                } else {
                    User newUser = getUserFromReader(line);
                    Review userReview = getReviewFromReader(line);
                    reviewRepository.save(userReview);
                    System.out.println(userReview.getReviewId() + " has been added to the db");
                    Set<Review> userReviews = Set.of(userReview);
                    newUser.setUserReviews(userReviews);
                    userRepository.save(newUser);
                    System.out.println(newUser.getUserId() + " has been added to the db");
                }
            }
        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }
    }

    private User getUserFromReader(String[] line) {
        User user = new User();
        user.setUserId(line[2]);
        user.setProfileName(line[3]);
        user.setProfilePassword(line[3]);
        user.setUserRoles(Set.of(Role.roleOf("USER")));
        return user;
    }

    private Review getReviewFromReader(String[] line) {
        Review review = new Review();
        review.setReviewId(Long.parseLong(line[0]));
        review.setProductId(line[1]);
        review.setHelpNum(Long.parseLong(line[4]));
        review.setHelpDenum(Long.parseLong(line[5]));
        review.setScore(Long.parseLong(line[6]));
        review.setTime(getTimeFromTimestamp(line[7]));
        review.setReviewSummary(line[8]);
        review.setText(line[9]);
        return review;
    }

    private String getTimeFromTimestamp(String time) {
        Timestamp timestamp = new Timestamp(Long.parseLong(time)*TIMESTAMP_MULTIPLIER);
        Date date = new Date(timestamp.getTime());
        return date.toString();
    }
}
