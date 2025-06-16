public class Box<T> {
    private String label;
    private T value;

    public Box(String label, T value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
    public void displayContents() {
        System.out.println(label + " contains: " + value);
    }
}
