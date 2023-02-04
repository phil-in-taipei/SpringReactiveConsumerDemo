package SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Src {
    String original;
    String large2x;
    String large;
    String medium;
    String small;

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

    String portrait;
    String landscape;
    @JsonProperty("tiny")
    String thumb;
}
