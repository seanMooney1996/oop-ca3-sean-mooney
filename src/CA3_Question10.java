import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Name:Sean Mooney
 * Class Group: SD2B
 */
public class CA3_Question10 {

    public static void main(String[] args) throws IOException {
        Map<String, TreeSet<DistanceTo>> directConnections = new HashMap<>();

        File i = new File("src/connections.txt");
        Scanner input = new Scanner(i);

        while (input.hasNextLine()) {
            String from = input.next();
            String to = input.next();
            int distance = input.nextInt();
            DistanceTo dt = new DistanceTo(to, distance);
            if (!directConnections.containsKey(from)) {
                TreeSet<DistanceTo> t1 = new TreeSet<>();
                t1.add(dt);
                directConnections.put(from, t1);
            } else {
                directConnections.get(from).add(dt);
            }

        }


        String from = "Pendleton";

        PriorityQueue<DistanceTo> priority = new PriorityQueue<>();
        priority.add(new DistanceTo(from, 0));

        Map<String, Integer> shortestKnownDistance = new HashMap<>();

        while (!priority.isEmpty()) {
            DistanceTo shortestPriority = priority.poll();
            String currentPCity = shortestPriority.getTarget();
            if (!shortestKnownDistance.containsKey(currentPCity)) {
                int currentPDistance = shortestPriority.getDistance();
                shortestKnownDistance.put(currentPCity, currentPDistance);
                TreeSet<DistanceTo> currentCitiesConnections = directConnections.get(currentPCity);
                for (DistanceTo dt : currentCitiesConnections) {
                    String nextCity = dt.getTarget();
                    int newDistance = currentPDistance + dt.getDistance();
                    priority.add(new DistanceTo(nextCity, newDistance));
                }
            }
        }

        for (Map.Entry<String, Integer> entry : shortestKnownDistance.entrySet()) {
            String city = entry.getKey();
            int shortestDistanceToThatCity = entry.getValue();

            System.out.println("To city: " + city + "       Shortest Distance: " + shortestDistanceToThatCity);
        }
    }
}
