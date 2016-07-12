package com.airhacks;

import com.airhacks.javaorchestration.Archiver;
import com.airhacks.javaorchestration.NumberProvider;
import com.airhacks.javaorchestration.Transformer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
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
        //   this.executor = Executors.newFixedThreadPool(5);
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(1);
        this.executor
                = new ThreadPoolExecutor(1, 1, 1, TimeUnit.DAYS,
                        queue,
                        Executors.defaultThreadFactory(),
                        this::onOverload);
    }

    @Test
    public void orchestrate() {
        for (int i = 0; i < 10; i++) {

            supplyAsync(NumberProvider::random, executor).
                    thenApply(Transformer::transform).
                    thenAccept(Archiver::save);
        }
    }

    void onOverload(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("overloaded = " + executor);
    }

}
