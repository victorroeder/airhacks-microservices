package com.airhacks.numbers.boundary;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;

/**
 *
 * @author airhacks.com
 */
@Stateless
@Path("numbers")
public class NumbersResource {

    @Inject
    NumberGenerator generator;

    @Resource
    ManagedExecutorService mes;

    @GET
    public void all(@Suspended AsyncResponse response) {
        response.setTimeout(1, TimeUnit.SECONDS);
        response.setTimeoutHandler(this::handleTimeout);
        supplyAsync(generator::numbers, mes).
                thenAccept(response::resume).
                exceptionally((Throwable t) -> {
                    response.resume("-- error" + t.toString());
                    return null;
                });
    }

    void handleTimeout(AsyncResponse response) {
        System.out.println("-- response: " + response);
        Response error = Response.status(Response.Status.SERVICE_UNAVAILABLE).
                header("reason", "lazy ejb").build();
        response.resume(error);
    }
}
