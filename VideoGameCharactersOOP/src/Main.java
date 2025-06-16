public class Main {
    public static void main(String[] args) {
        Combatant[] fighters = new Combatant[3];


        fighters[0] = new Warrior("Kratos", 100, 20, "battle axe");
        fighters[1] = new Mage("Harry", 80, 25, "Fireball");
        fighters[2] = new Archer("Legolas", 90, 15, 20);

        for (Combatant c : fighters) { // Polymorphic call, treat different subclass objects
            c.attack();  //as if they were the same superclass type, then the correct method run.
        }
    }
}