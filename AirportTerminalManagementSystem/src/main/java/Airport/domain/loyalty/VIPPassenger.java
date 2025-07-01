package Airport.domain.loyalty;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class VIPPassenger implements LoyaltyProgram {

    // A VIP passenger gets a 20% discount
    @Override
    public BigDecimal applyDiscount(BigDecimal price) {
        return price.multiply(new BigDecimal("0.80")); // 20% discount
    }
}
