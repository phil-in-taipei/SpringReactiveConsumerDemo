package SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;


import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

@Configuration
public class ClientConfig {


    @Value("${search.uri}")
    private URI searchUri;

    @Value("${search.uri2}")
    private URI searchUri2;

    @Value("${api.client-id}")
    private String secret;

    @Value("${api.client-id2}")
    private String secret2;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(searchUri.toString())
                .defaultHeader(HttpHeaders.AUTHORIZATION, secret)
                .build();
    }

    @Bean
    public WebClient webClient2() {
        return WebClient.builder()
                .baseUrl(searchUri2.toString())
                .defaultHeader(HttpHeaders.AUTHORIZATION, secret2)
                .build();
    }
}
