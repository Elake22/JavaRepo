public class HealSpell implements Spell {
    @Override
    public void cast() {
        System.out.println("You chew a Senzu Bean and feel completely restored!");
    }

    @Override
    public String getIncantation() {
        return "senzu bean";
    }

    @Override
    public String getHelp() {
        return "Magical, green, and crunchy beans that possess " +
                "potent healing and restorative properties";
    }
}
