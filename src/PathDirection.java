public class PathDirection {


    private int startingRow;

    private int startingColumn;

    private DIRECTION pathDirection;

    public PathDirection(int startingRow, int startingColumn, DIRECTION pathDirection) {
        this.startingRow = startingRow;
        this.startingColumn = startingColumn;
        this.pathDirection = pathDirection;
    }

    public int getStartingRow() {
        return startingRow;
    }

    public void setStartingRow(int startingRow) {
        this.startingRow = startingRow;
    }

    public int getStartingColumn() {
        return startingColumn;
    }

    public void setStartingColumn(int startingColumn) {
        this.startingColumn = startingColumn;
    }

    public DIRECTION getPathDirection() {
        return pathDirection;
    }

    public void setPathDirection(DIRECTION pathDirection) {
        this.pathDirection = pathDirection;
    }
}
