public class Warrior extends Character {
    private String weaponType;


    // Calls character constructor to set weapons
    public Warrior(String name, int health, int attackPower, String weaponType) {
        super(name, health, attackPower); // Sets stats
        this.weaponType = weaponType; // Sets weapons
    }
    // Overides ATK to give custom ATK style for warrior class
    @Override
    public void attack() {
        System.out.println(getName() + " swings a " + weaponType + " swiftly!" );
    }
}
