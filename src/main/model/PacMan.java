package model;

import java.util.ArrayList;
import model.Ghost;
import model.Map;
import ui.TerminalGame;


public class PacMan {
    private Position body;
    private Direction dir;
    private Position lastBody;
    private Map walls;

    public PacMan() {
        this.body = new Position(13, 18);
        this.dir = Direction.UP;
        lastBody = new Position(13,18);
        walls = new Map();
    }

    public void move() {
        if (dir == Direction.RIGHT) {
            moveRight();
        } else if (dir == Direction.LEFT) {
            moveLeft();
        } else if (dir == Direction.UP) {
            moveUp();
        } else if (dir == Direction.DOWN) {
            moveDown();
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

    public void moveLeft() {
        if (cantMoveLeft()) {
            body = new Position(body.getPosX(), body.getPosY());
        } else {
            body = new Position(body.getPosX() - 1, body.getPosY());
            lastBody = new Position(body.getPosX() + 1, body.getPosY());
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

    public Position getPos() {
        return body;
    }

    public void setDirection(Direction direction) {
        this.dir = direction;
    }

    public Position getLastBody() {
        return lastBody;
    }

}
