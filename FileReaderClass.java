import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileReaderClass {
    private int numberOfCities;
    private String[] cityNames;
    private int numberOfRoutes;
    private String[][] routes;
    private String firstCity;
    private String endCity;
    private boolean isValidFile = true;

    public void readInput(String filePath) {
        try (Scanner sc = new Scanner(new File(filePath))) {
            int lineNumber = 1;

            // Number of Cities
            if (sc.hasNextLine()) {
                try {
                    numberOfCities = Integer.parseInt(sc.nextLine().trim());
                    lineNumber++;
                } catch (NumberFormatException e) {
                    System.err.println("Error Line: " + lineNumber + " Invalid number of cities.");
                    isValidFile = false;
                    return;
                }
            }

            // City Names
            if (sc.hasNextLine()) {
                cityNames = sc.nextLine().trim().split(" ");
                lineNumber++;
                if (cityNames.length != numberOfCities) {
                    System.err.println("Error Line: " + lineNumber + " City count mismatch.");
                    isValidFile = false;
                    return;
                }
            }

            // Number of Routes
            if (sc.hasNextLine()) {
                try {
                    numberOfRoutes = Integer.parseInt(sc.nextLine().trim());
                    lineNumber++;
                } catch (NumberFormatException e) {
                    System.err.println("Error Line: " + lineNumber + " Invalid number of routes.");
                    isValidFile = false;
                    return;
                }
            }

            // Routes
            routes = new String[numberOfRoutes][3];
            for (int i = 0; i < numberOfRoutes; i++) {
                if (sc.hasNextLine()) {
                    String[] routeData = sc.nextLine().trim().split(" ");
                    lineNumber++;
                    if (routeData.length != 3) {
                        System.err.println("Error Line: " + lineNumber + " Invalid route format.");
                        isValidFile = false;
                        return;
                    }
                    try {
                        Integer.parseInt(routeData[2]);
                    } catch (NumberFormatException e) {
                        System.err.println("Error Line: " + lineNumber + " Invalid time format.");
                        isValidFile = false;
                        return;
                    }
                    routes[i] = routeData;
                } else {
                    System.err.println("Error Line: " + lineNumber + " Missing route data.");
                    isValidFile = false;
                    return;
                }
            }

            // Start and End Cities
            if (sc.hasNextLine()) {
                String[] startEndCities = sc.nextLine().trim().split(" ");
                lineNumber++;
                if (startEndCities.length != 2) {
                    System.err.println("Error Line: " + lineNumber + " Invalid start and end cities format.");
                    isValidFile = false;
                    return;
                }
                firstCity = startEndCities[0];
                endCity = startEndCities[1];
            } else {
                System.err.println("Error Line: " + lineNumber + " Missing start and end cities.");
                isValidFile = false;
                return;
            }

            if (isValidFile) {
                System.out.println("File read is successful!");
            }

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    public int getNumberOfCities() {
        return numberOfCities;
    }

    public String[] getCityNames() {
        return cityNames;
    }

    public int getNumberOfRoutes() {
        return numberOfRoutes;
    }

    public String[][] getRoutes() {
        return routes;
    }

    public String getStartCity() {
        return firstCity;
    }

    public String getEndCity() {
        return endCity;
    }
}
