package Airport.domain.model;

import Airport.domain.loyalty.LoyaltyProgram;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Passenger {

    // Attributes
    private final String name;
    private final String passportNumber;
    private final LoyaltyProgram loyaltyProgram;

    // Constructor for regular passengers
    public Passenger(String name, String passportNumber) {
        this(name, passportNumber, null);
    }

    // Constructor with loyalty program
    public Passenger(String name, String passportNumber, LoyaltyProgram loyaltyProgram) {
        this.name = name;
        this.passportNumber = passportNumber;
        this.loyaltyProgram = loyaltyProgram;
    }


    public String getName() {
        return name;
    }

    public String getPassportNumber() {
        return passportNumber;
    }
    public LoyaltyProgram getLoyaltyProgram() {
        return loyaltyProgram;
    }
    // Apply discount if available
    public BigDecimal getDiscountedPrice(BigDecimal originalPrice) {
        BigDecimal discounted = loyaltyProgram != null
                ? loyaltyProgram.applyDiscount(originalPrice)
                : originalPrice;

        // Round to 2 decimal places using HALF_UP rounding
        return discounted.setScale(2, RoundingMode.HALF_UP);
    }

}
