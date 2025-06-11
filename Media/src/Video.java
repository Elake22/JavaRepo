//Extends Media and represents a video file
public class Video extends Media {
    private int duration;
    private String resolution;

    public Video(String name, int duration, String resolution) {
        super(name);
        this.duration = duration; // Duration of the video in minutes
        this.resolution = resolution; // Video resolution (e.g., "1080p", "4K")
    }
    // Getter method for duration
    public int getDuration() {
        return duration;
    }
    // Setter method for duration
    public void setDuration(int duration) {
        this.duration = duration;
    }
    // Getter method for resolution
    public String getResolution() {
        return resolution;
    }
    // Setter method for resolution
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    @Override // Implements the abstract play method from Media
    public void play() {
        System.out.println("Now playing video: " + getName());
    }

    @Override // Implements the abstract getDescription method from Media
    public String getDescription() {
        return "Video (" + resolution + ", " + duration + " min)";
    }
}
