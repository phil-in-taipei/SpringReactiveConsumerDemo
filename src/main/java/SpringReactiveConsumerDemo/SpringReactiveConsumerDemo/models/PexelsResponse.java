package SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PexelsResponse {

    private int page;
    @JsonProperty("per_page")
    private int perPage;
    @JsonProperty("total_results")
    private int totalResults;
    // per_page=20 -- will have to divide to get the amount of pages
    private List<PexelPhoto> photos;


}
