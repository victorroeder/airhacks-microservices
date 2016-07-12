package com.airhacks.numbers.boundary;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;
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
        Consumer<Object> consumer = response::resume;
        Supplier<String> supplier = generator::numbers;
        CompletableFuture.
                supplyAsync(supplier).
                thenAccept(consumer);
    }

}
