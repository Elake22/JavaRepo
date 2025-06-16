import java.util.ArrayList;

// Creating a Generic Container that can hold any type of object.
class Container<T> {
    private T value; // The stored item

    // This method lets us put something in the box.
    public void set(T value) {
        this.value = value;
    }

    // This method lets us take something out of the box.
    public T get() {
        return value;
    }
}