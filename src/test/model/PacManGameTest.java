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
        assertFalse(game.noMorePellets());
        assertNotEquals(32,game.getPellets().getMap().get(0).getPosX());
        assertNotEquals(32,game.getPellets().getMap().get(0).getPosY());
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                game.getPellets().eatPellet(i,j);
            }
        }
        assertEquals(32,game.getPellets().getMap().get(0).getPosX());
        assertEquals(32,game.getPellets().getMap().get(0).getPosY());
        assertEquals(32,game.getPellets().getMap().get(3).getPosX());
        assertEquals(32,game.getPellets().getMap().get(3).getPosY());
        assertEquals(32,game.getPellets().getMap().get(14).getPosX());
        assertEquals(32,game.getPellets().getMap().get(14).getPosY());
        assertTrue(game.noMorePellets());
        if(game.noMorePellets()) {
            game.tick();
            assertTrue(game.isEnded());
        }

    }


    @Test
    void collisionWithBlinkyTest() {
        blinky.setPos(3,3);
        pacMan.setBody(3,3);
        blinky.setLastBody(3,2);
        pacMan.setLastBody(4,3);
        assertTrue(game.hasCollidedWithBlinky());
        if(game.hasCollidedWithBlinky()) {
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
    void collisionWithPinkyTest() {
        pinky.setPos(3,3);
        pacMan.setBody(3,3);
        pinky.setLastBody(3,2);
        pacMan.setLastBody(4,3);
        assertTrue(game.hasCollidedWithPinky());
        if(game.hasCollidedWithPinky()) {
            game.tick();
            assertTrue(game.isEnded());
        }
        pinky.setPos(3,3);
        pacMan.setBody(3,2);
        pinky.setLastBody(4,3);
        pacMan.setLastBody(3,3);
        assertTrue(game.hasCollidedWithPinky());
        pinky.setPos(3,4);
        pacMan.setBody(3,3);
        pinky.setLastBody(3,3);
        pacMan.setLastBody(2,3);
        assertTrue(game.hasCollidedWithPinky());
        pinky.setPos(7,5);
        pacMan.setBody(3,2);
        pinky.setLastBody(6,1);
        pacMan.setLastBody(2,7);
        assertFalse(game.hasCollidedWithPinky());
    }

    @Test
    void collisionWithInkyTest() {
        inky.setPos(3,3);
        pacMan.setBody(3,3);
        inky.setLastBody(3,2);
        pacMan.setLastBody(4,3);
        assertTrue(game.hasCollidedWithInky());
        if(game.hasCollidedWithInky()) {
            game.tick();
            assertTrue(game.isEnded());
        }
        inky.setPos(3,3);
        pacMan.setBody(3,2);
        inky.setLastBody(4,3);
        pacMan.setLastBody(3,3);
        assertTrue(game.hasCollidedWithInky());
        inky.setPos(3,4);
        pacMan.setBody(3,3);
        inky.setLastBody(3,3);
        pacMan.setLastBody(2,3);
        assertTrue(game.hasCollidedWithInky());
        inky.setPos(7,5);
        pacMan.setBody(3,2);
        inky.setLastBody(6,1);
        pacMan.setLastBody(2,7);
        assertFalse(game.hasCollidedWithInky());
    }

    @Test
    void collisionWithClydeTest() {
        clyde.setPos(3,3);
        pacMan.setBody(3,3);
        clyde.setLastBody(3,2);
        pacMan.setLastBody(4,3);
        assertTrue(game.hasCollidedWithClyde());
        if(game.hasCollidedWithClyde()) {
            game.tick();
            assertTrue(game.isEnded());
        }
        clyde.setPos(3,3);
        pacMan.setBody(3,2);
        clyde.setLastBody(4,3);
        pacMan.setLastBody(3,3);
        assertTrue(game.hasCollidedWithClyde());
        clyde.setPos(3,4);
        pacMan.setBody(3,3);
        clyde.setLastBody(3,3);
        pacMan.setLastBody(2,3);
        assertTrue(game.hasCollidedWithClyde());
        clyde.setPos(7,5);
        pacMan.setBody(3,2);
        clyde.setLastBody(6,1);
        pacMan.setLastBody(2,7);
        assertFalse(game.hasCollidedWithClyde());
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