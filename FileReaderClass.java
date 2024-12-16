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

    public void readInput(String filePath) {
        try (Scanner sc = new Scanner(new File(filePath))) {

            numberOfCities = Integer.parseInt(sc.nextLine().trim());


            cityNames = sc.nextLine().trim().split(" ");


            numberOfRoutes = Integer.parseInt(sc.nextLine().trim());


            routes = new String[numberOfRoutes][3];
            for (int i = 0; i < numberOfRoutes; i++) {
                String[] routeData = sc.nextLine().trim().split(" ");
                if (routeData.length == 3) {
                    routes[i] = routeData;
                } else {
                    System.err.println("Invalid route format at line " + (i + 4));
                    i--; 
                }
            }

            String[] startEndCities = sc.nextLine().trim().split(" ");
            if (startEndCities.length == 2) {
                firstCity = startEndCities[0];
                endCity = startEndCities[1];
            } else {
                System.err.println("Invalid start and end cities format.");
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format: " + e.getMessage());
        } 
    }

    public void displayMapData() {
        System.out.println("Number of Cities: " + numberOfCities);
        System.out.println("City Names: " + String.join(", ", cityNames));
        System.out.println("Number of Routes: " + numberOfRoutes);
        for (int i = 0; i < numberOfRoutes; i++) {
            System.out.println("Route: " + routes[i][0] + " to " + routes[i][1] + " takes " + routes[i][2] + " minutes");
        }
        System.out.println("Start City: " + firstCity);
        System.out.println("End City: " + endCity);
    }

    public void read() {


        FileReaderClass fileReader = new FileReaderClass();
        fileReader.readInput("D:\\Desktop\\New folder (4)\\CityMap\\src\\Test.txt");
        fileReader.displayMapData();
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
