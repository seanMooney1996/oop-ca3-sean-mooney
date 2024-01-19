import java.util.*;

/**
 * Name:
 * Class Group:
 */
public class CA3_Question7 {
    /*
   Will repeatedly ask the user to enter the commands in the format
   buy company qty price
   or
   sell company qty price
   or
   quit
    */
    public static void main(String[] args) {
        Map<String, Queue<Share>> companyMap = new HashMap<>();
        Scanner in = new Scanner(System.in);
        String command = "";
        do {
            System.out.print(">");
            command = in.next();
            if (command.equalsIgnoreCase("buy")) {
                String company = in.next().trim();
                int qty = in.nextInt();
                double price = in.nextDouble();
                // Code to buy shares here

                Share s = new Share(qty, price);
                if (companyMap.containsKey(company)) {
                    Queue<Share> compShares = companyMap.get(company);
                    compShares.add(s);
                } else {
                    Queue<Share> stocks = new LinkedList<>();
                    stocks.add(s);
                    companyMap.put(company, stocks);
                }
            } else if (command.equals("sell")) {
                String company = in.next().trim();
                int qty = in.nextInt();
                double price = in.nextDouble();
                int totalStocks = 0;
                Queue<Share> compShares = null;
                if (companyMap.containsKey(company)) {
                    compShares = companyMap.get(company);
                    for (Share s : compShares) {
                        totalStocks += s.getQuantity();
                    }
                    double spent;
                    double profit = 0;
                    int remainingQty = qty;
                    if (qty <= totalStocks) {
                        while (remainingQty > 0) {
                            if (compShares.peek().getQuantity() < remainingQty) {
                                Share s = compShares.poll();
                                int quantityToBeSold = s.getQuantity();
                                spent = s.getPrice() * quantityToBeSold;
                                profit += (price * quantityToBeSold) - spent;
                                remainingQty = remainingQty - quantityToBeSold;
                                totalStocks -= quantityToBeSold;
                            } else {
                                Share s = compShares.peek();
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
                } else {
                    System.out.println("No shares exists with named company");
                }
            }
        } while (!command.equalsIgnoreCase("quit"));
    }
}
