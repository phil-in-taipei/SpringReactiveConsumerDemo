package SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PexelsResponse {

    @JsonProperty("total_results")
    private int totalResults;

    // per_page=20 -- will have to divide to get the amount of pages
}
