import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String resultsData = Utils.readFileAsString("data/2016_Presidential_Results.csv");
        String educationData = Utils.readFileAsString("data/Education.csv");
        String unemploymentData = Utils.readFileAsString("data/Unemployment.csv");
        DataManager results = Utils.parse2016Data(resultsData, educationData, unemploymentData);
        if (results.getStates().size() > 48) System.out.println("it worked I think");
        //ArrayList<ElectionResult> results = Utils.parse2016ElectionResults(data);
    }
}
