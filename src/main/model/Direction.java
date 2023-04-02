package model;

//The direction used by PacMan and Ghosts for movement
public enum Direction {
    UP(0, -1),
    DOWN(0, 1),
    RIGHT(1, 0),
    LEFT(-1, 0);


    private int dx;
    private int dy;

    //MODIFIES: this
    //EFFECTS: Constructs the direction
    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    //MODIFIES: this
    //EFFECTS: adds the dx and dy to the objects position's x and y.
    public Position move(Position pos) {
        return new Position(
                pos.getPosX() + dx,
                pos.getPosY() + dy);
    }

}
