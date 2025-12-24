package com.order.demo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/emails")
public class EmailController {
    @Qualifier("emailService")
    @Autowired
    private EmailService emailService;
    @PostMapping("/generateEmail") 
    public Map<String, String> generateEmail(@RequestBody EmailRequest request) {
        String emailContent = emailService.generateEmail(
            request.getSituation(),
            request.getName(),
            request.getCustId()
        );
        return Map.of("emailContent", emailContent);
       }
    
}
