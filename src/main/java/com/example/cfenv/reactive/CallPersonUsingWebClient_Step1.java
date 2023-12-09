package com.example.cfenv.reactive;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class CallPersonUsingWebClient_Step1 {
    private static final Logger logger = LoggerFactory.getLogger(CallPersonUsingWebClient_Step1.class);
    private static String baseUrl = "http://localhost:8080";
    private static WebClient client = WebClient.create(baseUrl);

    @GetMapping("/retrieveCallPersonUsingWebClient_Step1")
    public String retrieveCallPersonUsingWebClient_Step1() {
        logger.info("------------CallPersonUsingWebClient_Step1 start ------------------");

        Instant start = Instant.now();
        Mono<Person> monoPerson;
        List<Mono<Person>> personMonoList = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            monoPerson = client.get().uri("/person/{id}", i)
                    .retrieve()
                    .bodyToMono(Person.class);
            personMonoList.add(monoPerson);
            monoPerson.subscribe(
                    value -> logger.info(Thread.currentThread().getName() + " " + value.toString()),
                    error -> error.printStackTrace(),
                    () -> logger.info(
                            Thread.currentThread().getName() + " " + "end of flow and completed without errors."));

        }

        logTime(start);

        Mono.when(personMonoList).block();
        logger.info("------------CallPersonUsingWebClient_Step1 end ------------------");
        return "CallPersonUsingWebClient_Step1 OK";
    }

    private static void logTime(Instant start) {
        logger.info("Elapsed time: " + Duration.between(start, Instant.now()).toMillis() + "ms");
    }
}
