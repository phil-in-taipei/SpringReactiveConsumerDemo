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
public class Urls {
    String raw;
    String full;
    String regular;
    String small;
    String thumb;
    @JsonProperty("small_s3")
    private String smallS3;
}
