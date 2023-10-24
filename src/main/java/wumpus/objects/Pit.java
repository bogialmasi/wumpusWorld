package wumpus.objects;

import java.util.Objects;

public class Pit {
    int row;
    int col;

    public Pit(int row, int col) {
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
        if(pitOnMap().equals(c)) return true;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    public String pitOnMap(){
        return "P";
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Pit{");
        sb.append("row=").append(row);
        sb.append(", col=").append(col);
        sb.append('}');
        return sb.toString();
    }
}
