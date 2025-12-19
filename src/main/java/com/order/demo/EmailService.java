package com.order.demo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;



@Service
public class EmailService {
    @Value("${openai.api.url}")
    private String apiURL;
    @Value("${openai.api.model}")
    private String model;
     @Value("${openai.api.key}")
    private String openAiApiKey ;
    // @Qualifier("openAiRestTemplate")
    // @Autowired
    private final RestTemplate restTemplate = new RestTemplate();
    public String generateEmail(String situation, String name, String orderId ) {
        Map<String , Object> requestBody = new HashMap<>();
        requestBody.put("model", "llama-3.3-70b-versatile");
        List<Map<String , String>> messages = new ArrayList<>();
        Map<String , String> systemMessage = new HashMap<>();
        systemMessage.put("role","system");
        systemMessage.put("content","You are a professional customer support agent.");
        messages.add(systemMessage);
        Map<String , String> userMessage= new HashMap<>();
        userMessage.put("role","user");
        userMessage.put("content", String.format(
                "Write a professional customer support email for this situation:\n" +
                "- Situation: %s\n" +
                "- Customer Name: %s\n" +
                "- Order ID: %s\n\n" +
                "Keep it under 150 words, empathetic and professional tone.",
                situation, name, orderId
            ));
            messages.add(userMessage);
             requestBody.put("messages", messages);
            requestBody.put("temperature", 0.7);
            requestBody.put("max_tokens", 500);
            
            HttpHeaders httpheaders = new HttpHeaders();
            httpheaders.setContentType(MediaType.APPLICATION_JSON);
            httpheaders.setBearerAuth(openAiApiKey);
            HttpEntity<Map<String , Object>> entity = new HttpEntity<Map<String,Object>>(requestBody, httpheaders);
            ResponseEntity<Map> response = restTemplate.postForEntity(apiURL, entity, Map.class);
            Map<String , Object> body = response.getBody();
              List<Map<String, Object>> choices = (List<Map<String, Object>>) body.get("choices");
            Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
            
            return (String) message.get("content");
// ;        String prompt = "Generate a professional email for the following situation: " + situation +
//                         ". The email should address the customer by name: " + name +
//                         " and include the order ID: " + orderId + "."
//                         + " Keep the email concise and to the point.";
//         EmailDTO emailDTO = new EmailDTO(model, prompt);
       
//         // Call OpenAI API to generate email content
//         String emailContent = restTemplate.postForObject(
//             apiURL,
//             emailDTO,
//             String.class
//         );
//         return emailContent;
    }

   }