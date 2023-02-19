package model;

public class PacManGame {
    private final Ghost blinky;
    private final Ghost pinky;
    private final Ghost inky;
    private final Ghost clyde;
    private final PacMan pacMan;
    private final Pellets pellets;
    private final Map map;
    private Boolean ended;
    private final int tickPerSec;

    public PacManGame() {
        blinky = new Ghost();
        pinky = new Ghost();
        inky = new Ghost();
        clyde = new Ghost();
        pacMan = new PacMan();
        pellets = new Pellets();
        map = new Map();
        ended = false;
        tickPerSec = 4;
    }

    //MODIFIES: this
    //EFFECTS: when game is running, each tick will run this code once.
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
            if (!(pellets.getMap().get(i).getPosX() == 32
                    && pellets.getMap().get(i).getPosY() == 32)) {
                return false;
            }
        }
        return true;
    }

    //MODIFIES: this
    //EFFECTS: return true if Pac man has collided with Blinky.
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
                || (blinkyLastPosX == pacManPosX && blinkyLastPosY == pacManLastPosY));
    }

    //MODIFIES: this
    //EFFECTS: return true if Pac man has collided with Pinky.
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
                || (pinkyLastPosX == pacManPosX && pinkyLastPosY == pacManLastPosY));
    }

    //MODIFIES: this
    //EFFECTS: return true if Pac man has collided with Inky.
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
                || (inkyLastPosX == pacManPosX && inkyLastPosY == pacManLastPosY));
    }

    //MODIFIES: this
    //EFFECTS: return true if Pac man has collided with Clyde.
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
                || (clydeLastPosX == pacManPosX && clydeLastPosY == pacManLastPosY));
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
