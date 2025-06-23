import java.io.*;
import java.io.FileReader;

// Create file
public class FileIOEx {
    public static void main(String[] args) {
        File file = new File("student_data.txt");
        {
            try {
                if (file.createNewFile()) {
                    System.out.println("student_data.txt created");

                } else {
                    System.out.println("student_data already exist");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
            // Print to text to file
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("student_data.txt");
            writer.println("Alice A");
            writer.println("Bob B");
            writer.println("Charlie, A+");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            // Always clean up!
            if (writer != null) {
                writer.close();
            }
        } // Append file
        try (FileWriter fileWriter = new FileWriter("student_data.txt", true);
             PrintWriter writer2 = new PrintWriter(fileWriter)) {
            writer2.println("David, B+");
            writer2.println("Eva, A");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // Print file contents to terminal
        System.out.println("File contents:");
        try (FileReader fileReader = new FileReader("student_data.txt");
             BufferedReader reader = new BufferedReader(fileReader)) {

        // When there are no more lines, readLine() returns null.
            for (String line = reader.readLine(); line != null; line =
                    reader.readLine()) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}