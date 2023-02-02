package SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Photo {
    private String id;
    private int width;
    private int height;
    private String color;
    private String description;
    private Urls urls;
}
