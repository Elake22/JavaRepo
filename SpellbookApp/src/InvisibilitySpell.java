public class InvisibilitySpell implements Spell {
    @Override
    public void cast() {
        System.out.println("A mystical vail shrouds you - you are now invisible!");
    }

    @Override
    public String getIncantation() {
        return "Cloak";
    }

    @Override
    public String getHelp() {
        return "Cloaks your presence, rendering you invisible";
    }
}
