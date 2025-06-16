import java.util.ArrayList;

public class SpellBook {
    private ArrayList<Spell> spells;

    //Populates spellbook with spells
    public SpellBook() {
        spells = new ArrayList<>();
        spells.add(new FireballSpell());
        spells.add(new HealSpell());
        spells.add(new InvisibilitySpell());
        spells.add(new StoneDefenseSpell());
        spells.add(new TeleportSpell());
        spells.add(new ExitSpell());
    }
    // Attempts to cast spell based on spell name
    public void tryIncantation(String incantation) {
        for (Spell spell : spells) {
            if (spell.getIncantation().equalsIgnoreCase(incantation)) {
                spell.cast();
                return;
            }

        }
        System.out.println("Pshhhh...The spell fizzled out! Try again.");
    }
    // Bonus: Display all available spells and their descriptions
    public void getHelp() {
        System.out.println("\n📖 Known Spells:");
        System.out.printf("%-15s | %s\n", "Incantation", "Description");
        System.out.println("---------------------|-------------------------------");
        for (Spell spell : spells) {
            System.out.printf("%-15s | %s\n", spell.getIncantation(), spell.getHelp());
        }
    }
}


