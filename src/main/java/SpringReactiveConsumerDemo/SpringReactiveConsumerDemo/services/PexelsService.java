package SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PexelsService {

    @Autowired
    @Qualifier("webClient2")
    WebClient webClient2;

    @Value("${api.client-id2}")
    private String secret;

    @Value("${search.uri2}")
    private String url;
}
