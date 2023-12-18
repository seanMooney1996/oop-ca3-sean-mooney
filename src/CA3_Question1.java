import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 *  Name:
 *  Class Group:
 */
public class CA3_Question1
{
    public static void runSimulation()
    {
        Deque<Integer> carPark = new ArrayDeque<>();
        Deque<Integer> street = new ArrayDeque<>();

        int userInput = 1;
        Scanner input = new Scanner(System.in);

        while (userInput != 0) {
            System.out.println("Enter a number (+ to add new car, - for removal, 0 to end)");
            userInput = input.nextInt();
            if (!carPark.contains(userInput)) {
                if (userInput > 0) {
                    carPark.push(userInput);
                } else if (userInput < 0) {
                    if (!carPark.isEmpty()) {
                        removeCarFromStack(carPark, street, userInput);
                    } else {
                        System.out.println("Car park is empty");
                    }
                }
                printCarPark(carPark);
            } else {
                System.out.println("Car park contains that car with that reg already");
            }
        }
    }

    public static void printCarPark(Deque<Integer> carPark) {
        System.out.println("-Stack-");
        if (!carPark.isEmpty()) {
            for (int car : carPark) {
                System.out.println(car);
            }
        }
        System.out.println("-Stack-");
    }

    public static void removeCarFromStack(Deque<Integer> carPark, Deque<Integer> street, int userInput) {
        if (carPark.contains(userInput*-1)) {
            while (!carPark.isEmpty() && carPark.peek() != (userInput * -1)) {
                street.push(carPark.pop());
            }
            carPark.pop();
        } else
            System.out.println("Car of that reg does not exist in car park");
        while(!street.isEmpty()){
            carPark.push(street.pop());
        }
    }

    public static void main(String[] args) {
        runSimulation();
    }
}
