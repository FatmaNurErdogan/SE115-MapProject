public class WayFinder {
    private CountryMap map;

    public WayFinder(CountryMap map) {
        this.map = map;
    }

    public String findShortestPath(String startCity, String endCity) {
        int numberOfCities = map.getCities().length;
        boolean[] visited = new boolean[numberOfCities];
        int[] distances = new int[numberOfCities];
        int[] predecessors = new int[numberOfCities];

       
        for (int i = 0; i < numberOfCities; i++) {
            distances[i] = Integer.MAX_VALUE;
            predecessors[i] = -1;
        }

        int startIndex = getCityIndex(startCity);
        distances[startIndex] = 0;

        for (int i = 0; i < numberOfCities; i++) {
            int current = getClosestCity(distances, visited);
            visited[current] = true;

            for (int neighbor = 0; neighbor < numberOfCities; neighbor++) {
                if (!visited[neighbor] && map.getAdjacencyMatrix()[current][neighbor] != Integer.MAX_VALUE) {
                    int newDist = distances[current] + map.getAdjacencyMatrix()[current][neighbor];
                    if (newDist < distances[neighbor]) {
                        distances[neighbor] = newDist;
                        predecessors[neighbor] = current;
                    }
                }
            }
        }

        return buildPathWithTime(predecessors, startIndex, getCityIndex(endCity), distances);
    }

    private int getClosestCity(int[] distances, boolean[] visited) {
        int minDistance = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < distances.length; i++) {
            if (!visited[i] && distances[i] < minDistance) {
                minDistance = distances[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    private int getCityIndex(String cityName) {
        for (int i = 0; i < map.getCities().length; i++) {
            if (map.getCities()[i].getName().equals(cityName)) {
                return map.getCities()[i].getIndex();
            }
        }
        throw new IllegalArgumentException("City not found: " + cityName);
    }

    private String buildPathWithTime(int[] predecessors, int start, int end, int[] distances) {
        StringBuilder path = new StringBuilder();
        int current = end;

        while (current != -1) {
            path.insert(0, map.getCities()[current].getName() + " ");
            current = predecessors[current];
        }

        // Build the formatted output with time
        return "Fastest Way: " + path.toString().trim().replace(" ", "->") + "\nTotal Time: " + distances[end] + " min";
    }
}
