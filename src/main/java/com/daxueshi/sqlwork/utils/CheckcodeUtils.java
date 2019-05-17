package com.daxueshi.sqlwork.utils;

import java.util.Random;

/**
 * @author onion
 * @date 2019-05-07 -15:57
 */
public class CheckcodeUtils {
    private static final String source = "0123456789";
    public static String getCheckcode(){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 6; i++){
            sb.append(source.charAt(random.nextInt(10))+"");
        }
        return sb.toString();
    }
}
