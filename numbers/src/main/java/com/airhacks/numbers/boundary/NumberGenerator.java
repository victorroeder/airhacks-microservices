package com.airhacks.numbers.boundary;

import javax.ejb.Stateless;

/**
 *
 * @author airhacks.com
 */
@Stateless
public class NumberGenerator {

    public String numbers() {
        return "--- " + System.currentTimeMillis();
    }

}
