public class Archer extends Character {
    private int arrows;


    // Calls character constructor to set weapons
    public Archer(String name, int health, int attackPower, int arrows) {
        super(name, health, attackPower);
        this.arrows = arrows;
    }

    // Calls character constructor to set ATK
    @Override
    public void attack() {
        System.out.println(getName() + " fires an arrow! " + --arrows + " arrows left.");
    }
}