package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PowerUpsTest {
    private PowerUps pow;

    @BeforeEach
    void runBefore() {
        pow = new PowerUps();
    }

    @Test
    void getterTest() {
        assertEquals(3, pow.getPowerUps().getPosX());
        assertEquals(4, pow.getPowerUps().getPosY());
    }

}
