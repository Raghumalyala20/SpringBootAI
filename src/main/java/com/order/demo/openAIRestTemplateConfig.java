package com.order.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class openAIRestTemplateConfig {

    @Value("${openai.api.key}")
    private String openAiApiKey ;   
    
    @Bean
    @Qualifier("openAiRestTemplate")
    public RestTemplate openAiRestTemplate() { 
        RestTemplate restTemplate = new RestTemplate();
        // Add any necessary interceptors or message converters here
        // For example, you might want to add an interceptor to include the API key in headers
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + openAiApiKey);
            return execution.execute(request, body);
        });
        return restTemplate;
    }
}
