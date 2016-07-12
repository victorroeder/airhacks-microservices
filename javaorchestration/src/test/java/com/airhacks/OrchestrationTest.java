package com.airhacks;

import com.airhacks.javaorchestration.Archiver;
import com.airhacks.javaorchestration.NumberProvider;
import com.airhacks.javaorchestration.Transformer;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author airhacks.com
 */
public class OrchestrationTest {

    private Supplier<Long> supplier;
    private Function<Long, Long> transformer;
    private Consumer<Long> archiver;

    @Before
    public void init() {
        this.supplier = new NumberProvider()::random;
        this.transformer = new Transformer()::transform;
        this.archiver = new Archiver()::save;
    }

    @Test
    public void orchestrate() {
        CompletableFuture.supplyAsync(this.supplier).
                thenApply(transformer).
                thenAccept(this.archiver);
    }

}
