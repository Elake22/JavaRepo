// Seat Reservations

public class SeatReservation {
    enum SeatSection {
        GENERAL,
        PREMIUM,
        VIP
    }


    public static void main(String[] args) {
        for (SeatSection section : SeatSection.values()) {
            System.out.println(section + " is assigned value: " + section.ordinal());
        }
    }
}