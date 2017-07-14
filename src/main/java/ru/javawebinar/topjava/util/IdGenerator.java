package ru.javawebinar.topjava.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by max on 7/13/17.
 */
public class IdGenerator {

    private static AtomicInteger id = new AtomicInteger(1);

    public static Integer getId() {
        return id.getAndIncrement();
    }
}
