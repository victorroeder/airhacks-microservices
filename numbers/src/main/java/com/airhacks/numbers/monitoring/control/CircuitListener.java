package com.airhacks.numbers.monitoring.control;

import javax.enterprise.event.Observes;

/**
 *
 * @author airhacks.com
 */
public class CircuitListener {

    public void onProblem(@Observes String alarm) {
        System.out.println("-alarm received-- " + alarm);
    }

}
