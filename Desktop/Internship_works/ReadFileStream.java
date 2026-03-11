import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ReadFileStream {

    public static void main(String[] args) {

        try {

            Stream<String> stream = Files.lines(Paths.get("user.txt"));

            stream.forEach(System.out::println);

            stream.close();

        } catch (Exception e) {
            System.out.println("Error reading file");
        }
    }
}