public class Mage extends Character {
    private String spell;

    // Calls character constructor to set spells
    public Mage(String name, int health, int attackPower, String spell) {
        super(name, health, attackPower); // Set stats
        this.spell = spell;              // Set spell
    }


    // Calls character constructor to set weapons
    @Override
    public void attack() {
        System.out.println(getName() + " cast " + spell );
    }
}
