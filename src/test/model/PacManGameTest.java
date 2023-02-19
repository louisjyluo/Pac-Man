package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PacManGameTest {
    private PacManGame game;
    private Ghost blinky;
    private Ghost pinky;
    private Ghost inky;
    private Ghost clyde;
    private PacMan pacMan;

    @BeforeEach
    void runBefore() {
        game = new PacManGame();
        blinky = game.getBlinky();
        pinky = game.getPinky();
        inky = game.getInky();
        clyde = game.getClyde();
        pacMan = game.getPacMan();
    }

    @Test
    void isPelletTest() {
        assertTrue(game.isPellet());
        game.tick();
        assertFalse(game.isPellet());
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 18; j++) {
                game.getPellets().eatPellet(i,j);
            }
        }
        if(game.noMorePellets()) {
            assertTrue(game.isEnded());
        }
    }


    @Test
    void collisionTest() {
        blinky.setPos(3,3);
        pacMan.setBody(3,3);
        blinky.setLastBody(3,2);
        pacMan.setLastBody(4,3);
        assertTrue(game.hasCollidedWithBlinky());
        if(game.hasCollidedWithBlinky()) {
            game.tick();
            assertTrue(game.isEnded());
        }
        if(game.hasCollidedWithPinky()) {
            game.tick();
            assertTrue(game.isEnded());
        }
        if(game.hasCollidedWithInky()) {
            game.tick();
            assertTrue(game.isEnded());
        }
        if(game.hasCollidedWithClyde()) {
            game.tick();
            assertTrue(game.isEnded());
        }
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

    @Test
    void gettersTest() {
        assertEquals(new PacMan().getPos().getPosX(), game.getPacMan().getPos().getPosX());
        assertEquals(new PacMan().getPos().getPosY(), game.getPacMan().getPos().getPosY());
        assertEquals(new Ghost().getPos().getPosX(), blinky.getPos().getPosX());
        assertEquals(new Ghost().getPos().getPosY(), blinky.getPos().getPosY());
        assertEquals(new Ghost().getPos().getPosX(), pinky.getPos().getPosX());
        assertEquals(new Ghost().getPos().getPosY(), pinky.getPos().getPosY());
        assertEquals(new Ghost().getPos().getPosX(), inky.getPos().getPosX());
        assertEquals(new Ghost().getPos().getPosY(), inky.getPos().getPosY());
        assertEquals(new Ghost().getPos().getPosX(), clyde.getPos().getPosX());
        assertEquals(new Ghost().getPos().getPosY(), clyde.getPos().getPosY());
        assertEquals(4, game.getTickPerSec());
        assertEquals(165,game.getMap().makeMap().size());
        assertEquals(111,game.getPellets().makePellets().size());
    }

}