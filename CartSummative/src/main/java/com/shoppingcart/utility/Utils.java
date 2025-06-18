package com.shoppingcart.utility;

import java.text.NumberFormat;
import java.util.Locale;

public class Utils {

    // Formats a price value to US dollar
    public static String format(double value) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(value);
    }
}
