package com.airhacks.numbers.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author airhacks.com
 */
@Path("numbers")
public class NumbersResource {

    @GET
    public String number() {
        return "The ultimate answer: 42-" + System.currentTimeMillis();
    }

}
