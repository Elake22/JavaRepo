package learn.memories.models;

public class Memory {
    private int id;
    private String from;
    private String content;
    private boolean shareable;

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public boolean isShareable() { return shareable; }
    public void setShareable(boolean shareable) { this.shareable = shareable; }
}
