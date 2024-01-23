import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Name:Sean Mooney
 * Class Group: SD2B
 */

/*
Direction enum used to indicate direction.
 */


public class CA3_Question9 {
    public static void main(String[] args) {
        int[][] maze = new int[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 0 || i == 7) {
                    maze[i][j] = 1;
                }
                if (j == 0 || j == 7) {
                    maze[i][j] = 1;
                }

                if ((i == 2 || i == 4 || i == 6) && (j != 4)) {
                    maze[i][j] = 1;
                }
            }
        }
        maze[5][5] = 1;
        maze[5][6] = 1;
        maze[3][0] = 0;
        int startRow = 3;
        int startCol = 4;

        Deque<PathDirection> paths = new ArrayDeque<>();
        display(maze);
        paths.push(new PathDirection(startRow, startCol, DIRECTION.WEST));
        paths.push(new PathDirection(startRow, startCol, DIRECTION.SOUTH));
        paths.push(new PathDirection(startRow, startCol, DIRECTION.EAST));
        paths.push(new PathDirection(startRow, startCol, DIRECTION.NORTH));
        int popCount= 0;
        while (!paths.isEmpty()) {
            PathDirection pd = paths.pop();
             popCount++;
            System.out.println("Popped direction: " + pd.getPathDirection()+" from row "+pd.getStartingRow()+" and column "+pd.getStartingColumn());
            DIRECTION dir = pd.getPathDirection();
            int x = pd.getStartingRow();
            int y = pd.getStartingColumn();
            solve(x,y,dir,maze,paths);
        }
        System.out.println("^^^^^    This was the exit   ^^^^");

    }

    public static void display(int[][] image) {
        for (int x = 0; x < image.length; x++) {
            for (int y = 0; y < image[0].length; y++) {
                System.out.printf("%4d", image[x][y]);
            }
            System.out.println();
        }
    }

    public static void solve(int x, int y, DIRECTION dir, int[][] maze, Deque<PathDirection> paths) {
        int newPositionRow = x;
        int newPositionColumn = y;
        boolean mazeEscape = false;
        boolean isIntersection = false;
        boolean atWall = false;
        do {
            if (newPositionRow == 7 || newPositionRow == 0 || newPositionColumn == 0 || newPositionColumn == 7) {
                mazeEscape = true;
            }
            if (dir == DIRECTION.NORTH) {
                newPositionRow -= 1;

            } else if (dir == DIRECTION.EAST) {
                newPositionColumn += 1;

            } else if (dir == DIRECTION.SOUTH) {
                newPositionRow += +1;
            } else {
                newPositionColumn -= -1;
            }

            if (maze[newPositionRow][newPositionColumn]!=0){
                atWall=true;
            } else {
                isIntersection = checkForIntersection(newPositionRow, newPositionColumn, dir, maze, paths);
            }

        } while (!atWall && !mazeEscape && !isIntersection);

        if (mazeEscape){
            while (!paths.isEmpty()) {
                paths.pop();
            }
        }
    }

    public static boolean checkForIntersection(int newPositionRow, int newPositionColumn, DIRECTION dir, int[][] maze, Deque<PathDirection> paths) {
        int northPos = maze[newPositionRow - 1][newPositionColumn];
        int eastPos = maze[newPositionRow][newPositionColumn + 1];
        int westPos = maze[newPositionRow][newPositionColumn - 1];
        int southPos = maze[newPositionRow + 1][newPositionColumn];
        int openPositionCount = 0;
         if (dir == DIRECTION.EAST || dir == DIRECTION.WEST) {
            if (northPos == 0) {
                paths.push(new PathDirection(newPositionRow, newPositionColumn,  DIRECTION.NORTH));
                openPositionCount++;
            }
            if (southPos == 0) {
                paths.push(new PathDirection(newPositionRow, newPositionColumn,  DIRECTION.SOUTH));
                openPositionCount++;
            }

            return openPositionCount > 0;
        }
        if (dir == DIRECTION.NORTH || dir == DIRECTION.SOUTH) {
            if (eastPos == 0) {
                paths.push(new PathDirection(newPositionRow, newPositionColumn, DIRECTION.EAST));
                openPositionCount++;
            }
            if (westPos == 0) {
                paths.push(new PathDirection(newPositionRow, newPositionColumn,  DIRECTION.WEST));
                openPositionCount++;
            }

            return openPositionCount > 0;
        }
        return false;
    }
}
