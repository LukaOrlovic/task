package com.plenigo.task.services.webclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plenigo.task.config.AppConfig;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class GenericWebClient {


    private final AppConfig appConfig;
    private final WebClient webClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private int startingAfterId;

    public GenericWebClient(AppConfig appConfig, WebClient.Builder webClientBuilder) {
        this.appConfig = appConfig;
        this.webClient =  webClientBuilder
                .baseUrl(appConfig.getUrl())
                .build();
    }

    public <T> Mono<T> get(Class<T> tClass, String uri, int pagingSize) {
        return webClient.get()
                .uri(buildUri(uri, pagingSize, null))
                .header(appConfig.getApiKeyKey(), appConfig.getApiKeyValue())
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(response -> {
                    T instance = null;
                    try {
                        instance = objectMapper.readValue(response, objectMapper.getTypeFactory().constructType(tClass));
                    } catch (JsonProcessingException e) {
                        return Mono.error(e);
                    }
                    return Mono.just(instance);
                });
    }

    public <T> Mono<List<T>> getList(Class<T> tClass, String uri, int pagingSize, Map<String, String> queryParams) {

        return webClient.get()
                .uri(buildUri(uri, pagingSize, queryParams))
                .header(appConfig.getApiKeyKey(), appConfig.getApiKeyValue())
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError(), // Use lambda to check for 4xx errors
                        clientResponse -> {
                            if (clientResponse.statusCode() == HttpStatus.NOT_FOUND) {
                                return Mono.empty();
                            }
                            return clientResponse.createException();
                        }
                )
                .bodyToMono(String.class)
                .flatMap(response -> {
                    try {
                        Map<String, Object> map = objectMapper.readValue(response, new TypeReference<>() {
                        });
                        if (map.containsKey("startingAfterId")) {
                            this.startingAfterId = (int) map.get("startingAfterId");
                        }
                        Object items = map.get("items");

                        if(items == null){
                            return Mono.empty();
                        }

                        if (items instanceof List) {
                            List<T> list = objectMapper.convertValue(items, objectMapper.getTypeFactory().constructCollectionType(List.class, tClass));
                            return Mono.just(list);
                        } else {
                            // Handle unexpected formats
                            System.out.println(response);
                            return Mono.error(new IllegalStateException("Unexpected JSON format: 'items' key not found or not a list"));
                        }
                    } catch (JsonProcessingException e) {
                        return Mono.error(e);
                    }
                });

    }


    private String buildUri(String uri, int pagingSize, Map<String, String> queryParams) {
        UriBuilder uriBuilder = UriComponentsBuilder.newInstance();

        uriBuilder.path(uri);
        uriBuilder.queryParam("size", pagingSize);
        if(queryParams != null){
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                uriBuilder.queryParam(key, value);
            }
        }

        return uriBuilder.toUriString();
    }

}
