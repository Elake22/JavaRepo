class ExitSpell implements Spell {
    public void cast() {
        System.out.println("A portal opens beneath your feet. Goodbye, wizard!");
        System.exit(0);
    }
    public String getIncantation() {
        return "quit";
    }
}