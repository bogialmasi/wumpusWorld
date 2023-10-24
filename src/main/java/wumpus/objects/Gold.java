package wumpus.objects;

import java.util.Objects;

public class Gold {
    private int row;
    private int col;

    public Gold(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gold gold = (Gold) o;
        return row == gold.row && col == gold.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    public String goldOnMap() {
        return "G";
    }

    public boolean equals(String c) {
        if(goldOnMap().equals(c)) return true;
        return false;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Gold{");
        sb.append("row=").append(row);
        sb.append(", col=").append(col);
        sb.append('}');
        return sb.toString();
    }
}
