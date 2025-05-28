// Level Up

public class Main {
    public static void main(String[] args) {
        int currentXP = 1200; // experience points
        int level = 5;
        int xpToNextLevel = 1500;
        boolean levelUp;

        // Quest complete +300XP
        currentXP += 300; //1500
        xpToNextLevel -= 300; // XP remaining

        // Double XP
        currentXP *= 2;

        // Next level condition check
        boolean xpFilled = currentXP >= xpToNextLevel;
        boolean isMaxLevel = level >= 10;

        // Conditions met
        levelUp = xpFilled && !isMaxLevel;
        boolean isPro = level > 7 || currentXP > 5000;

        System.out.printf("Player Stats: ");
        System.out.printf("Current XP: " + currentXP);
        System.out.printf("  Level: " + level);
        System.out.printf("  XP to next Level: " + xpToNextLevel);
        System.out.printf("  Level UP!: " + levelUp);
        System.out.printf("  Pro: " + isPro);

    }
}