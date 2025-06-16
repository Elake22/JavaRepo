public class StoneDefenseSpell implements  Spell {
    @Override
    public void cast() {
        System.out.println("A towering wall of stone erupts from the ground, shielding you from harm!");
    }
    @Override
    public String getIncantation() {
        return "Stone wall";
    }

    @Override
    public String getHelp() {
        return "Summons a protective wall of earth to block attacks.";
    }
}

