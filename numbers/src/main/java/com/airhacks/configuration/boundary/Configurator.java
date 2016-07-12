package com.airhacks.configuration.boundary;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 *
 * @author airhacks.com
 */
public class Configurator {

    @Produces
    public Integer expose(InjectionPoint ip) {
        Class<?> clazz = ip.getMember().getDeclaringClass();
        String name = ip.getMember().getName();
        String key = clazz.getName() + "." + name;
        System.out.println("key = " + key);
        String value = System.getenv().getOrDefault(key, "3");
        System.out.println("-- value: " + value);
        return Integer.parseInt(value);
    }

}
