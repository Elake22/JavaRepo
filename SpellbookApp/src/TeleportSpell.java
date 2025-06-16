public class TeleportSpell implements Spell {
    @Override
    public void cast() {
        System.out.println("You warp and reappear in a new location!");
    }

    @Override
    public String getIncantation() {
        return "teleport";
    }

    @Override
    public String getHelp() {
        return "\"Instantly transports you to a location in visible range.\"";
    }
}
