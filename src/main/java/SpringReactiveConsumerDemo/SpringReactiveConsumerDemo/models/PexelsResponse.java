package SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PexelsResponse {

    private int page;
    @JsonProperty("per_page")
    private int perPage;
    @JsonProperty("total_results")
    private int totalResults;

    @JsonProperty("photos")
    private List<PexelPhoto> photos;


}
