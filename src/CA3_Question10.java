import java.util.*;

/**
 * Name:Sean Mooney
 * Class Group: SD2B
 */
public class CA3_Question10 {

    public static void main(String[] args) {
        Map<String, TreeSet<DistanceTo>> directConnections = new HashMap<>();

        TreeSet<DistanceTo> t1 = new TreeSet<>();
        t1.add(new DistanceTo("Pierre", 2));
        t1.add(new DistanceTo("Pueblo", 8));
        t1.add(new DistanceTo("Phoenix", 4));

        directConnections.put("Pendleton", t1);

        TreeSet<DistanceTo> t2 = new TreeSet<>();
        t2.add(new DistanceTo("Pendleton", 2));
        t2.add(new DistanceTo("Pueblo", 3));
        directConnections.put("Pierre", t2);


        TreeSet<DistanceTo> t3 = new TreeSet<>();
        t3.add(new DistanceTo("Peoria", 3));
        t3.add(new DistanceTo("Pierre", 3));
        t3.add(new DistanceTo("Pendleton", 8));
        t3.add(new DistanceTo("Phoenix", 3));

        directConnections.put("Pueblo", t3);

        TreeSet<DistanceTo> t4 = new TreeSet<>();
        t4.add(new DistanceTo("Pueblo", 3));
        t4.add(new DistanceTo("Pendleton", 4));
        t4.add(new DistanceTo("Pittsburgh", 10));
        t4.add(new DistanceTo("Peoria", 4));
        t4.add(new DistanceTo("Pensacola", 5));

        directConnections.put("Phoenix", t4);

        TreeSet<DistanceTo> t5 = new TreeSet<>();
        t5.add(new DistanceTo("Pueblo", 3));
        t5.add(new DistanceTo("Phoenix", 4));
        t5.add(new DistanceTo("Pittsburgh", 5));

        directConnections.put("Peoria", t5);

        TreeSet<DistanceTo> t6 = new TreeSet<>();
        t6.add(new DistanceTo("Peoria", 5));
        t6.add(new DistanceTo("Phoenix", 10));
        t6.add(new DistanceTo("Pensacola", 4));
        t6.add(new DistanceTo("Princeton", 2));

        directConnections.put("Pittsburgh", t6);

        TreeSet<DistanceTo> t7 = new TreeSet<>();
        t7.add(new DistanceTo("Pittsburgh", 4));
        t7.add(new DistanceTo("Phoenix", 5));
        t7.add(new DistanceTo("Princeton", 5));

        directConnections.put("Pensacola", t7);

        TreeSet<DistanceTo> t8 = new TreeSet<>();
        t8.add(new DistanceTo("Pensacola", 2));
        t8.add(new DistanceTo("Pittsburgh", 5));

        directConnections.put("Princeton", t8);

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
