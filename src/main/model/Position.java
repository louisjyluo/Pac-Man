package model;

// represents the positions of every object in the game
public class Position {
    private int posX;
    private int posY;

    //REQUIRES: both x and y has to be between 0 and the terminal size.
    //MODIFIES: this
    //EFFECTS: Constructs the position on the map.
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
