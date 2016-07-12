package com.airhacks.numbers.boundary;

import javax.ejb.Stateless;

/**
 *
 * @author airhacks.com
 */
@Stateless
public class NumberGenerator {

    public String numbers() {
        System.out.println("-- numbers called " + Thread.currentThread().getName());
        //return "--- " + System.currentTimeMillis();
        throw new IllegalStateException("lunch break");
    }

}
