package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Random;

//Class that represents the ghosts in PacMan
public class Ghost implements Writable {
    private Position body;
    private Position lastBody;
    private Walls walls;
    private Random num;
    private Boolean weak;
    private double randomNum;

    //REQUIRES: starting position must be inside the ghost box.
    //MODIFIES: this
    //EFFECTS: Sets up the ghosts starting position
    public Ghost() {
        this.body = new Position(10,7);
        this.lastBody = new Position(10,7);
        walls = new Walls();
        num = new Random();
        weak = false;
    }


    //MODIFIES: this
    //EFFECTS: move the ghost randomly based on the randomly generated number
    public void move() {
        randomNum = num.nextDouble();
        if (randomNum < 0.25) {
            moveDown();
        } else if (randomNum < 0.5) {
            moveUp();
        } else if (randomNum < 0.75) {
            moveRight();
        } else {
            moveLeft();
        }
    }

    //MODIFIES: this
    //EFFECTS: moves the ghost up by one, unless not possible
    public void moveUp() {
        if (cantMoveUp()) {
            body = new Position(body.getPosX(), body.getPosY());
        } else {
            body = new Position(body.getPosX(), body.getPosY() - 1);
            lastBody = new Position(body.getPosX(), body.getPosY() + 1);
        }
    }

    //MODIFIES: this
    //EFFECTS: moves the ghost down by one, unless not possible
    public void moveDown() {
        if (cantMoveDown()) {
            body = new Position(body.getPosX(), body.getPosY());
        } else {
            body = new Position(body.getPosX(), body.getPosY() + 1);
            lastBody = new Position(body.getPosX(), body.getPosY() - 1);
        }
    }

    //MODIFIES: this
    //EFFECTS: moves the ghost left by one, unless not possible
    public void moveLeft() {
        if (cantMoveLeft()) {
            body = new Position(body.getPosX(), body.getPosY());
        } else {
            body = new Position(body.getPosX() - 1, body.getPosY());
            lastBody = new Position(body.getPosX() + 1, body.getPosY());
        }
    }

    //MODIFIES: this
    //EFFECTS: moves the ghost right by one, unless not possible
    public void moveRight() {
        if (cantMoveRight()) {
            body = new Position(body.getPosX(), body.getPosY());
        } else {
            body = new Position(body.getPosX() + 1, body.getPosY());
            lastBody = new Position(body.getPosX() - 1, body.getPosY());
        }
    }

    //MODIFIES: this
    //EFFECTS: return true if there is a wall above the ghost
    public boolean cantMoveDown() {
        for (int i = 0; i < walls.getWalls().length; i++) {
            if (body.getPosY() + 1 == walls.getWalls()[i][0]
                    && body.getPosX() == walls.getWalls()[i][1]) {
                return true;
            }
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: return true if there is a wall left of the ghost
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
    //EFFECTS: return true if there is a wall right of the ghost
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
    //EFFECTS: return true if there is a wall below the ghost
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
    //EFFECTS: parses individual ghost into position and boolean Json.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("position", body.toJson());
        json.put("last position", lastBody.toJson());
        json.put("state", weak);
        return json;
    }

    public Position getLastBody() {
        return lastBody;
    }

    public Position getPos() {
        return body;
    }

    public void setPos(int x, int y) {
        this.body = new Position(x,y);
    }

    public void setLastBody(int x, int y) {
        this.lastBody = new Position(x,y);
    }

    public boolean getWeak() {
        return weak;
    }

    public void setWeakGhost(boolean weak) {
        this.weak =  weak;
    }

    public double getRandomNum() {
        return randomNum;
    }

}
