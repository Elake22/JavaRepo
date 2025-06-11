// This class represents an Audio media type and extends the abstract Media superclass
public class Audio extends Media {
    // Private fields specific to Audio
    private int duration;    // Duration of the audio in minutes
    private String artist;   // Name of the artist or performer

    // Constructor that initializes the name (via superclass)
    public Audio(String name, int duration, String artist) {
        super(name); // Call the constructor of Media to set the name
        this.duration = duration;
        this.artist = artist;
    }




    // Implements the abstract play method from Media
    @Override
    public void play() {
        System.out.println("Now playing audio: " + getName());
    }

    // Implements the abstract getDescription method from Media
    // Returns a formatted string with artist and duration details
    @Override
    public String getDescription() {
        return "Audio by " + artist + " (" + duration + " min)";
    }
}
