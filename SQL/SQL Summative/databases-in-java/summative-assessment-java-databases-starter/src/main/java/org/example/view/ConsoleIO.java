package org.example.view;

import org.example.model.Order;
import org.example.model.OrderItem;
import org.example.model.Payment;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleIO implements IO {
    private final NumberFormat formatter = NumberFormat.getCurrencyInstance();
    private BigDecimal safeAmount(BigDecimal value) {
        return value != null ? value : BigDecimal.ZERO;
    }

    DateTimeFormatter dtformatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    private static final String ORDER_DISPLAY_DIVIDER = "===============================================";

    @Override
    public void prompt(String prompt) {
        System.out.print(prompt + ": ");
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void displayTitle(String title) {
        System.out.println(">>> " + title + " <<<");
    }

    @Override
    public String getString(String prompt) {
        Scanner scanner = new Scanner(System.in);
        String reply = null;
        while(reply == null || reply.trim().length() == 0) {
            prompt(prompt);
            reply = scanner.next();
        }

        return reply.trim();
    }

    @Override
    public int getInt(String prompt) {
        Scanner scanner = new Scanner(System.in);
        Integer result = null;

        while (result == null) {
            prompt(prompt);
            String input = scanner.nextLine();
            try {
                result = Integer.parseInt(input);
            } catch (Exception e) {
                result = null;
                displayMessage(e.getLocalizedMessage());
            }
        }

        return result;
    }

    @Override
    public int getIntRequiredRange(String prompt, int min, int max) {
        Integer result = null;

        while (result == null) {
            result = getInt(prompt);
            if (result < min || result > max) {
                result = null;
                displayMessage("Input must be between " + min + " and " + max);
            }
        }

        return result;
    }

    private double getDouble(String prompt) {
        Scanner scanner = new Scanner(System.in);
        Double result = null;

        while (result == null) {
            prompt(prompt);
            String input = scanner.nextLine();
            try {
                result = Double.valueOf(input);
            } catch (Exception e) {
                result = null;
                displayMessage(e.getLocalizedMessage());
            }
        }

        return result;
    }

    @Override
    public BigDecimal getMoney(String prompt) {
        BigDecimal result = null;

        while (result == null) {
            result = new BigDecimal(getDouble(prompt));
            if (result.compareTo(new BigDecimal(0)) < 0) {
                result = null;
                displayMessage("Input must be at least 0");
            }
        }

        return result;
    }

    @Override
    public String asCurrency(double money) {
        return formatter.format(money);
    }

    @Override
    public void displayOrders(List<Order> orders) {
        for(Order o : orders) {
            displayMessage(formatOrderSummary(o));
        }
    }

    public String formatOrderSummary(Order order) {
        return String.format("#%-5d    %-19s %-30s %7s", order.getOrderID(), order.getOrderDate().format(dtformatter), getServerName(order), formatter.format(order.getTotal()));
    }

    @Override
    public void displayOrder(Order order) {
        StringBuffer buff = new StringBuffer();

        if (order != null) {
            //Header
            buff.append(ORDER_DISPLAY_DIVIDER).append("\n");
            if (order.getOrderID() > 0 && order.getOrderDate() != null) {
                buff.append(String.format("Order #%-4d%36s\n", order.getOrderID(), order.getOrderDate().format(dtformatter)));
            } else {
                buff.append("*** NEW ORDER ***\n");
            }
            String fullName = getServerName(order);
            buff.append(String.format("Server: %39s\n", fullName));
            buff.append(ORDER_DISPLAY_DIVIDER).append("\n");

            //Order Items
            for (OrderItem oi : order.getItems()) {
                buff.append(formatOrderItem(oi)).append("\n");
            }

            //Footer
            buff.append(ORDER_DISPLAY_DIVIDER).append("\n");
            buff.append(String.format("%39s %7s\n", "Subtotal", formatter.format(safeAmount(order.getSubTotal()))));
            buff.append(String.format("%39s %7s\n", "Tax", formatter.format(safeAmount(order.getTax()))));
            buff.append(String.format("%39s %7s\n", "Tip", formatter.format(safeAmount(order.getTip()))));
            buff.append(String.format("%48s", "===============\n"));
            buff.append(String.format("%39S %7s\n", "Total", formatter.format(safeAmount(order.getTotal()))));

            buff.append(ORDER_DISPLAY_DIVIDER).append("\n");

            buff.append("PAYMENTS:\n");
            BigDecimal unpaid = new BigDecimal(order.getTotal().doubleValue());
            for (Payment p : order.getPayments()) {
                buff.append(String.format("%39S %7s\n",
                        p.getPaymentType() != null ? p.getPaymentType().getPaymentTypeName() : "[Unknown]",
                        formatter.format(safeAmount(p.getAmount()))));

                unpaid = safeAmount(unpaid); // prevent null

                if (unpaid.compareTo(BigDecimal.ZERO) > 0) {
                    buff.append(String.format("%39S %7s\n", "UNPAID:", formatter.format(unpaid)));
                } else if (unpaid.compareTo(BigDecimal.ZERO) == 0) {
                    buff.append(String.format("%39S\n", "PAID IN FULL"));
                } else {
                    BigDecimal refund = unpaid.abs();
                    buff.append(String.format("%39S %7s\n", "REFUND DUE:", formatter.format(refund)));
                }
            }

            } else {
            buff.append("Unable to display order.");
        }

        displayMessage(buff.toString());
    }

    @Override
    public void displayMenuChoice(int choiceId, String label) {
        displayMessage(String.format("%3d - %s", choiceId, label));
    }

    private static String getServerName(@NotNull Order order) {
        if (order.getServer() == null) {
            return "[No Server]";
        }
        return order.getServer().getFirstName() + " " + order.getServer().getLastName();
    }


    private String formatOrderItem(OrderItem item) {
        BigDecimal price = item.getPrice();
        int quantity = item.getQuantity();

        if (price == null) {
            System.err.println("⚠️  Price is null for item ID: " + item.getItemID());
            price = BigDecimal.ZERO;
        }

        BigDecimal total = price.multiply(BigDecimal.valueOf(quantity));

        System.out.println("DEBUG - Price: " + price + " | Class: " + price.getClass().getName());
        System.out.println("DEBUG - Total: " + total + " | Class: " + total.getClass().getName());

        String formattedPrice = "ERR";
        String formattedTotal = "ERR";

        try {
            formattedPrice = formatter.format(price);
        } catch (Exception ex) {
            System.err.println("❌ Failed to format price: " + price + " - " + ex.getMessage());
        }

        try {
            formattedTotal = formatter.format(total);
        } catch (Exception ex) {
            System.err.println("❌ Failed to format total: " + total + " - " + ex.getMessage());
        }

        return String.format(
                "%-25s %3d %8s %8s",
                item.getItem().getItemName(),
                quantity,
                formattedPrice,
                formattedTotal
        );
    }
    public static class OrderItemFormatter {
        private static final NumberFormat formatter = NumberFormat.getCurrencyInstance();

        public static String formatOrderItem(OrderItem item) {
            BigDecimal price = item.getPrice();
            int quantity = item.getQuantity();

            if (price == null) {
                System.err.println("⚠️  Price is null for item ID: " + item.getItemID());
                price = BigDecimal.ZERO;
            }

            BigDecimal total = price.multiply(BigDecimal.valueOf(quantity));

            System.out.println("DEBUG - Price: " + price + " | Class: " + price.getClass().getName());
            System.out.println("DEBUG - Total: " + total + " | Class: " + total.getClass().getName());

            String formattedPrice = "ERR";
            String formattedTotal = "ERR";

            try {
                formattedPrice = formatter.format(price);
            } catch (Exception ex) {
                System.err.println("❌ Failed to format price: " + price + " - " + ex.getMessage());
            }

            try {
                formattedTotal = formatter.format(total);
            } catch (Exception ex) {
                System.err.println("❌ Failed to format total: " + total + " - " + ex.getMessage());
            }

            return String.format(
                    "%-25s %3d %8s %8s",
                    item.getItem().getItemName(),
                    quantity,
                    formattedPrice,
                    formattedTotal
            );
        }
    }

}
