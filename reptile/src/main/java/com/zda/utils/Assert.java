package com.zda.utils;

public class Assert {

    /**
     * 如果对象为null则抛出异常
     * @param object
     * @param message
     */
    public static void checkNullPint(Object object, String message) {
        if (object==null) {
            throw new NullPointerException(message);
        }
    }

    public static void checkIllegalArgument(boolean expectExpression, String message) {
        if (!expectExpression) {
            throw new IllegalArgumentException(message);
        }
    }
}
