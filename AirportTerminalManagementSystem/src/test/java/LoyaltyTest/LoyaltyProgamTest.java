package LoyaltyTest;

import Airport.domain.loyalty.LoyaltyProgram;
import Airport.domain.loyalty.RegularPassenger;
import Airport.domain.loyalty.VIPPassenger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class LoyaltyProgamTest {
    @Test
    void testRegularPassengerPaysFullPrice() {
        LoyaltyProgram regular = new RegularPassenger();
        BigDecimal basePrice = new BigDecimal("300.00");

        BigDecimal result = regular.applyDiscount(basePrice);

        assertEquals(new BigDecimal("300.00"), result, "Regular passenger should pay full price");
    }

    @Test
    void testVIPPassengerGetsDiscount() {
        LoyaltyProgram vip = new VIPPassenger();
        BigDecimal basePrice = new BigDecimal("300.00");

        BigDecimal result = vip.applyDiscount(basePrice);

        assertEquals(new BigDecimal("240.00"), result, "VIP passenger should pay 20% less");
    }

    @Test
    void testVIPPassengerRoundsToTwoDecimals() {
        LoyaltyProgram vip = new VIPPassenger();
        BigDecimal basePrice = new BigDecimal("299.99");

        BigDecimal result = vip.applyDiscount(basePrice);

        assertEquals(new BigDecimal("239.99"), result, "Discounted price should round to two decimals");
    }
}
