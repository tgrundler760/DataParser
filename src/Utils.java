import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {
    public static String readFileAsString(String filepath) {
        StringBuilder output = new StringBuilder();

        try (Scanner scanner = new Scanner(new File(filepath))) {

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                output.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output.toString();
    }

    public static ArrayList<ElectionResult> parse2016ElectionResults(String data) {
        ArrayList<ElectionResult> results = new ArrayList<ElectionResult>();

        String[] lines = data.split(System.getProperty("line.separator"));
        for (int i = 1; i < lines.length; i++) {
            String singleLine = lines[i];

            StringBuilder cleanLine = new StringBuilder();
            boolean inQuotes = false;

            for (char temp : singleLine.toCharArray()) {
                if (inQuotes) {
                    cleanLine.append(temp);
                } else if (temp == '\"') {
                    inQuotes = !inQuotes;
                } else if (temp != '%') {
                    cleanLine.append(temp);
                }
            }

            results.add(new ElectionResult(cleanLine.toString().split(",")));
        }

        return results;
    }
}
