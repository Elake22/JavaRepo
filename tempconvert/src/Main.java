// Weather Temperature Conversion

public class Main {
    public static void main(String[] args) {
        double temperatureCelsius = 25.0;  // Current Temp in C
        boolean isRaining = false;        // Raining
        int windSpeedKhm = 10;            // wind speed in km/h

        double temperatureFahrenheit = (temperatureCelsius * 9 / 5) + 32;
        temperatureCelsius += 5; // increase by 5
        windSpeedKhm += 5;

        // convert C to F   C = (F -32) * 5/9
        temperatureFahrenheit = (temperatureCelsius * 9 / 5) + 32;

        boolean isHot = temperatureFahrenheit > 85;
        boolean isWindy = windSpeedKhm > 20;

        boolean goodDayToGoOut = !isRaining && temperatureFahrenheit >= 60 && temperatureFahrenheit <=85;
        boolean weatherWarning = windSpeedKhm > 30 || temperatureCelsius < 0;

        System.out.println("Today's Weather");
        System.out.println("Temperature in Fahrenheit:" + temperatureFahrenheit);
        System.out.println("Temperature in Celsius: " + temperatureCelsius);
        System.out.println("Rain? " + isRaining);
        System.out.println("Windy? " + isWindy);
        System.out.println("Hot? " + isHot);
        System.out.println("Good to go out? " + goodDayToGoOut );
        System.out.println("Weather Warning?  " + weatherWarning);

        if (isHot) {
            System.out.println("Too HOT!! ");
        }
    }
}