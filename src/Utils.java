import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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

//    public static ArrayList<ElectionResult> parse2016ElectionResults(String data) {
//        ArrayList<ElectionResult> results = new ArrayList<ElectionResult>();
//
//        String[] lines = data.split(System.getProperty("line.separator"));
//        for (int i = 1; i < lines.length; i++) {
//            String singleLine = lines[i];
//
//            StringBuilder cleanLine = new StringBuilder();
//            boolean inQuotes = false;
//
//            for (char temp : singleLine.toCharArray()) {
//                if (inQuotes && temp != ',') {
//                    cleanLine.append(temp);
//                } else if (temp == '\"') {
//                    inQuotes = !inQuotes;
//                } else if (temp != '%') {
//                    cleanLine.append(temp);
//                }
//            }
//
//            results.add(new ElectionResult(cleanLine.toString().split(",")));
//        }
//
//        return results;
//    }

    public static DataManager parse2016Data(String resultsData, String educationData, String unemploymentData) {
        DataManager results = new DataManager();
        results.setStates(new ArrayList<State>());

        String[] lines = resultsData.split(System.getProperty("line.separator"));
        for (int i = 1; i < lines.length; i++) {

            String singleLine = lines[i];
            StringBuilder cleanLine = new StringBuilder();

            boolean inQuotes = false;

            for (char temp : singleLine.toCharArray()) {
                if (inQuotes && temp != ',') {
                    cleanLine.append(temp);
                } else if (temp == '\"') {
                    inQuotes = !inQuotes;
                } else if (temp != '%') {
                    cleanLine.append(temp);
                }
            }

            String[] parsedLine = cleanLine.toString().split(",");

            for (int j = 0; j < results.getStates().size(); i++) {
                if (results.getStates().get(j).getName().equals(parsedLine[7])) {
                    Boolean countyFound = false;
                    for (int k = 0; k < results.getStates().get(j).getCounties().size(); k++) {
                        if (results.getStates().get(j).getCounties().get(k).getName().equals(parsedLine[8])) {
                            countyFound = true;
                            results.getStates().get(j).getCounties().get(k).getVote2016().setDemVotes(Integer.parseInt(parsedLine[0]));
                            results.getStates().get(j).getCounties().get(k).getVote2016().setGopVotes(Integer.parseInt(parsedLine[1]));
                            results.getStates().get(j).getCounties().get(k).getVote2016().setTotalVotes(Integer.parseInt(parsedLine[2]));
                        }
                    }
                    if (!countyFound) {
                        County county = new County();
                        Election2016 electionResults = new Election2016();
                        electionResults.setDemVotes(Integer.parseInt(parsedLine[0]));
                        electionResults.setGopVotes(Integer.parseInt(parsedLine[1]));
                        electionResults.setTotalVotes(Integer.parseInt(parsedLine[2]));

                        county.setVote2016(electionResults);
                        county.setName(parsedLine[8]);
                        county.setFips(Integer.parseInt(parsedLine[9]));
                        results.getStates().get(j).getCounties().add(county);
                    }
                } else {
                    State state = new State();
                    state.setName(parsedLine[7]);

                    County county = new County();
                    Election2016 electionResults = new Election2016();
                    electionResults.setDemVotes(Integer.parseInt(parsedLine[0]));
                    electionResults.setGopVotes(Integer.parseInt(parsedLine[1]));
                    electionResults.setTotalVotes(Integer.parseInt(parsedLine[2]));

                    county.setVote2016(electionResults);
                    county.setName(parsedLine[8]);
                    county.setFips(Integer.parseInt(parsedLine[9]));
                    state.getCounties().add(county);

                    results.getStates().add(state);
                }
            }

        }

        lines = educationData.split(System.getProperty("line.separator"));
        for (int i = 6; i < lines.length; i++) {

            String singleLine = lines[i];
            StringBuilder cleanLine = new StringBuilder();

            boolean inQuotes = false;

            for (char temp : singleLine.toCharArray()) {
                if (inQuotes && temp != ',') {
                    cleanLine.append(temp);
                } else if (temp == '\"') {
                    inQuotes = !inQuotes;
                } else if (temp != '%') {
                    cleanLine.append(temp);
                }
            }

            String[] parsedLine = cleanLine.toString().split(",");

            //TODO: add code to add Education2016 to county from each line

        }

        lines = unemploymentData.split(System.getProperty("line.separator"));
        for (int i = 9; i < lines.length; i++) {

            String singleLine = lines[i];
            StringBuilder cleanLine = new StringBuilder();

            boolean inQuotes = false;

            for (char temp : singleLine.toCharArray()) {
                if (inQuotes && temp != ',') {
                    cleanLine.append(temp);
                } else if (temp == '\"') {
                    inQuotes = !inQuotes;
                } else if (temp != '%') {
                    cleanLine.append(temp);
                }
            }

            String[] parsedLine = cleanLine.toString().split(",");

            //TODO: add code to add Employment2016 to county from each line

        }

        return results;
    }

}
