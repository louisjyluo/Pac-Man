package model;

//Represents the power ups that can be collected by PacMan for a boost.
public class PowerUps {
    private Position powerUps;

    public PowerUps() {
        powerUps = new Position(3,4);
    }

    public Position getPowerUps() {
        return powerUps;
    }
}
