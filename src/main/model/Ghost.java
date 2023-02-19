package model;

import java.util.Random;

public class Ghost {
    private Position body;
    private Position lastBody;
    private Map walls;
    private Random num;

    public Ghost() {
        this.body = new Position(10,7);
        this.lastBody = new Position(10,7);
        walls = new Map();
        num = new Random();
    }

    //MODIFIES:
    //EFFECTS:
    public void move() {
        if (num.nextDouble() < 0.25) {
            moveDown();
        } else if (num.nextDouble() >= 0.25 && num.nextDouble() < 0.5) {
            moveUp();
        } else if (num.nextDouble() >= 0.5 && num.nextDouble() < 0.75) {
            moveRight();
        } else {
            moveLeft();
        }

    }

    //MODIFIES:
    //EFFECTS:
    public void moveUp() {
        if (cantMoveUp()) {
            body = new Position(body.getPosX(), body.getPosY());
        } else {
            body = new Position(body.getPosX(), body.getPosY() - 1);
            lastBody = new Position(body.getPosX(), body.getPosY() + 1);
        }
    }

    //MODIFIES:
    //EFFECTS:
    public void moveDown() {
        if (cantMoveDown()) {
            body = new Position(body.getPosX(), body.getPosY());
        } else {
            body = new Position(body.getPosX(), body.getPosY() + 1);
            lastBody = new Position(body.getPosX(), body.getPosY() - 1);
        }
    }

    //MODIFIES:
    //EFFECTS:
    public void moveLeft() {
        if (cantMoveLeft()) {
            body = new Position(body.getPosX(), body.getPosY());
        } else {
            body = new Position(body.getPosX() - 1, body.getPosY());
            lastBody = new Position(body.getPosX() + 1, body.getPosY());
        }
    }

    //MODIFIES:
    //EFFECTS:
    public void moveRight() {
        if (cantMoveRight()) {
            body = new Position(body.getPosX(), body.getPosY());
        } else {
            body = new Position(body.getPosX() + 1, body.getPosY());
            lastBody = new Position(body.getPosX() - 1, body.getPosY());
        }
    }

    //MODIFIES:
    //EFFECTS:
    public boolean cantMoveDown() {
        for (int i = 0; i < walls.getWalls().length; i++) {
            if (body.getPosY() + 1 == walls.makeMap().get(i).getPosY()
                    && body.getPosX() == walls.makeMap().get(i).getPosX()) {
                return true;
            }
        }
        return false;
    }

    //MODIFIES:
    //EFFECTS:
    public boolean cantMoveLeft() {
        for (int i = 0; i < walls.getWalls().length; i++) {
            if (body.getPosX() - 1 == walls.makeMap().get(i).getPosX()
                    && body.getPosY() == walls.makeMap().get(i).getPosY()) {
                return true;
            }
        }
        return false;
    }

    //MODIFIES:
    //EFFECTS:
    public boolean cantMoveRight() {
        for (int i = 0; i < walls.getWalls().length; i++) {
            if (body.getPosX() + 1 == walls.makeMap().get(i).getPosX()
                    && body.getPosY() == walls.makeMap().get(i).getPosY()) {
                return true;
            }
        }
        return false;
    }

    //MODIFIES:
    //EFFECTS:
    public boolean cantMoveUp() {
        for (int i = 0; i < walls.getWalls().length; i++) {
            if (body.getPosY() - 1 == walls.makeMap().get(i).getPosY()
                    && body.getPosX() == walls.makeMap().get(i).getPosX()) {
                return true;
            }
        }
        return false;
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

    public Random getNum() {
        return num;
    }

}
