package com.daxueshi.sqlwork.utils;

import java.util.Random;

/**
 * @author onion
 * @date 2019-05-28 -22:55
 */
public class KeyUtils {
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
