package model;

import org.json.JSONObject;
import persistence.Writable;

import java.security.DigestException;


//Pacman class - represents PacMan, have move() method, wall collision, direction.
public class PacMan implements Writable {
    private Position body;
    private Direction dir;
    private Direction lastDir;
    private Position lastBody;
    private final Walls walls;

    //REQUIRES: starting position must be not a wall, inside the ghost box, and within the map.
    //MODIFIES: this
    //EFFECTS: Sets up PacMan's starting position
    public PacMan() {
        body = new Position(10, 10);
        dir = Direction.UP;
        lastBody = new Position(10,10);
        walls = new Walls();
        lastDir = Direction.UP;
    }

    //MODIFIES: this
    //EFFECTS: Moves pacman with arrow keys.
    public void move() {
        if (dir == Direction.RIGHT) {
            moveRight();
            lastDir = Direction.RIGHT;
        } else if (dir == Direction.LEFT) {
            moveLeft();
            lastDir = Direction.LEFT;
        } else if (dir == Direction.UP) {
            moveUp();
            lastDir = Direction.UP;
        } else {
            moveDown();
            lastDir = Direction.DOWN;
        }
    }

    //MODIFIES: this
    //EFFECTS: move right if possible.
    public void moveRight() {
        if (cantMoveRight()) {
            dir = lastDir;
        } else {
            body = new Position(body.getPosX() + 1, body.getPosY());
            lastBody = new Position(body.getPosX() - 1, body.getPosY());
        }
    }

    //MODIFIES: this
    //EFFECTS: move left if possible.
    public void moveLeft() {
        if (cantMoveLeft()) {
            dir = lastDir;
        } else {
            body = new Position(body.getPosX() - 1, body.getPosY());
            lastBody = new Position(body.getPosX() + 1, body.getPosY());
        }
    }

    //MODIFIES: this
    //EFFECTS: move up if possible.
    public void moveUp() {
        if (cantMoveUp()) {
            dir = lastDir;
        } else {
            body = new Position(body.getPosX(), body.getPosY() - 1);
            lastBody = new Position(body.getPosX(), body.getPosY() + 1);
        }
    }

    //MODIFIES: this
    //EFFECTS: move down if possible.
    public void moveDown() {
        if (cantMoveDown()) {
            dir = lastDir;
        } else {
            body = new Position(body.getPosX(), body.getPosY() + 1);
            lastBody = new Position(body.getPosX(), body.getPosY() - 1);
        }
    }

    //MODIFIES: this
    //EFFECTS: returns true if a wall occupies the space below, and also if the ghost gate is below it.
    public boolean cantMoveDown() {
        for (int i = 0; i < walls.getWalls().length; i++) {
            if (body.getPosY() + 1 == walls.getWalls()[i][0]
                    && body.getPosX() == walls.getWalls()[i][1]) {
                return true;
            }
        }
        if (body.getPosX() == 10 && body.getPosY() + 1 == 6) {
            return true;
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: returns true if a wall occupies the space to the left.
    public boolean cantMoveLeft() {
        for (int i = 0; i < walls.getWalls().length; i++) {
            if (body.getPosX() - 1 == walls.getWalls()[i][1]
                    && body.getPosY() == walls.getWalls()[i][0]) {
                return true;
            }
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: returns true if a wall occupies the space to the right.
    public boolean cantMoveRight() {
        for (int i = 0; i < walls.getWalls().length; i++) {
            if (body.getPosX() + 1 == walls.getWalls()[i][1]
                    && body.getPosY() == walls.getWalls()[i][0]) {
                return true;
            }
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: returns true if a wall occupies the space above.
    public boolean cantMoveUp() {
        for (int i = 0; i < walls.getWalls().length; i++) {
            if (body.getPosY() - 1 == walls.getWalls()[i][0]
                    && body.getPosX() == walls.getWalls()[i][1]) {
                return true;
            }
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: parses PacMan into positions and directions
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("position",body.toJson());
        json.put("last position", lastBody.toJson());
        json.put("direction", dir);
        return json;
    }

    public Position getPos() {
        return body;
    }

    public void setDirection(Direction direction) {
        this.dir = direction;
    }

    public Direction getDir() {
        return dir;
    }

    public Position getLastBody() {
        return lastBody;
    }

    public void setBody(int x, int y) {
        this.body = new Position(x,y);
    }

    public void setLastBody(int x, int y) {
        this.lastBody = new Position(x,y);
    }

    public void setLastDir(Direction dir) {
        this.lastDir = dir;
    }

    public Direction getLastDir() {
        return lastDir;
    }





}
