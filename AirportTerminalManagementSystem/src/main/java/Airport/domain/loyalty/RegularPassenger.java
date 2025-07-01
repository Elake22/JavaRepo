package Airport.domain.loyalty;

import java.math.BigDecimal;

public class RegularPassenger implements  LoyaltyProgram {

    @Override
    public BigDecimal applyDiscount(BigDecimal ticketPrice) {
        return ticketPrice;
    }
}
