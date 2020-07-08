package amazonreviewsapp.springboot.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String userId;
    private String profileName;
    private String profilePassword;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Role> userRoles;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Review> userReviews;

    public User() {
    }

    public User(String userId, String profileName, String profilePassword, Set<Role> userRoles, Set<Review> userReviews) {
        this.userId = userId;
        this.profileName = profileName;
        this.profilePassword = profilePassword;
        this.userRoles = userRoles;
        this.userReviews = userReviews;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfilePassword() {
        return profilePassword;
    }

    public void setProfilePassword(String profilePassword) {
        this.profilePassword = profilePassword;
    }

    public Set<Role> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<Role> userRoles) {
        this.userRoles = userRoles;
    }

    public Set<Review> getUserReviews() {
        return userReviews;
    }

    public void setUserReviews(Set<Review> userReviews) {
        this.userReviews = userReviews;
    }
}
