package com.airhacks;

import com.airhacks.javaorchestration.Archiver;
import com.airhacks.javaorchestration.NumberProvider;
import com.airhacks.javaorchestration.Transformer;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import org.junit.Test;

/**
 *
 * @author airhacks.com
 */
public class OrchestrationTest {

    @Test
    public void orchestrate() {
        supplyAsync(NumberProvider::random).
                thenApply(Transformer::transform).
                thenAccept(Archiver::save);
    }

}
