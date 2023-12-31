package com.example.cfenv.reactive;

import java.time.Duration;
import java.time.Instant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class GreetReactiveController {

    @GetMapping("/greetings")
    public Flux<Greeting> greetingPublisher() {
        Flux<Greeting> greetingFlux = Flux.<Greeting>generate(sink -> sink.next(new Greeting("Hello")))
                .take(50);
        return greetingFlux;
    }

    @GetMapping(value = "/greetings/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Greeting> sseGreetings() {
        Flux<Greeting> delayElements = Flux
                .<Greeting>generate(sink -> sink.next(new Greeting("Hello @" + Instant.now().toString())))
                .take(10)
                .delayElements(Duration.ofSeconds(1));
        return delayElements;
    }

}