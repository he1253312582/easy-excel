package com.heq.util;

/**
 * String utils
 *
 * @author jipengfei
 */
public class StringUtils {
    public static final String EMPTY = "";

    private StringUtils() {}

    public static boolean isEmpty(Object str) {
        return (str == null || EMPTY.equals(str));
    }
}
