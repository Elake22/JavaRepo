// This class represents an Image media type and extends the abstract Media superclass
public class Image extends Media {

    // Private fields specific to Image
    private String dimensions;    // Image dimensions, e.g., "1920x1080"
    private String fileFormat;    // Image format, e.g., "JPEG", "PNG"

    // Constructor to initialize the name (from superclass), dimensions, and file format
    public Image(String name, String dimensions, String fileFormat) {
        super(name);                 // Call Media  constructor to set the name
        this.dimensions = dimensions; // Set image dimensions
        this.fileFormat = fileFormat; // Set image file format
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    @Override // Implements the abstract play() method from Media
    public void play() {
        System.out.println("Displaying image: " + getName());
    }

    @Override  // Implements the abstract getDescription() method from Media
    public String getDescription() {
        return "Image (" + dimensions + " , " + fileFormat + ")";
    }
}