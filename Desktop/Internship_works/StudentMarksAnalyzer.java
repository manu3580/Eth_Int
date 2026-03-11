import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class StudentMarksAnalyzer {

    public static void main(String[] args) {

        try {

            Stream<String> stream = Files.lines(Paths.get("marks.txt"));

            int[] marks = stream.mapToInt(Integer::parseInt).toArray();

            int total = 0;
            int highest = 0;

            for (int mark : marks) {

                total = total + mark;

                if (mark > highest) {
                    highest = mark;
                }
            }

            double average = (double) total / marks.length;

            System.out.println("Total Marks: " + total);
            System.out.println("Average Marks: " + average);
            System.out.println("Highest Mark: " + highest);

        } catch (Exception e) {
            System.out.println("Error reading file");
        }
    }
}