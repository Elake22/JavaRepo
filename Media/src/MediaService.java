// This class manages the media collection (the business logic layer)
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MediaService {
    private List<Media> mediaList; // List to store all media items (Video, Audio,  Image, Book)

    // Constructor to initialize list
    public MediaService() {
        mediaList = new ArrayList<>();
    }
    // Adds media to collection
    public void addMedia(Media media) {
        mediaList.add(media);
    }
    // Removes a media item by name
    public boolean removeMediaByName(String name) {
        Iterator<Media> iterator = mediaList.iterator();

        while (iterator.hasNext()) { // Iterate through the list and remove
            Media media = iterator.next();
            if (media.getName().equalsIgnoreCase(name)) {
                iterator.remove();
                return true;// Indicates success
            }
        }
        return false; // Indicates failure
    }
    public List<Media> getMediaList() {
        return new ArrayList<>(mediaList);
    }

    public Media findMediaByName(String name) { // Finds media by name
        for (Media media : mediaList) {
            if (media.getName().equalsIgnoreCase(name)) {
                return media;
            }
        }
        return null;
    }
    public boolean isEmpty() {
        return mediaList.isEmpty();
    }
    public List<Media> getAllMedia() {
        return new ArrayList<>(mediaList); // return a copy to protect the original list
    }

}


