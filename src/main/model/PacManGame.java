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
    private final int width = 650;
    private final int length = 600;
    private Boolean ended = false;
    private final int tickPerSec = 50;
    private int whichGhost = -1;
    private int powerUpDurationTimer = 0;
    private int pacManTimer = 0;
    private int ghostTimer = 0;
    private int lives = 3;
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
    //EFFECTS: resets the entire board
    public void resetGame() {
        pacMan.setBody(10,10);
        pacMan.setLastBody(10,10);
        pacMan.setDirection(Direction.UP);
        powerUpDurationTimer = 0;
        for (Ghost ghost : listOfGhost) {
            ghost.setWeakGhost(false);
            ghost.setLastBody(10,7);
            ghost.setPos(10,7);
        }

        for (int i = 0; i < pellets.getPellet().length; i++) {
            pellets.getPellet()[i][0] = pellets.getBackUp()[i][0];
            pellets.getPellet()[i][1] = pellets.getBackUp()[i][1];
        }

        for (int i = 0; i < power.getPowerUps().length; i++) {
            power.getPowerUps()[i][0] = power.getBackUp()[i][0];
            power.getPowerUps()[i][1] = power.getBackUp()[i][1];
        }
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
        if (ghostTimer > 9 && isWeakGhost()) {
            for (Ghost ghost : listOfGhost) {
                ghost.move();
            }
            ghostTimer = 0;
        }
        if (ghostTimer > 5 && !isWeakGhost()) {
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
        hasCollidedWithGhost();
        if (lives > 0) {
            if (isWeakGhost()) {
                eatWeakGhost();
            } else if (hitGhost()) {
                resetGame();
                lives--;
            }

            if (noMorePellets()) {
                resetGame();
            }
        } else {
            ended = true;
        }
    }

    // MODIFIES: this
    // EFFECTS: checks if PacMan collides with a specific ghost
    public boolean hitGhost() {
        return hasCollidedWithGhost() && !listOfGhost.get(whichGhost).getWeak();
    }

    //MODIFIES: this
    //EFFECTS: if pacMan eats a powerUp, returns true.
    public boolean isPowerUp() {
        for (int i = 0; i < power.getPowerUps().length; i++) {
            if (pacMan.getPos().getPosX() == power.getPowerUps()[i][1]
                    && pacMan.getPos().getPosY() == power.getPowerUps()[i][0]) {

                return true;
            }
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: returns true if pac-man is on a position that contains a pellet.
    public boolean isPellet() {
        for (int i = 0; i < pellets.getPellet().length; i++) {
            if (pacMan.getPos().getPosX() == pellets.getPellet()[i][1]
                    && pacMan.getPos().getPosY() == pellets.getPellet()[i][0]) {
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
            if (!(pellets.getPellet()[i][1] == 32)) {
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
        if (powerUpDurationTimer < 400) {
            if (hasCollidedWithGhost()) {
                hasCollidedWithGhost();
                Ghost thisGhost = listOfGhost.get(whichGhost);
                thatGhost = thisGhost;
                if (!thisGhost.getWeak()) {
                    resetGame();
                    lives--;
                }
                resetGhost();
            }
            powerUpDurationTimer++;
        } else {
            for (Ghost ghost : listOfGhost) {
                ghost.setWeakGhost(false);
            }
            powerUpDurationTimer = 0;
        }

    }

    // MODIFIES: this
    // EFFECTS: resets the ghost to its original state and position
    public void resetGhost() {
        if (thatGhost.getWeak()) {
            thatGhost.setPos(10,7);
            thatGhost.setLastBody(10,7);
            thatGhost.setWeakGhost(false);
            pellets.increaseScoreGhost();
            whichGhost = -1;
        }
    }


    //MODIFIES: this
    //EFFECTS: returns true if any of the ghosts is weak
    public boolean isWeakGhost() {
        for (int i = 0; i < listOfGhost.size(); i++) {
            if (listOfGhost.get(i).getWeak()) {
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: adds a new ghost into the game
    public void addGhosts() {
        Ghost ghost = new Ghost();
        listOfGhost.add(ghost);
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

    public Ghost getThatGhost() {
        return thatGhost;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getLives() {
        return lives;
    }

    public void setPacManTimer(int timer) {
        this.pacManTimer = timer;
    }

}
