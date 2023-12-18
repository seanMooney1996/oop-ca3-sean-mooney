import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.Stack;

/**
 *  Name:
 *  Class Group:
 */
public class CA3_Question2
{
    /*
        Starter function to create the 2D array and populate it with 0

     */
    public static int[][]  floodFillStart() {
        Scanner kb = new Scanner(System.in);
        int[][] arr = new int[10][10];
        for (int x = 0; x < 10; x++)
        {
            for (int y = 0; y < 10; y++)
            {
                arr[x][y] = 0;
            }
        }
       return arr;
    }
    /*
        Helper function to display the image
     */
    public static void display(int[][] arr)
    {
        for (int x = 0; x < 10; x++)
        {
            for (int y = 0; y < 10; y++)
            {
                System.out.printf("%4d", arr[x][y]);
            }
            System.out.println();
        }
    }
    private static void fill(int r, int c, int[][] arr)
    {
        RowColumnPair p1 = new RowColumnPair(r,c);
        Deque<RowColumnPair> pairStack = new ArrayDeque<>();
        pairStack.push(p1);
        int counter = 0;
        do{
            counter++;
            RowColumnPair p = pairStack.pop();
            arr[p.getRow()][p.getColumn()] = counter;
            getNotFilledCoordinates(pairStack,p,arr);
        }while(!pairStack.isEmpty());
    }

    public static void start()
    {
       int[][] arr = floodFillStart();
       display(arr);
        System.out.println("\nInput column and row to begin color flood");
        Scanner input = new Scanner(System.in);
        System.out.println("Row :");
        int row = input.nextInt();
        System.out.println("Column :");
        int column = input.nextInt();
        fill(row,column,arr);
        display(arr);
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


    public static void main(String[] args) {
        start();
    }

}
