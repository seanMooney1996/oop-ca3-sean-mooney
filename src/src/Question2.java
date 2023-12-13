import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Question2 {
    public static void main(String[] args) {
        int[][] array2D = new int[10][10];

        for (int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                array2D[i][j] = 0;
            }
        }

        for (int i=0; i<10;i++) {
            System.out.print("\n");
            for (int j = 0; j < 10; j++) {
                System.out.printf("%5d", array2D[i][j]);
            }
        }

        System.out.println("\nInput column and row to begin color flood");

        Scanner input = new Scanner(System.in);
        System.out.println("Row :");
        int row = input.nextInt();
        System.out.println("Column :");
        int column = input.nextInt();
        RowColumnPair p1 = new RowColumnPair(row,column);
        Deque<RowColumnPair> pairStack = new ArrayDeque<>();
        pairStack.push(p1);
        int counter = 0;


        do{
            counter++;
            RowColumnPair p = pairStack.pop();
            array2D[p.getRow()][p.getColumn()] = counter;
            getNotFilledCoordinates(pairStack,p,array2D);
        }while(!pairStack.isEmpty());


        for (int i=0; i<10;i++) {
            System.out.print("\n");
            for (int j = 0; j < 10; j++) {
                System.out.printf("%5d", array2D[i][j]);
            }
        }
    }

    public static void getNotFilledCoordinates(Deque<RowColumnPair> pairStack,RowColumnPair p,int[][] array2D){
        RowColumnPair north = new RowColumnPair(p.getRow()-1,p.getColumn());
        RowColumnPair south = new RowColumnPair(p.getRow()+1,p.getColumn());
        RowColumnPair east = new RowColumnPair(p.getRow(),p.getColumn()+1);
        RowColumnPair west = new RowColumnPair(p.getRow(),p.getColumn()-1);

       RowColumnPair[] pairs = new RowColumnPair[4];
       pairs[0] = north;
       pairs[1] = south;
       pairs[2] = east;
       pairs[3] = west;


       for (RowColumnPair pair: pairs){
           int row = pair.getRow();
           int column = pair.getColumn();
           boolean rowCheck = (row>-1 && row<10);
           boolean columnCheck = (column>-1 && column<10);
           if (rowCheck && columnCheck){
               if (array2D[row][column]==0 && !pairStack.contains(pair)){
                   pairStack.push(pair);
               }
           }
       }
    }
}
