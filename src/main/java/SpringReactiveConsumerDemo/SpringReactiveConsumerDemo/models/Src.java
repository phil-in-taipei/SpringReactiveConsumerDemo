package SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Src {

    @JsonProperty("original")
    private String original;

    @JsonProperty("large2x")
    private String large2x;

    @JsonProperty("large")
    private String large;

    @JsonProperty("medium")
    private String medium;

    @JsonProperty("small")
    private String small;

    @Override
    public String toString() {
        return "Src{" +
                "original='" + original + '\'' +
                ", large2x='" + large2x + '\'' +
                ", large='" + large + '\'' +
                ", medium='" + medium + '\'' +
                ", small='" + small + '\'' +
                ", portrait='" + portrait + '\'' +
                ", landscape='" + landscape + '\'' +
                ", thumb='" + thumb + '\'' +
                '}';
    }

    @JsonProperty("portrait")
    private String portrait;

    @JsonProperty("landscape")
    private String landscape;
    @JsonProperty("tiny")
    private String thumb;
}
