
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Name:
 * Class Group:
 */
public class CA3_Question6 {

    /*
    Will repeatedly ask the user to enter the commands in the format
    buy qty price
    or
    sell qty price
    or
    quit
     */

    public static void main(String[] args) {
        Queue<Share> stocks = new LinkedList<>();
        Scanner in = new Scanner(System.in);
        int totalStocks = 0;
        String command = "";
        do {
            System.out.print(">");
            command = in.next();
            if (command.equalsIgnoreCase("buy")) {
                int qty = in.nextInt();
                double price = in.nextDouble();
                Share s = new Share(qty, price);
                stocks.add(s);
                totalStocks += qty;
            } else if (command.equals("sell")) {
                int qty = in.nextInt();
                double price = in.nextDouble();
                double spent = 0;
                double profit = 0;
                int remainingQty = qty;

                if (qty <= totalStocks) {
                    while (remainingQty > 0) {
                        if (stocks.peek().getQuantity() < remainingQty) {
                            Share s = stocks.poll();
                            int quantityToBeSold = s.getQuantity();
                            spent = s.getPrice() * quantityToBeSold;
                            profit += (price * quantityToBeSold) - spent;
                            remainingQty = remainingQty - quantityToBeSold;
                            totalStocks -= quantityToBeSold;
                        } else {
                            Share s = stocks.peek();
                            int quantityToBeSold = remainingQty;
                            spent = s.getPrice() * quantityToBeSold;
                            profit += (price * quantityToBeSold) - spent;
                            remainingQty = 0;
                            totalStocks -= quantityToBeSold;
                        }
                    }
                } else {
                    System.out.println("Attempting to sell more shares than available");
                }
                System.out.println("This sale made a profit of " + profit);
            }
        } while (!command.equalsIgnoreCase("quit"));
    }
}