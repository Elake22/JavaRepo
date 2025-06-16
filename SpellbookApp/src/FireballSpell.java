public class FireballSpell implements  Spell {
    public void cast() {
        System.out.println("\uD83D\uDCA5 Amaterasu a burst of pitch black flames");
    }
    public String getIncantation() {
        return " Fireball";
    }
    public String getHelp() {
       return "Amaterasu burns as hot as the sun itself, " +
               "and cannot be extinguished unless the user wills it, " +
               "or until the target has been reduced to ash";
    }
}
