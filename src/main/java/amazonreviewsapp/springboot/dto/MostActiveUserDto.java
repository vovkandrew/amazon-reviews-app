package amazonreviewsapp.springboot.dto;

public class MostActiveUserDto {
    private String profileName;
    private Long numbOfReviews;

    public MostActiveUserDto(String profileName, Long numbOfReviews) {
        this.profileName = profileName;
        this.numbOfReviews = numbOfReviews;
    }

    public MostActiveUserDto() {
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public Long getNumbOfReviews() {
        return numbOfReviews;
    }

    public void setNumbOfReviews(Long numbOfReviews) {
        this.numbOfReviews = numbOfReviews;
    }
}
