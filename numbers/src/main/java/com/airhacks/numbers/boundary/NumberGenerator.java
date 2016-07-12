package com.airhacks.numbers.boundary;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author airhacks.com
 */
@Stateless
public class NumberGenerator {

    public String numbers() {
        System.out.println("-- numbers called " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(NumberGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "--- " + System.currentTimeMillis();
    }

}
