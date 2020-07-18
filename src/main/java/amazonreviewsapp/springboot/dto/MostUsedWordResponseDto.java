package amazonreviewsapp.springboot.dto;

public class MostUsedWordResponseDto {
    private String word;
    private Integer occurrences;

    public MostUsedWordResponseDto(String word, Integer occurrences) {
        this.word = word;
        this.occurrences = occurrences;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(Integer occurrences) {
        this.occurrences = occurrences;
    }
}
