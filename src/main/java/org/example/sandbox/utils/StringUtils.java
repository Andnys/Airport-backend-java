package org.example.sandbox.utils;

import org.jetbrains.annotations.Contract;

public class StringUtils {

    /**
     * Checks if a string has value, and if it has a length
     * @param string The value to be checked
     * @return false if the string is null or empty.
     */
    @Contract("null -> true")
    public static boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

    /**
     * Checks if a string has value, and if it has a length and if it contains anything but whitespaces.
     * @param string The value to be checked
     * @return true if the string is null or empty or nothing but whitespaces.
     */
    @Contract("null -> true")
    public static boolean IsNullOrBlank(String string) {
        return isNullOrEmpty(string) || string.isBlank();
    }
}
