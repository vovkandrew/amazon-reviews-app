package amazonreviewsapp.springboot;

public class Main {
    public static void main(String[] args) {
        String mysting = "this is my review";
        String mysummary = "this is my summary";
        System.out.println(Math.abs(mysting.hashCode()));
        System.out.println(mysummary.hashCode());
    }
}
