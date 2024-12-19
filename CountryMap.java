public class CountryMap {
    private City[] cities;
    private int[][] adjacencyMatrix;

    public CountryMap(String[] cityNames, String[][] routes) {
        
        this.cities = new City[cityNames.length];
        for (int i = 0; i < cityNames.length; i++) {
            cities[i] = new City(cityNames[i], i);
        }
        
        adjacencyMatrix = new int[cityNames.length][cityNames.length];
        for (int i = 0; i < cityNames.length; i++) {
            for (int j = 0; j < cityNames.length; j++) {
                adjacencyMatrix[i][j] = (i == j) ? 0 : Integer.MAX_VALUE;
            }
        }

        
        for (String[] route : routes) {
            int city1Index = getCityIndex(route[0]);
            int city2Index = getCityIndex(route[1]);
            int time = Integer.parseInt(route[2]);

            adjacencyMatrix[city1Index][city2Index] = time;
            adjacencyMatrix[city2Index][city1Index] = time; 
        }
    }

    private int getCityIndex(String cityName) {
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].getName().equals(cityName)) {
                return cities[i].getIndex();
            }
        }
        throw new IllegalArgumentException("City not found: " + cityName);
    }

    public City[] getCities() {
        return cities;
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }
}
