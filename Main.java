import java.io.File;

public class Main {
    public static void main(String[] args) {

        String inputFilePath = "src/input.txt";
        File inputFile = new File(inputFilePath);


        if (inputFile.exists() && inputFile.getName().equals("input.txt")) {
            FileReaderClass fileReader = new FileReaderClass();
            fileReader.readInput(inputFilePath);

            CountryMap map = new CountryMap(fileReader.getCityNames(), fileReader.getRoutes());
            WayFinder pathFinder = new WayFinder(map);

            String startCity = fileReader.getStartCity();
            String endCity = fileReader.getEndCity();
            String result = pathFinder.findShortestPath(startCity, endCity);

            System.out.println(result);
        } else {
            System.out.println("Please provide the input file as in input.txt form.");
        }
    }
}
