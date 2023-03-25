package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//Test for PowerUp Class
public class PowerUpsTest {
    private PowerUps pow;

    @BeforeEach
    void runBefore() {
        pow = new PowerUps();
    }

    @Test
    void getterTest() {
        assertEquals(2, pow.getPowerUps()[0][1]);
    }

    @Test
    void eatPowerUpTest() {
        pow.eatPowerUp(4,13);
        int posX = pow.getPowerUps()[2][1];
        int posY = pow.getPowerUps()[2][0];
        assertEquals(32, posX);
        assertEquals(32, posY);
        pow.eatPowerUp(18,1);
         posX = pow.getPowerUps()[1][1];
         posY = pow.getPowerUps()[1][0];
        assertEquals(32, posX);
        assertEquals(32, posY);
        pow.eatPowerUp(2,1);
         posX = pow.getPowerUps()[1][1];
         posY = pow.getPowerUps()[1][0];
        assertEquals(32, posX);
        assertEquals(32, posY);
        pow.eatPowerUp(16,13);
         posX = pow.getPowerUps()[3][1];
         posY = pow.getPowerUps()[3][0];
        assertEquals(32, posX);
        assertEquals(32, posY);


    }



}
