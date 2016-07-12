package com.airhacks.numbers.boundary;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author airhacks.com
 */
@Stateless
public class NumberGenerator {

    private Client client;
    private WebTarget tut;

    @PostConstruct
    public void init() {
        this.client = ClientBuilder.newClient();
        this.tut = this.client.target("http://localhost:8080/numberprovider/resources/numbers");
    }

    public String numbers() {
        return this.tut.request().get(String.class);
    }

}
