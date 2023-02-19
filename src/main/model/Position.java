package model;

public class Position {
    private int posX;
    private int posY;

    //REQUIRES: both x and y has to be between 0 and the terminal size.
    //MODIFIES: this
    //EFFECTS:
    public Position(int x, int y) {
        posX = x;
        posY = y;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }


}
