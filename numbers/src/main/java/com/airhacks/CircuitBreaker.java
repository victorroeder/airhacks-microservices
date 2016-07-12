package com.airhacks;

import java.util.concurrent.atomic.AtomicInteger;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author airhacks.com
 */
public class CircuitBreaker {

    private AtomicInteger errorCounter = new AtomicInteger();

    @Inject
    Event<String> notifications;

    @Inject
    int maxError;

    @AroundInvoke
    public Object watch(InvocationContext ic) throws Exception {
        System.out.println("-- " + ic.getMethod());
        if (errorCounter.get() > this.maxError) {
            notifications.fire("Circuit opened with: " + ic.getMethod());
            System.out.println("--- opening the circuit");
            return null;
        }
        try {
            return ic.proceed();
        } catch (Exception ex) {
            this.errorCounter.incrementAndGet();
            throw ex;
        }
    }

}
