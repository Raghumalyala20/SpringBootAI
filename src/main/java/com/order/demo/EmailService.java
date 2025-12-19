package com.order.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class EmailService {
    @Value("${openai.api.url}")
    private String apiURL;
    @Value("${openai.api.model}")
    private String model;
    @Qualifier("openAiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;
    public String generateEmail(String situation, String name, String orderId ) {
        String prompt = "Generate a professional email for the following situation: " + situation +
                        ". The email should address the customer by name: " + name +
                        " and include the order ID: " + orderId + "."
                        + " Keep the email concise and to the point.";
        EmailDTO emailDTO = new EmailDTO(model, prompt);
       
        // Call OpenAI API to generate email content
        String emailContent = restTemplate.postForObject(
            apiURL,
            emailDTO,
            String.class
        );
        return emailContent;
    }

   }