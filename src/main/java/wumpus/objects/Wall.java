package wumpus.objects;

import java.util.Objects;

public class Wall {
    private int row;
    private int col;

    public Wall(int row, int col) {
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
        if(wallOnMap().equals(c)) return true;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Wall{");
        sb.append("row=").append(row);
        sb.append(", col=").append(col);
        sb.append('}');
        return sb.toString();
    }

    public String wallOnMap(){
        return "W";
    }
}
