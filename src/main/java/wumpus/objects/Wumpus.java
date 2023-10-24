package wumpus.objects;

import java.util.Objects;

public class Wumpus {
    private int row;
    private int col;

    public Wumpus(int row, int col) {
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

    public boolean equals(String c) {
        if(wumpusOnMap().equals(c)) return true;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Wumpus{");
        sb.append("row=").append(row);
        sb.append(", col=").append(col);
        sb.append('}');
        return sb.toString();
    }

    public String wumpusOnMap(){
        return "U";
    }
}
