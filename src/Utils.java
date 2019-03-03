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
            String[] splitOnQuotations = singleLine.split("\"");
            for (int j = 1; j <= 11; j++) {
                String singleLineSplitOnQuotations = splitOnQuotations[j];
                String[] splitOnCommas = singleLineSplitOnQuotations.split(",");
                results.add(new ElectionResult(splitOnCommas));
            }
        }

        return results;
    }
}
