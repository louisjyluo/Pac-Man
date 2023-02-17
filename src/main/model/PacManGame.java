package model;

import model.Ghost;
import model.PacMan;
import model.Map;

import java.util.ArrayList;

public class PacManGame {
    private Ghost blinky = new Ghost();
    private Ghost pinky = new Ghost();
    private Ghost inky = new Ghost();
    private Ghost clyde = new Ghost();
    private PacMan pacMan = new PacMan();
    private Pellets pellets = new Pellets();
    private Map map = new Map();
    private Boolean ended = false;
    private int maxX;
    private int maxY;
    private int tickPerSec = 4;

    public PacManGame(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public void tick() {
        blinky.move();
        pinky.move();
        inky.move();
        clyde.move();
        if (isPellet()) {
            pellets.eatPellet(pacMan.getPos().getPosX(), pacMan.getPos().getPosY());
        }
        pacMan.move();
        if (hasCollidedWithBlinky() || hasCollidedWithClyde()
                || hasCollidedWithInky() || hasCollidedWithPinky() || noMorePellets()) {
            ended = true;
        }


    }

    public boolean isPellet() {
        for (int i = 0; i < pellets.getPellet().length; i++) {
            if (pacMan.getPos().getPosX() == pellets.makePellets().get(i).getPosX()
                    && pacMan.getPos().getPosY() == pellets.makePellets().get(i).getPosY()) {
                return true;
            }
        }
        return false;
    }

    public boolean noMorePellets() {
        for (int i = 0; i < pellets.getPellet().length; i++) {
            if (!(pellets.getMap().get(i).getPosX() == 32
                    && pellets.getMap().get(i).getPosY() == 32)) {
                return false;
            }
        }
        return true;
    }


    public boolean hasCollidedWithBlinky() {
        int blinkyPosX = blinky.getPos().getPosX();
        int blinkyLastPosX = blinky.getLastBody().getPosX();
        int pacManPosX = pacMan.getPos().getPosX();
        int pacManLastPosX = pacMan.getLastBody().getPosX();
        int blinkyPosY = blinky.getPos().getPosY();
        int blinkyLastPosY = blinky.getLastBody().getPosY();
        int pacManPosY = pacMan.getPos().getPosY();
        int pacManLastPosY = pacMan.getLastBody().getPosY();
        return ((blinkyPosX == pacManPosX && blinkyPosY == pacManPosY)
                || (blinkyPosX == pacManLastPosX &&  blinkyPosY == pacManLastPosY)
                || (blinkyLastPosX == pacManPosX && blinkyLastPosY == pacManLastPosY)
                || (blinkyLastPosY == pacManPosY && blinkyLastPosX == pacManLastPosX));
    }

    public boolean hasCollidedWithPinky() {
        int pinkyPosX = pinky.getPos().getPosX();
        int pinkyLastPosX = pinky.getLastBody().getPosX();
        int pacManPosX = pacMan.getPos().getPosX();
        int pacManLastPosX = pacMan.getLastBody().getPosX();
        int pinkyPosY = pinky.getPos().getPosY();
        int pinkyLastPosY = pinky.getLastBody().getPosY();
        int pacManPosY = pacMan.getPos().getPosY();
        int pacManLastPosY = pacMan.getLastBody().getPosY();
        return ((pinkyPosX == pacManPosX && pinkyPosY == pacManPosY)
                || (pinkyPosX == pacManLastPosX &&  pinkyPosY == pacManLastPosY)
                || (pinkyLastPosX == pacManPosX && pinkyLastPosY == pacManLastPosY)
                || (pinkyLastPosY == pacManPosY && pinkyLastPosX == pacManLastPosX));
    }

    public boolean hasCollidedWithInky() {
        int inkyPosX = inky.getPos().getPosX();
        int inkyLastPosX = inky.getLastBody().getPosX();
        int pacManPosX = pacMan.getPos().getPosX();
        int pacManLastPosX = pacMan.getLastBody().getPosX();
        int inkyPosY = inky.getPos().getPosY();
        int inkyLastPosY = inky.getLastBody().getPosY();
        int pacManPosY = pacMan.getPos().getPosY();
        int pacManLastPosY = pacMan.getLastBody().getPosY();
        return ((inkyPosX == pacManPosX && inkyPosY == pacManPosY)
                || (inkyPosX == pacManLastPosX && inkyPosY == pacManLastPosY)
                || (inkyLastPosX == pacManPosX && inkyLastPosY == pacManLastPosY)
                || (inkyLastPosY == pacManPosY && inkyLastPosX == pacManLastPosX));
    }

    public boolean hasCollidedWithClyde() {
        int clydePosX = clyde.getPos().getPosX();
        int clydeLastPosX = blinky.getLastBody().getPosX();
        int pacManPosX = pacMan.getPos().getPosX();
        int pacManLastPosX = pacMan.getLastBody().getPosX();
        int clydePosY = clyde.getPos().getPosY();
        int clydeLastPosY = clyde.getLastBody().getPosY();
        int pacManPosY = pacMan.getPos().getPosY();
        int pacManLastPosY = pacMan.getLastBody().getPosY();
        return ((clydePosX == pacManPosX && clydePosY == pacManPosY)
                || (clydePosX == pacManLastPosX &&  clydePosY == pacManLastPosY)
                || (clydeLastPosX == pacManPosX && clydeLastPosY == pacManLastPosY)
                || (clydeLastPosY == pacManPosY && clydeLastPosX == pacManLastPosX));
    }


    public Ghost getBlinky() {
        return blinky;
    }

    public Ghost getPinky() {
        return pinky;
    }

    public Ghost getInky() {
        return inky;
    }

    public Ghost getClyde() {
        return clyde;
    }

    public boolean isEnded() {
        return ended;
    }

    public int getTickPerSec() {
        return tickPerSec;
    }

    public PacMan getPacMan() {
        return pacMan;
    }

    public Map getMap() {
        return map;
    }

    public Pellets getPellets() {
        return pellets;
    }



    //Pacman class - represents PacMan, have move() method, wall collision, direction.
    //Pacman direction - represents how PacMan should move,
    //Pacman position - represents a position in the game space for PacMan
    //Ghost class - ghosts move randomly around, leave it up to chance during turns,
    //and wall collision, and weaken state.
    //Map class - the fixed map, currently empty, sets up walls.
    //Pellets class - scoreboard gets increased as coins get collected, game finishes when all coins are collected
    //PowerUp class - Sets up a PowerUp for PacMan, enables the ability to eat ghosts
    //Leaderboard class - the highest scores get recorded on a leaderboard
    //TerminalGame class (for Lanterna)
}
