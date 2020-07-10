package amazonreviewsapp.springboot.service;

import amazonreviewsapp.springboot.model.Review;
import amazonreviewsapp.springboot.model.Role;
import amazonreviewsapp.springboot.model.User;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CSVFileParserService {
    private static final int TIMESTAMP_MULTIPLIER = 1000;
    private static final int REVIEW_ID = 0;
    private static final int PRODUCT_ID = 1;
    private static final int USER_ID = 2;
    private static final int PROFILE_NAME = 3;
    private static final int HELP_NUM = 4;
    private static final int HELP_DENUM = 5;
    private static final int SCORE = 6;
    private static final int TIME = 7;
    private static final int SUMMARY = 8;
    private static final int TEXT = 9;
    private static final String USER_ROLE = "USER";
    private static final int COUNTER = 1000;

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private RoleService roleService;

    public void parseCsvFile(String filePath) {
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(filePath));
            reader.readNext();
            String[] line;
            int count = COUNTER;
            while ((line = reader.readNext()) != null && (count > 0)) {
                if (userService.existsById(line[USER_ID])) {
                    User userToUpdate = userService.findById(line[USER_ID]).get();
                    userService.delete(userToUpdate);
                    Set<Review> userReviews = userToUpdate.getUserReviews();
                    Review userReview = getReviewFromReader(line);
                    reviewService.save(userReview);
                    System.out.println(userReview.getReviewId() + " has been added to the db");
                    userReviews.add(userReview);
                    userToUpdate.setUserReviews(userReviews);
                    userService.save(userToUpdate);
                    System.out.println(userToUpdate.getUserId() + " has been updated in the db");
                    count--;
                } else {
                    User newUser = getUserFromReader(line);
                    Review userReview = getReviewFromReader(line);
                    reviewService.save(userReview);
                    System.out.println(userReview.getReviewId() + " has been added to the db");
                    Set<Review> userReviews = Set.of(userReview);
                    newUser.setUserReviews(userReviews);
                    userService.save(newUser);
                    System.out.println(newUser.getUserId() + " has been added to the db");
                    count--;
                }
            }
        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }
    }

    private User getUserFromReader(String[] line) {
        User user = new User();
        user.setUserId(line[USER_ID]);
        user.setProfileName(line[PROFILE_NAME]);
        user.setProfilePassword(line[PROFILE_NAME]);
        user.setUserRoles(Set.of(roleService.findByRoleName(Role.RoleName.valueOf(USER_ROLE))));
        return user;
    }

    private Review getReviewFromReader(String[] line) {
        Review review = new Review();
        review.setReviewId(Long.parseLong(line[REVIEW_ID]));
        review.setProductId(line[PRODUCT_ID]);
        review.setHelpNum(Long.parseLong(line[HELP_NUM]));
        review.setHelpDenum(Long.parseLong(line[HELP_DENUM]));
        review.setScore(Long.parseLong(line[SCORE]));
        review.setTime(getTimeFromTimestamp(line[TIME]));
        review.setReviewSummary(line[SUMMARY]);
        review.setText(line[TEXT]);
        return review;
    }

    private String getTimeFromTimestamp(String time) {
        Timestamp timestamp = new Timestamp(Long.parseLong(time)*TIMESTAMP_MULTIPLIER);
        Date date = new Date(timestamp.getTime());
        return date.toString();
    }
}
