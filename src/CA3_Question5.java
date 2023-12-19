import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Name:
 * Class Group:
 */

public class CA3_Question5 {

    public static void main(String[] args) {
        Queue<String> takeOffQueue = new LinkedList<>();
        Queue<String> landingQueue = new LinkedList<>();

        boolean quit = false;

        while (!quit) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter a valid command: 'takeoff','land','next','quit'");
            String command = input.next();

            if (command.equalsIgnoreCase("takeoff")) {
                System.out.println("Enter flight name");
                takeOffQueue.add(input.next());
            } else if (command.equalsIgnoreCase("land")) {
                System.out.println("Enter flight name");
                landingQueue.add(input.next());
            } else if (command.equalsIgnoreCase("next")) {

                if (!landingQueue.isEmpty()) {
                    String landingFlight = landingQueue.remove();
                    System.out.println("Flight : " + landingFlight + " - landing");
                } else if (!takeOffQueue.isEmpty()) {
                    String takeOffFlight = takeOffQueue.remove();
                    System.out.println("Flight : " + takeOffFlight + " - take off");
                } else {
                    System.out.println("No flights in any queue");
                }

            } else if (command.equalsIgnoreCase("quit")) {
                quit = true;
            } else {
                System.out.println("Not a valid command");
            }
        }
    }
}
