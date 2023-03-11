package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//Test for PowerUp Class
public class PowerUpsTest {
    private PowerUps pow;
    private ArrayList<Position> map;

    @BeforeEach
    void runBefore() {
        pow = new PowerUps();
        map = pow.makePowerUps();
    }

    @Test
    void getterTest() {
        assertEquals(2, pow.getPowerUps()[0][1]);
    }

    @Test
    void makePowerUpTest() {
        assertEquals(4, map.size());
        int posX = map.get(0).getPosX();
        int posY = map.get(0).getPosY();
        assertEquals(2, posX);
        assertEquals(1, posY);
    }

    @Test
    void eatPowerUpTest() {
        pow.eatPowerUp(2,1);
        int posX = map.get(0).getPosX();
        int posY = map.get(0).getPosY();
        assertEquals(33, posX);
        assertEquals(33, posY);

    }



}
