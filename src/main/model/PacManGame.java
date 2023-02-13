package model;

import model.Ghost;
import model.PacMan;

import java.util.ArrayList;

public class PacManGame {
    private Ghost blinky = new Ghost();
    private Ghost pinky = new Ghost();
    private Ghost inky = new Ghost();
    private Ghost clyde = new Ghost();
    private PacMan pacMan = new PacMan();
    private Pellets pellets = new Pellets();
    private Boolean ended = false;
    private int score = 0;
    private int maxX;
    private int maxY;
    private int tickPerSec = 5;

    public PacManGame(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public void tick() {
        pacMan.move();
        blinky.move();
        pinky.move();
        inky.move();
        clyde.move();
        increaseScore();
        if (hasCollidedWithBlinky() || hasCollidedWithClyde()
                || hasCollidedWithInky() || hasCollidedWithPinky() || checkBoundaries()) {
            ended = true;
            return;
        }
    }


    public boolean checkBoundaries() {
        if (pacMan.getPos().getPosX() == maxX) {
            return true;
        }
        if (pacMan.getPos().getPosY() == maxY) {
            return true;
        }
        if (pacMan.getPos().getPosX() == 0) {
            return true;
        }
        if (pacMan.getPos().getPosY() == 0) {
            return true;
        }
        return false;
    }

    public boolean hasCollidedWithBlinky() {
        return (blinky.getPos().getPosX() == pacMan.getPos().getPosX()
                && blinky.getPos().getPosY() == pacMan.getPos().getPosY());
    }

    public boolean hasCollidedWithPinky() {
        return (pinky.getPos().getPosX() == pacMan.getPos().getPosX()
                && pinky.getPos().getPosY() == pacMan.getPos().getPosY());
    }

    public boolean hasCollidedWithInky() {
        return (inky.getPos().getPosX() == pacMan.getPos().getPosX()
                && inky.getPos().getPosY() == pacMan.getPos().getPosY());
    }

    public boolean hasCollidedWithClyde() {
        return (clyde.getPos().getPosX() == pacMan.getPos().getPosX()
                && clyde.getPos().getPosY() == pacMan.getPos().getPosY());
    }

    public int increaseScore() {
        if (pacMan.getPos().equals(pellets.getPellet())) {
            int score = pellets.getScore();
            return score += pellets.getScore() + 20;
        }
        return pellets.getScore();
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

    public int getScore() {
        return score;
    }


    //Pacman class - represents PacMan, have move() method, wall collision, direction, and remove body.
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
