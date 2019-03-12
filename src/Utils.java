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

    public static DataManager parse2016Data(String resultsData, String educationData, String unemploymentData) {
        DataManager results = new DataManager();
        results.setStates(new ArrayList<>());

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
                    boolean countyFound = false;
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

            for (int j = 0; i < singleLine.toCharArray().length; i++) {
                char temp = singleLine.toCharArray()[j];
                if (inQuotes && temp != ',') {
                    cleanLine.append(temp);
                } else if (temp == '\"') {
                    inQuotes = !inQuotes;
                } else if (temp != '%' && !(temp == ',' && temp == singleLine.toCharArray()[j + 1])) {
                    cleanLine.append(temp);
                }
            }

            String[] parsedLine = cleanLine.toString().split(",");

            for (int j = 0; j < results.getStates().size(); i++) {
                if (results.getStates().get(j).getName().equals(parsedLine[7])) {
                    boolean countyFound = false;
                    for (int k = 0; k < results.getStates().get(j).getCounties().size(); k++) {
                        if (results.getStates().get(j).getCounties().get(k).getName().equals(parsedLine[8])) {
                            countyFound = true;
                            results.getStates().get(j).getCounties().get(k).getEduc2016().setNoHighSchool(Double.parseDouble(parsedLine[7]));
                            results.getStates().get(j).getCounties().get(k).getEduc2016().setOnlyHighSchool(Double.parseDouble(parsedLine[8]));
                            results.getStates().get(j).getCounties().get(k).getEduc2016().setSomeCollege(Double.parseDouble(parsedLine[9]));
                            results.getStates().get(j).getCounties().get(k).getEduc2016().setBachlorsOrMore(Double.parseDouble(parsedLine[10]));
                        }
                    }
                    if (!countyFound) {
                        County county = new County();
                        Education2016 educationResults = new Education2016();
                        educationResults.setNoHighSchool(Double.parseDouble(parsedLine[41]));
                        educationResults.setOnlyHighSchool(Double.parseDouble(parsedLine[42]));
                        educationResults.setSomeCollege(Double.parseDouble(parsedLine[43]));
                        educationResults.setBachlorsOrMore(Double.parseDouble(parsedLine[44]));

                        county.setEduc2016(educationResults);
                        county.setName(parsedLine[2]);
                        county.setFips(Integer.parseInt(parsedLine[0]));
                        results.getStates().get(j).getCounties().add(county);
                    }
                } else {
                    State state = new State();
                    state.setName(parsedLine[1]);

                    County county = new County();
                    Education2016 educationResults = new Education2016();
                    educationResults.setNoHighSchool(Double.parseDouble(parsedLine[41]));
                    educationResults.setOnlyHighSchool(Double.parseDouble(parsedLine[42]));
                    educationResults.setSomeCollege(Double.parseDouble(parsedLine[43]));
                    educationResults.setBachlorsOrMore(Double.parseDouble(parsedLine[44]));

                    county.setEduc2016(educationResults);
                    county.setName(parsedLine[2]);
                    county.setFips(Integer.parseInt(parsedLine[0]));
                    state.getCounties().add(county);

                    results.getStates().add(state);
                }
            }

            //need noHighSchool, onlyHighSchool, someCollege, bachelorsOrMore
        }

        lines = unemploymentData.split(System.getProperty("line.separator"));
        for (int i = 9; i < lines.length; i++) {

            String singleLine = lines[i];
            StringBuilder cleanLine = new StringBuilder();

            boolean inQuotes = false;

            for (int j = 0; i < singleLine.toCharArray().length; i++) {
                char temp = singleLine.toCharArray()[j];
                if (inQuotes && temp != ',') {
                    cleanLine.append(temp);
                } else if (temp == '\"') {
                    inQuotes = !inQuotes;
                } else if (temp != '%' && !(temp == ',' && temp == singleLine.toCharArray()[j + 1])) {
                    cleanLine.append(temp);
                }
            }

            String[] parsedLine = cleanLine.toString().split(",");
            parsedLine[1] = parsedLine[1].toUpperCase();


            for (int j = 0; j < results.getStates().size(); i++) {
                if (results.getStates().get(j).getName().equals(parsedLine[7])) {
                    boolean countyFound = false;
                    for (int k = 0; k < results.getStates().get(j).getCounties().size(); k++) {
                        if (results.getStates().get(j).getCounties().get(k).getName().equals(parsedLine[8])) {
                            countyFound = true;
                            results.getStates().get(j).getCounties().get(k).getEmploy2016().setEmployedLaborForce(Integer.parseInt(parsedLine[42]));
                            results.getStates().get(j).getCounties().get(k).getEmploy2016().setTotalLaborForce(Integer.parseInt(parsedLine[41]));
                            results.getStates().get(j).getCounties().get(k).getEmploy2016().setUnemployedLaborForce(Integer.parseInt(parsedLine[43]));
                            results.getStates().get(j).getCounties().get(k).getEmploy2016().setUnemployedPercent(Integer.parseInt(parsedLine[44]));
                        }
                    }
                    if (!countyFound) {
                        County county = new County();
                        Employment2016 employmentResults = new Employment2016();
                        employmentResults.setEmployedLaborForce(Integer.parseInt(parsedLine[41]));
                        employmentResults.setTotalLaborForce(Integer.parseInt(parsedLine[42]));
                        employmentResults.setUnemployedLaborForce(Integer.parseInt(parsedLine[43]));
                        employmentResults.setUnemployedPercent(Double.parseDouble(parsedLine[44]));

                        county.setEmploy2016(employmentResults);
                        county.setName(parsedLine[2]);
                        county.setFips(Integer.parseInt(parsedLine[0]));
                        results.getStates().get(j).getCounties().add(county);
                    }
                } else {
                    State state = new State();
                    state.setName(parsedLine[1]);

                    County county = new County();
                    Employment2016 employmentResults = new Employment2016();
                    employmentResults.setEmployedLaborForce(Integer.parseInt(parsedLine[41]));
                    employmentResults.setTotalLaborForce(Integer.parseInt(parsedLine[42]));
                    employmentResults.setUnemployedLaborForce(Integer.parseInt(parsedLine[43]));
                    employmentResults.setUnemployedPercent(Double.parseDouble(parsedLine[44]));

                    county.setEmploy2016(employmentResults);
                    county.setName(parsedLine[2]);
                    county.setFips(Integer.parseInt(parsedLine[0]));
                    state.getCounties().add(county);

                    results.getStates().add(state);
                }
            }
            //toalLaborForce, employedLaborForce, unemployedLaborForce, unemployedPercent

        }

        return results;
    }

}
