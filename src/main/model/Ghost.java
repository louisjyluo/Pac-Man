package model;

import java.util.ArrayList;
import java.util.Random;

public class Ghost {
    private Position body;

    public Ghost() {
        this.body = new Position(20,7);
    }

    public void move() {
        Random num = new Random();
        if (moveAtBoundaries()) {
            moveAtBoundaries();
        }
        if (num.nextDouble() < 0.25) {
            body = new Position(body.getPosX(), body.getPosY() + 1);
        } else if (num.nextDouble() >= 0.25 && num.nextDouble() < 0.5) {
            body = new Position(body.getPosX(), body.getPosY() - 1);
        } else if (num.nextDouble() >= 0.5 && num.nextDouble() < 0.75) {
            body = new Position(body.getPosX() + 1, body.getPosY());
        } else {
            body = new Position(body.getPosX() - 1, body.getPosY());
        }

    }

    public boolean moveAtBoundaries() {
        if (body.getPosX() == 40) {
            body = new Position(body.getPosX() - 1, body.getPosY());
            return true;
        }
        if (body.getPosY() == 30) {
            body = new Position(body.getPosX(), body.getPosY() - 1);
            return true;
        }
        if (body.getPosX() == 0) {
            body = new Position(body.getPosX() + 1, body.getPosY());
            return true;
        }
        if (body.getPosY() == 0) {
            body = new Position(body.getPosX(), body.getPosY() + 1);
            return true;
        }
        return false;
    }



    public Position getPos() {
        return body;
    }

}
