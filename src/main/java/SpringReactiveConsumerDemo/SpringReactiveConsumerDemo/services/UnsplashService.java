package SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.services;

import SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.models.Photo;
import SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.models.UnsplashResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UnsplashService {
    @Autowired
    WebClient webClient;

   // @Autowired
    //RestTemplate restTemplate;

    @Value("${api.client-id.param}")
    private String secret;

    @Value("${search.uri}")
    private String url;

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
                        .queryParam("orientation", "squarish")
                        //.queryParam("client_id", secret)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(UnsplashResponse.class)
                .map(UnsplashResponse::getTotalPages)
                .map(Integer::valueOf);
    }
    /*
    public void getCount(String searchText) {
        return webClient.get()
                .uri(uri -> uri
                        .queryParam("page", "1")
                        .queryParam("query", searchText)
                        .queryParam("orientation", "squarish")
                        //.queryParam("client_id", secret)
                        .build());
                .accept(MediaType.APPLICATION_JSON)
                .retrieve.bodyToMono(UnsplashResponse.class).block();
    }

     */

    public void getTotal(String searchText) {
        System.out.println("****************This is the url for the search:" + "**********");
        System.out.println("url");
        System.out.println(url);
        System.out.println("id:");
        System.out.println(secret);
        System.out.println(searchText);
        System.out.println(url + "?query=" + searchText + "&client_id=" + secret);
        UnsplashResponse response = webClient.get()
                .uri(uri -> uri
                        //.queryParam("page", pageNumber)
                        .queryParam("query", searchText)
                        .queryParam("orientation", "squarish")
                        //.queryParam("client_id", secret)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(UnsplashResponse.class).block();
        //System.out.println(response);
        //String urlString = url + "?query=" + searchText + "&client_id=" + secret;
        //UnsplashResponse response = restTemplate.getForObject(urlString, UnsplashResponse.class);
    }


    public Mono<UnsplashResponse> searchUnsplash(String searchText, int pageNumber) {
        return webClient.get()
                .uri(uri -> uri
                        .queryParam("page", pageNumber)
                        .queryParam("query", searchText)
                        .queryParam("orientation", "squarish")
                        //.queryParam("client_id", secret)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(UnsplashResponse.class);
    }
}
