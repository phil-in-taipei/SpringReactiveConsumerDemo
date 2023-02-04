package SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PexelPhoto {
    private double id;
    private double width;
    private double height;
    @JsonProperty("avg_color")
    private String color;

    @JsonProperty("src")
    private Src urls;

    @Override
    public String toString() {
        return "PexelPhoto{" +
                "id=" + id +
                ", width=" + width +
                ", height=" + height +
                ", color='" + color + '\'' +
                ", urls=" + urls.toString() +
                ", description='" + description + '\'' +
                '}';
    }

    private String description;
}
