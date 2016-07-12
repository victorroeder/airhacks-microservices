package com.airhacks;

import com.airhacks.javaorchestration.Archiver;
import com.airhacks.javaorchestration.NumberProvider;
import com.airhacks.javaorchestration.Transformer;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author airhacks.com
 */
public class OrchestrationTest {

    private ExecutorService executor;

    @Before
    public void init() {
        this.executor = Executors.newFixedThreadPool(5);
    }

    @Test
    public void orchestrate() {
        supplyAsync(NumberProvider::random, executor).
                thenApply(Transformer::transform).
                thenAccept(Archiver::save);
    }

}
