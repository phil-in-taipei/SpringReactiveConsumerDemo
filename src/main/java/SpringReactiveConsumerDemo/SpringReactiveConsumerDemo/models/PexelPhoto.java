package SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PexelPhoto {
    private int id;
    private int width;
    private int height;
    @JsonProperty("avg_color")
    private String color;
    private Src urls;

    private String description;
}
