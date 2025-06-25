// src/main/java/domain/Memory.java
package domain;

public class Memory {
    private String id;
    private String content;

    public Memory(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}