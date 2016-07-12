package com.airhacks.numbers.boundary;

import com.airhacks.CircuitBreaker;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.interceptor.Interceptors;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author airhacks.com
 */
@Singleton
@Interceptors(CircuitBreaker.class)
public class NumberGenerator {

    private Client client;
    private WebTarget tut;

    @PostConstruct
    public void init() {
        this.client = ClientBuilder.newClient();
    }

    public String numbers() {
        WebTarget target = this.client.target("http://provider:8080/numberprovider/resources/numbers");
        return target.request().get(String.class);
    }

}
