package SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.services;

import SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.models.PexelPhoto;
import SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.models.PexelsResponse;
import SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.models.Photo;
import SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.models.UnsplashResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PexelsService {

    @Autowired
    @Qualifier("webClient2")
    WebClient webClient2;

    @Value("${api.client-id2}")
    private String secret;

    @Value("${search.uri2}")
    private String url;

    public Flux<PexelPhoto> getPhotos(String searchText, String orientation) {
        return getTotalResults(searchText, orientation)
                // try something here to check if the getTotal value is zero, and if so abort
                // Note: t is divided by 15 because number of queries per page
                // Pexel doesn't have an absolute number of pages like Unsplash
                .flatMapMany(t -> Flux.range(1, t > 2 ? 2 : t/15))
                .flatMap(f -> searchPexel(searchText, f, orientation)
                        .flatMapIterable(PexelsResponse::getPhotos), 5);
    }

    public Mono<Integer> getTotalResults(String searchText, String orientation) {
        return webClient2.get()
                .uri(uri -> uri
                        .queryParam("page", "1")
                        .queryParam("per_page", 15)
                        .queryParam("query", searchText)
                        .queryParam("orientation", orientation)
                        //.queryParam("client_id", secret)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(PexelsResponse.class)
                .map(PexelsResponse::getTotalResults)
                .map(Integer::valueOf);
    }

    public Mono<PexelsResponse> searchPexel(String searchText, int pageNumber, String orientation) {
        return webClient2.get()
                .uri(uri -> uri
                        .queryParam("page", pageNumber)
                        .queryParam("query", searchText)
                        .queryParam("per_page", 15)
                        .queryParam("orientation", orientation)
                        //.queryParam("client_id", secret)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PexelsResponse.class);
    }
}
