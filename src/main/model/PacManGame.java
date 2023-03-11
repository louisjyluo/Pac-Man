package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.ArrayList;

//The class that puts everything together and creates the game itself
public class PacManGame implements Writable {
    private final Ghost blinky;
    private final Ghost pinky;
    private final Ghost inky;
    private final Ghost clyde;
    private final PacMan pacMan;
    private final ArrayList<Ghost> listOfGhost;
    private final Pellets pellets;
    private final Walls map;
    private final PowerUps power;
    private Boolean ended = false;
    private final int tickPerSec = 50;
    private int whichGhost = -1;
    private int powerUpDurationTimer = 0;
    private int pacManTimer = 0;
    private int ghostTimer = 0;
    private Ghost thatGhost;

    //REQUIRES: tickPerSec > 0;
    //MODIFIES: this
    //EFFECTS: sets up a new game
    public PacManGame() {
        blinky = new Ghost();
        pinky = new Ghost();
        inky = new Ghost();
        clyde = new Ghost();
        pacMan = new PacMan();
        listOfGhost = new ArrayList<>();
        listOfGhost.add(blinky);
        listOfGhost.add(inky);
        listOfGhost.add(pinky);
        listOfGhost.add(clyde);
        map = new Walls();
        pellets = new Pellets();
        power = new PowerUps();
    }

    //MODIFIES: this
    //EFFECTS: when game is running, each tick will run this code once.
    public void tick() {
        handleGhostMovement();
        handlePacManMovement();
        if (isPellet()) {
            pellets.eatPellet(pacMan.getPos().getPosX(), pacMan.getPos().getPosY());
        }
        eatPowerUpToWeakenGhost();
        checkEndGame();

    }

    //MODIFIES: this
    //EFFECTS: handles the movement of PacMan per tick
    public void handlePacManMovement() {
        if (pacManTimer > 7) {
            pacMan.move();
            pacManTimer = 0;
        } else {
            pacManTimer++;
        }
    }

    //MODIFIES: this
    //EFFECTS: handles the movement of ghosts per tick
    public void handleGhostMovement() {
        if (ghostTimer > 5 && !isWeakGhost()) {
            for (Ghost ghost : listOfGhost) {
                ghost.move();
            }
            ghostTimer = 0;
        } else if (ghostTimer > 9 && isWeakGhost()) {
            for (Ghost ghost : listOfGhost) {
                ghost.move();
            }
            ghostTimer = 0;
        } else {
            ghostTimer++;
        }
    }

    //MODIFIES: this
    //EFFECTS: if PacMan eats a powerUp, all ghosts become weak and starts the timer for the duration of the powerUp
    public void eatPowerUpToWeakenGhost() {
        if (isPowerUp()) {
            power.eatPowerUp(pacMan.getPos().getPosX(), pacMan.getPos().getPosY());
            powerUpDurationTimer = 0;
            for (Ghost ghost : listOfGhost) {
                ghost.setWeakGhost(true);
            }

        }
    }

    //MODIFIES: this
    //EFFECTS: check when the game ends and handles weak ghost
    public void checkEndGame() {
        if (isWeakGhost()) {
            eatWeakGhost();

        } else if (hasCollidedWithGhost()) {
            ended = true;
        }

        if (noMorePellets()) {
            ended = true;
        }
    }


    //MODIFIES: this
    //EFFECTS: if pacMan eats a powerUp, returns true.
    public boolean isPowerUp() {
        for (int i = 0; i < power.getPowerUps().length; i++) {
            if (pacMan.getPos().getPosX() == power.makePowerUps().get(i).getPosX()
                    && pacMan.getPos().getPosY() == power.makePowerUps().get(i).getPosY()) {

                return true;
            }
        }
        return false;
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
        int pacManPosY = pacMan.getPos().getPosY();
        for (int i = 0; i < listOfGhost.size(); i++) {
            int ghostPosX = listOfGhost.get(i).getPos().getPosX();
            int ghostLastPosX = listOfGhost.get(i).getLastBody().getPosX();
            int ghostPosY = listOfGhost.get(i).getPos().getPosY();
            int ghostLastPosY = listOfGhost.get(i).getLastBody().getPosY();
            if ((ghostPosX == pacManPosX && ghostPosY == pacManPosY)
                    || (ghostLastPosX == pacManPosX && ghostLastPosY == pacManPosY)) {
                whichGhost = i;
                return true;
            }
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: handles PacMan eating weak ghosts, and handles the duration of the powerUp.
    public void eatWeakGhost() {
        if (powerUpDurationTimer < 350) {
            if (hasCollidedWithGhost()) {
                hasCollidedWithGhost();
                Ghost thisGhost = listOfGhost.get(whichGhost);
                if (thisGhost.getWeak()) {
                    thisGhost.setPos(10,7);
                    thisGhost.setLastBody(10,7);
                    thisGhost.setWeakGhost(false);
                    pellets.increaseScoreGhost();
                    thatGhost = thisGhost;
                    whichGhost = -1;
                }
            }
            powerUpDurationTimer++;
        } else {
            for (Ghost ghost : listOfGhost) {
                ghost.setWeakGhost(false);
            }
            powerUpDurationTimer = 0;
        }

    }


    //MODIFIES: this
    //EFFECTS: returns true if any of the ghosts is weak
    public boolean isWeakGhost() {
        return (listOfGhost.get(0).getWeak()
               || listOfGhost.get(1).getWeak()
               || listOfGhost.get(2).getWeak()
               || listOfGhost.get(3).getWeak());
    }

    //MODIFIES: this
    //EFFECTS: parses the entire game into a Json file
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("ghosts", ghostsToJson());
        json.put("pacMan",pacMan.toJson());
        json.put("power up", power.toJson());
        json.put("pellet", pellets.toJson());
        json.put("timer", powerUpDurationTimer);
        return json;
    }

    //MODIFIES: this
    //EFFECTS: parses each ghost into Json
    private JSONArray ghostsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Ghost g : listOfGhost) {
            jsonArray.put(g.toJson());
        }
        return jsonArray;
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

    public PowerUps getPower() {
        return power;
    }

    public ArrayList<Ghost> getListOfGhost() {
        return listOfGhost;
    }

    public void setPowerUpDurationTimer(int timer) {
        powerUpDurationTimer = timer;
    }

    public int getPowerUpDurationTimer() {
        return powerUpDurationTimer;
    }

    public void setGhostTimer(int timer) {
        ghostTimer = timer;
    }

    public int getGhostTimer() {
        return ghostTimer;
    }

}
