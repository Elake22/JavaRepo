package com.shoppingcart.utility;

import java.text.NumberFormat;
import java.util.Locale;

public class Utils {

    // Formats a price value to US dollar
    public static String format(double value) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(value);
    }

    // Capitalizes the first letter of the item name for display
    public static String capitalize(String name) {
        if (name == null || name.isEmpty()) return name;
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

}
