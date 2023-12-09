package com.example.cfenv.reactive;

import java.time.Duration;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class CallPersonUsingRestTemplate {
   private static final Logger logger = LoggerFactory.getLogger(CallPersonUsingRestTemplate.class);

    @GetMapping("/retrievecallpersonusingresttemplate")
    public String retrieveCallPersonUsingRestTemplate() {
        
        logger.info("------------CallPersonUsingRestTemplate start ------------------");
        String baseUrl = "http://localhost:8080";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(baseUrl));
        
        Instant start = Instant.now();

        for (int i = 1; i <= 10; i++) {
            logger.info(restTemplate.getForObject("/person/{id}",Person.class, i).toString());
        }

        logTime(start);

        logger.info("------------CallPersonUsingRestTemplate end ------------------");
        return "retrievecallpersonusingresttemplate OK"; 
    }

    private static void logTime(Instant start) {
        logger.info("Elapsed time: " + Duration.between(start, Instant.now()).toMillis() + "ms");
    } 
}
