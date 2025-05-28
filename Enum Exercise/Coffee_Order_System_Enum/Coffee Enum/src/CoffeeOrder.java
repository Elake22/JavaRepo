// Coffee Enum

public class CoffeeOrder {
    enum CoffeeSize {
        SMALL,
        MEDIUM,
        LARGE
    }

    public static void main(String[] args) {
        CoffeeSize myOrder = CoffeeSize.MEDIUM;
        System.out.println("Coffee size:  " + myOrder);

    }
}