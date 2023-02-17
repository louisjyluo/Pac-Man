package model;

import java.util.ArrayList;
import java.util.Random;

public class Ghost {
    private Position body;
    private Position lastBody;
    private Map walls;

    public Ghost() {
        this.body = new Position(13,15);
        this.lastBody = new Position(13,15);
        walls = new Map();
    }

    public void move() {
        Random num = new Random();
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

    public void moveUp() {
        if (cantMoveUp()) {
            body = new Position(body.getPosX(), body.getPosY());
        } else {
            body = new Position(body.getPosX(), body.getPosY() - 1);
            lastBody = new Position(body.getPosX(), body.getPosY() + 1);
        }
    }

    public void moveDown() {
        if (cantMoveDown()) {
            body = new Position(body.getPosX(), body.getPosY());
        } else {
            body = new Position(body.getPosX(), body.getPosY() + 1);
            lastBody = new Position(body.getPosX(), body.getPosY() - 1);
        }
    }

    public void moveLeft() {
        if (cantMoveLeft()) {
            body = new Position(body.getPosX(), body.getPosY());
        } else {
            body = new Position(body.getPosX() - 1, body.getPosY());
            lastBody = new Position(body.getPosX() + 1, body.getPosY());
        }
    }

    public void moveRight() {
        if (cantMoveRight()) {
            body = new Position(body.getPosX(), body.getPosY());
        } else {
            body = new Position(body.getPosX() + 1, body.getPosY());
            lastBody = new Position(body.getPosX() - 1, body.getPosY());
        }
    }

    public boolean cantMoveDown() {
        for (int i = 0; i < walls.getWalls().length; i++) {
            if (body.getPosY() + 1 == walls.makeMap().get(i).getPosY()
                    && body.getPosX() == walls.makeMap().get(i).getPosX()) {
                return true;
            }
        }
        return false;
    }

    public boolean cantMoveLeft() {
        for (int i = 0; i < walls.getWalls().length; i++) {
            if (body.getPosX() - 1 == walls.makeMap().get(i).getPosX()
                    && body.getPosY() == walls.makeMap().get(i).getPosY()) {
                return true;
            }
        }
        return false;
    }

    public boolean cantMoveRight() {
        for (int i = 0; i < walls.getWalls().length; i++) {
            if (body.getPosX() + 1 == walls.makeMap().get(i).getPosX()
                    && body.getPosY() == walls.makeMap().get(i).getPosY()) {
                return true;
            }
        }
        return false;
    }

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

}
