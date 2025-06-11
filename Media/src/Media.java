public abstract class Media {
    protected String name;

    // Constructor to initialize the name
    public Media(String name) {
        this.name = name;
    }
    // Name getter
    public String getName() {
        return name;
    }
    // name setter
    public void setName(String name) {
        this.name = name;
    }

    // Abstract method to be overridden by subclasses
    public abstract void play();

    // Abstract method to be overridden by subclass();
    public abstract String getDescription();

}