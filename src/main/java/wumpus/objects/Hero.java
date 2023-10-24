package wumpus.objects;

public class Hero {
    private int row;
    private int col;
    private int arrows;
    public enum dir{N, E, S, W};
    private boolean hasGold;

    public Hero(int row, int col, int arrows, boolean hasGold) {
        this.row = row;
        this.col = col;
        this.arrows = arrows;
        this.hasGold = hasGold;
    }


    public boolean isHasGold() {
        return hasGold;
    }

    public void setHasGold(boolean hasGold) {
        this.hasGold = hasGold;
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

    public int getArrows() {
        return arrows;
    }

    public void setArrows(int arrows) {
        this.arrows = arrows;
    }

    public String heroOnMap() {
        return "H";
    }

    public boolean equals(String c) {
        if (heroOnMap().equals(c)) return true;
        return false;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Hero stats:\n");
        sb.append("The hero stands on ").append(row).append(" ").append(col).append(" position.");
        sb.append("The hero has ").append(arrows).append(" pcs of arrow(s)");
        return sb.toString();
    }
}
