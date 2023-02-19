package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PacManGameTest {
    private PacManGame game;
    private Ghost blinky;
    private PacMan pacMan;

    @BeforeEach
    void runBefore() {
        game = new PacManGame();
        blinky = game.getBlinky();
        pacMan = game.getPacMan();
    }

    @Test
    void constructorTest() {
        assertEquals(new PacMan().getPos().getPosX(), game.getPacMan().getPos().getPosX());
        assertEquals(new PacMan().getPos().getPosY(), game.getPacMan().getPos().getPosY());
        assertEquals(new Ghost().getPos().getPosX(), game.getBlinky().getPos().getPosX());
        assertEquals(new Ghost().getPos().getPosY(), game.getBlinky().getPos().getPosY());
    }

    @Test
    void isPelletTest() {
        assertTrue(game.isPellet());
        game.tick();
        assertFalse(game.isPellet());
    }


    @Test
    void collisionTest() {
        blinky.setPos(3,3);
        pacMan.setBody(3,3);
        blinky.setLastBody(3,2);
        pacMan.setLastBody(4,3);
        assertTrue(game.hasCollidedWithBlinky());
        blinky.setPos(3,3);
        pacMan.setBody(3,2);
        blinky.setLastBody(4,3);
        pacMan.setLastBody(3,3);
        assertTrue(game.hasCollidedWithBlinky());
        blinky.setPos(3,4);
        pacMan.setBody(3,3);
        blinky.setLastBody(3,3);
        pacMan.setLastBody(2,3);
        assertTrue(game.hasCollidedWithBlinky());
        blinky.setPos(7,5);
        pacMan.setBody(3,2);
        blinky.setLastBody(6,1);
        pacMan.setLastBody(2,7);
        assertFalse(game.hasCollidedWithBlinky());
    }

}