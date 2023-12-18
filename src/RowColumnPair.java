public class RowColumnPair{
    private int column;
    private int row;

    public RowColumnPair(int row, int column) {
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RowColumnPair that = (RowColumnPair) obj;
        return row == that.row && column == that.column;
    }
}
