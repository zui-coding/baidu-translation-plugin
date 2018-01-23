package com.zuicoding.framework.translation.utils;

/**
 * Created by <a href="mailto:stephen.linicoding@gmail.com">Stephen.lin</a> on 2018/1/22.
 * <p>
 * <p>
 * </p>
 */
public class StringTools {

    private StringTools(){}

    public static boolean isBank(String text){

        return text == null || text.trim().isEmpty();
    }

    public static boolean isNotBank(String text){

        return !isBank(text);
    }
}
