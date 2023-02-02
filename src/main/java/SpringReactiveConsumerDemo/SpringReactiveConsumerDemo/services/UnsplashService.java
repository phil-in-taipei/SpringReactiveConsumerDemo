package SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.services;

import SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.models.Photo;
import SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.models.UnsplashResponse;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Value("${api.client-id}")
    private String secret;

    public Flux<Photo> getPhotos(String searchText) {
        return getTotalPages(searchText)
                .flatMapMany(t -> Flux.range(1, t > 2 ? 2 : t))
                .flatMap(f -> searchUnsplash(searchText, f)
                        .flatMapIterable(UnsplashResponse::getResults), 5);
    }

    public Mono<Integer> getTotalPages(String searchText) {
        return webClient.get()
                .uri(uri -> uri
                        .queryParam("page", "1")
                        .queryParam("query", searchText)
                        .queryParam("client_id", secret).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(UnsplashResponse.class)
                .map(UnsplashResponse::getTotalPages)
                .map(Integer::valueOf);
    }

    public Mono<UnsplashResponse> searchUnsplash(String searchText, int pageNumber) {
        return webClient.get()
                .uri(uri -> uri
                        .queryParam("page", pageNumber)
                        .queryParam("query", searchText)
                        .queryParam("client_id", secret)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(UnsplashResponse.class);
    }
}
