package model;

import java.security.InvalidKeyException;
import java.util.ArrayList;

public class PacManGame {
    private final Ghost blinky;
    private final Ghost pinky;
    private final Ghost inky;
    private final Ghost clyde;
    private final PacMan pacMan;
    private final ArrayList<Ghost> listOfGhost;
    private final Pellets pellets;
    private final Walls map;
    private Boolean ended;
    private final int tickPerSec;

    //REQUIRES: tickPerSec > 0;
    //MODIFIES: this
    //EFFECTS: sets up a new game
    public PacManGame() {
        blinky = new Ghost();
        pinky = new Ghost();
        inky = new Ghost();
        clyde = new Ghost();
        pacMan = new PacMan();
        pellets = new Pellets();
        listOfGhost = new ArrayList<>();
        listOfGhost.add(blinky);
        listOfGhost.add(inky);
        listOfGhost.add(pinky);
        listOfGhost.add(clyde);
        map = new Walls();
        ended = false;
        tickPerSec = 4;
    }

    //MODIFIES: this
    //EFFECTS: when game is running, each tick will run this code once.
    public void tick() {
        for (int i = 0; i < listOfGhost.size(); i++) {
            listOfGhost.get(i).move();
        }
        pacMan.move();
        if (isPellet()) {
            pellets.eatPellet(pacMan.getPos().getPosX(), pacMan.getPos().getPosY());
        }
        if (hasCollidedWithGhost() || noMorePellets()) {
            ended = true;
        }


    }

    //MODIFIES: this
    //EFFECTS: returns true if pac-man is on a position that contains a pellet.
    public boolean isPellet() {
        for (int i = 0; i < pellets.getPellet().length; i++) {
            if (pacMan.getPos().getPosX() == pellets.makePellets().get(i).getPosX()
                    && pacMan.getPos().getPosY() == pellets.makePellets().get(i).getPosY()) {
                return true;
            }
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: returns true when all pellets are removed from the playable
    // map by setting all pellets to position 32,32.
    public boolean noMorePellets() {
        for (int i = 0; i < pellets.getPellet().length; i++) {
            if (!(pellets.getMap().get(i).getPosX() == 32)) {
                return false;
            }
        }
        return true;
    }

    //MODIFIES: this
    //EFFECTS: return true if Pac man has collided with any of the 4 ghosts.
    public boolean hasCollidedWithGhost() {
        int pacManPosX = pacMan.getPos().getPosX();
        int pacManLastPosX = pacMan.getLastBody().getPosX();
        int pacManPosY = pacMan.getPos().getPosY();
        int pacManLastPosY = pacMan.getLastBody().getPosY();
        for (int i = 0; i < listOfGhost.size(); i++) {
            int ghostPosX = listOfGhost.get(i).getPos().getPosX();
            int ghostLastPosX = listOfGhost.get(i).getLastBody().getPosX();
            int ghostPosY = listOfGhost.get(i).getPos().getPosY();
            int ghostLastPosY = listOfGhost.get(i).getLastBody().getPosY();
            if ((ghostPosX == pacManPosX && ghostPosY == pacManPosY)
                    || (ghostPosX == pacManLastPosX &&  ghostPosY == pacManLastPosY)
                    || (ghostLastPosX == pacManPosX && ghostLastPosY == pacManPosY)) {
                return true;
            }
        }
        return false;
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

    public Walls getMap() {
        return map;
    }

    public Pellets getPellets() {
        return pellets;
    }

    public ArrayList<Ghost> getListOfGhost() {
        return listOfGhost;
    }




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
