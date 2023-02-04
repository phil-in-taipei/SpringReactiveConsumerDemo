package SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.services;

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
public class UnsplashService {
    @Autowired
    WebClient webClient;

    @Value("${api.client-id.param}")
    private String secret;

    @Value("${search.uri}")
    private String url;

    public Flux<Photo> getPhotos(String searchText, String orientation) {
        return getTotalPages(searchText, orientation)
                // try something here to check if the getTotal value is zero, and if so abort
                .flatMapMany(t -> Flux.range(1, t > 2 ? 2 : t))
                .flatMap(f -> searchUnsplash(searchText, f, orientation)
                        .flatMapIterable(UnsplashResponse::getResults), 5);
    }

    public Mono<Integer> getTotalPages(String searchText, String orientation) {
        return webClient.get()
                .uri(uri -> uri
                        .queryParam("page", "1")
                        .queryParam("query", searchText)
                        .queryParam("orientation", orientation)
                        //.queryParam("client_id", secret)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(UnsplashResponse.class)
                .map(UnsplashResponse::getTotalPages)
                .map(Integer::valueOf);
    }

    public Mono<Integer> getTotal(String searchText) {
        return webClient.get()
                .uri(uri -> uri
                        .queryParam("page", "1")
                        .queryParam("query", searchText)
                        .queryParam("orientation", "squarish")
                        //.queryParam("client_id", secret)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(UnsplashResponse.class)
                .map(UnsplashResponse::getTotal)
                .map(Integer::valueOf);
    }


    public Mono<UnsplashResponse> searchUnsplash(String searchText, int pageNumber, String orientation) {
        return webClient.get()
                .uri(uri -> uri
                        .queryParam("page", pageNumber)
                        .queryParam("query", searchText)
                        //.queryParam("orientation", "squarish")
                        .queryParam("orientation", orientation)
                        //.queryParam("client_id", secret)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(UnsplashResponse.class);
    }
}
