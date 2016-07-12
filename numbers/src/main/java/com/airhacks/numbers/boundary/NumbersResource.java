package com.airhacks.numbers.boundary;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import java.util.concurrent.TimeUnit;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

/**
 *
 * @author airhacks.com
 */
@Stateless
@Path("numbers")
public class NumbersResource {

    @Inject
    NumberGenerator generator;

    @GET
    public void all(@Suspended AsyncResponse response) {
        response.setTimeout(1, TimeUnit.SECONDS);
        supplyAsync(generator::numbers).
                thenAccept(response::resume);
    }

}
