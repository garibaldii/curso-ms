package com.gft.book_service.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/book")
public class FooBarController {

    private final Logger logger = LoggerFactory.getLogger(FooBarController.class);

    @GetMapping("/foo-bar")
//    @Retry(name = "default") //pega as configuracoes padroes já configuradas do retry
//    @Retry(name = "foo-bar") //pega as configuracoes customizadas declaradas no .yaml
//    @Retry(name = "foo-bar", fallbackMethod = "fallbackMethod") //define um método de retorno para a exception
    @CircuitBreaker(name = "default ", fallbackMethod = "fallbackMethod")
    public String fooBar() {
        logger.info("Request to foo-bar received");
        var response = new RestTemplate()
                .getForEntity("http://localhost:8080/foo-bar", String.class);

        return response.getBody();
    }

    public String fallbackMethod(Exception ex){
        return "fallbackMethod foo-bar";
    }
}
