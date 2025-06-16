// Base class model
public abstract class Character implements Combatant{

    // Character fields  for stats
    private String name;
    private  int health;
    private int attackPower;

    // Constructs base stat value for characters
    public Character(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    // Still abstract or default attack() to override later
    public abstract void attack();
}
