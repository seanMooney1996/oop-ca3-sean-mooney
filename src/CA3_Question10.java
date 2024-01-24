import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Name:Sean Mooney
 * Class Group: SD2B
 */
public class CA3_Question10 {

    public static void main(String[] args)throws IOException {
        Map<String, TreeSet<DistanceTo>> directConnections = new HashMap<>();

        File i = new File("src/connections.txt");
        Scanner input = new Scanner(i);

        while (input.hasNextLine()){
            String from = input.next();
            String to = input.next();
            int distance = input.nextInt();
            DistanceTo dt = new DistanceTo(to,distance);
            if (!directConnections.containsKey(from)){
                TreeSet<DistanceTo> t1 = new TreeSet<>();
                t1.add(dt);
                directConnections.put(from,t1);
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
            String target = shortestPriority.getTarget();
            if (!shortestKnownDistance.containsKey(target)) {
                int d = shortestPriority.getDistance();
                shortestKnownDistance.put(target, d);
                for (Map.Entry<String, TreeSet<DistanceTo>> entry : directConnections.entrySet()) {
                    String city = entry.getKey();
                    TreeSet<DistanceTo> cityConnections = entry.getValue();
                    if (city.equals(target)) {
                        for (DistanceTo dt : cityConnections) {
                            priority.add(new DistanceTo(dt.getTarget(), dt.getDistance() + d));
                        }
                    }
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
