package SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PexelPhoto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("width")
    private Long width;
    @JsonProperty("height")
    private Long height;
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

    @JsonProperty("alt")
    private String description;
}
