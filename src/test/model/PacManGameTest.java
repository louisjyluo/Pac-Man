package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//Test for PacManGame Class
class PacManGameTest {
    private PacManGame game;
    private ArrayList<Ghost> listOfGhost;
    private PacMan pacMan;

    @BeforeEach
    void runBefore() {
        game = new PacManGame();
        listOfGhost = game.getListOfGhost();
        pacMan = game.getPacMan();
    }

    @Test
    void isPelletTest() {
        pacMan.setBody(12,10);
        assertTrue(game.isPellet());
        game.tick();
        assertFalse(game.isPellet());
        assertFalse(game.noMorePellets());
        assertNotEquals(32,game.getPellets().getPellet()[0][1]);
        assertNotEquals(32,game.getPellets().getPellet()[0][0]);
        for (int i = 0; i < game.getPellets().getPellet().length; i++) {
            game.getPellets().getPellet()[i][1] = 32;
            game.getPellets().getPellet()[i][0] = 32;
        }
        assertEquals(32,game.getPellets().getPellet()[0][1]);
        assertEquals(32,game.getPellets().getPellet()[3][1]);
        assertEquals(32,game.getPellets().getPellet()[3][0]);
        assertEquals(32,game.getPellets().getPellet()[14][1]);
        assertEquals(32,game.getPellets().getPellet()[14][0]);
        assertTrue(game.noMorePellets());
    }

    @Test
    void resetGameTest() {
        game.resetGame();
        assertEquals(10, pacMan.getPos().getPosX());
        assertEquals(10, pacMan.getPos().getPosY());
        assertEquals(10, listOfGhost.get(0).getPos().getPosX());
        assertEquals(7, listOfGhost.get(0).getPos().getPosY());
        assertEquals(10, listOfGhost.get(1).getPos().getPosX());
        assertEquals(7, listOfGhost.get(1).getPos().getPosY());
        assertEquals(10, listOfGhost.get(2).getPos().getPosX());
        assertEquals(7, listOfGhost.get(2).getPos().getPosY());
        assertEquals(0, game.getPowerUpDurationTimer());
        for (int i = 0; i < game.getPellets().getPellet().length; i++) {
            assertNotEquals(32, game.getPellets().getPellet()[i][1]);
            assertNotEquals(32, game.getPellets().getPellet()[i][0]);
        }
        for (int i = 0; i < game.getPower().getPowerUps().length; i++) {
            assertNotEquals(32, game.getPower().getPowerUps()[i][1]);
            assertNotEquals(32, game.getPower().getPowerUps()[i][0]);
        }
    }

    @Test
    void livesTest() {
        assertEquals(3, game.getLives());
        Ghost blinky = listOfGhost.get(0);
        blinky.setPos(10,10);
        blinky.setWeakGhost(true);
        game.checkEndGame();
        assertEquals(10, blinky.getPos().getPosX());
        assertEquals(7, blinky.getPos().getPosY());
        blinky.setPos(10,10);
        game.eatWeakGhost();
        assertEquals(2, game.getLives());
        blinky.setPos(10,10);
        game.eatWeakGhost();
        assertEquals(1, game.getLives());
        for (int i = 0; i < game.getPellets().getPellet().length; i++) {
            game.getPellets().getPellet()[i][1] = 32;
            game.getPellets().getPellet()[i][0] = 32;
        }
        assertTrue(game.noMorePellets());
        game.checkEndGame();
        assertEquals(10, blinky.getPos().getPosX());
        assertEquals(7, blinky.getPos().getPosY());
        blinky.setPos(10,10);
        game.eatWeakGhost();
        assertEquals(0, game.getLives());
        game.tick();
        assertTrue(game.isEnded());
    }

    @Test
    void handlePacManMovementTest() {
        pacMan.setBody(10,10);
        pacMan.setDirection(Direction.LEFT);
        game.setPacManTimer(8);
        game.tick();
        assertEquals(9, pacMan.getPos().getPosX());
    }

    @Test
    void handleGhostMovementTest() {
        game.setGhostTimer(10);
        for(Ghost ghost : listOfGhost) {
            ghost.setWeakGhost(true);
        }
        assertTrue(game.getGhostTimer() > 9);
        assertTrue(game.isWeakGhost());
        game.handleGhostMovement();

        game.setGhostTimer(10);
        for(Ghost ghost : listOfGhost) {
            ghost.setWeakGhost(false);
        }
        assertTrue(game.getGhostTimer() > 5);
        assertFalse(game.isWeakGhost());
        game.handleGhostMovement();

        game.setGhostTimer(6);
        for(Ghost ghost : listOfGhost) {
            ghost.setWeakGhost(false);
        }
        assertTrue(game.getGhostTimer() > 5);
        assertFalse(game.isWeakGhost());
        game.handleGhostMovement();

        game.setGhostTimer(6);
        for(Ghost ghost : listOfGhost) {
            ghost.setWeakGhost(true);
        }
        assertTrue(game.getGhostTimer() > 5);
        assertTrue(game.isWeakGhost());
        game.handleGhostMovement();

        game.setGhostTimer(3);
        for(Ghost ghost : listOfGhost) {
            ghost.setWeakGhost(false);
        }
        assertFalse(game.getGhostTimer() > 5);
        assertFalse(game.isWeakGhost());
        game.handleGhostMovement();

        game.setGhostTimer(3);
        for(Ghost ghost : listOfGhost) {
            ghost.setWeakGhost(true);
        }
        assertFalse(game.getGhostTimer() > 5);
        assertTrue(game.isWeakGhost());
        game.handleGhostMovement();
    }



    @Test
    void eatPowerUpToWeakenGhostTest() {
        pacMan.setBody(2,1);
        assertTrue(game.isPowerUp());
        game.eatPowerUpToWeakenGhost();
        assertEquals(0, game.getPowerUpDurationTimer());
        for(Ghost ghost : listOfGhost) {
            assertTrue(ghost.getWeak());
        }
    }

    @Test
    void removeGhostTest() {
        game.removeGhosts();
        assertEquals(4, game.getListOfGhost().size());
        game.addGhosts();
        game.addGhosts();
        game.removeGhosts();
        assertEquals(5, game.getListOfGhost().size());
    }

    @Test
    void EndStateTest() {
        pacMan.setBody(2,1);
        listOfGhost.get(0).setPos(2,1);
        game.checkEndGame();
        assertEquals(2,game.getLives());
        assertEquals(10, pacMan.getPos().getPosX());
        assertEquals(10, pacMan.getPos().getPosY());
        pacMan.setBody(2,1);
        listOfGhost.get(0).setPos(2,1);
        listOfGhost.get(0).setWeakGhost(true);
        game.eatWeakGhost();
        assertEquals(10, listOfGhost.get(0).getPos().getPosX());
        assertEquals(7, listOfGhost.get(0).getPos().getPosY());
        game.setPowerUpDurationTimer(401);
        game.tick();
        pacMan.setBody(2,1);
        listOfGhost.get(0).setPos(2,1);
        listOfGhost.get(0).setWeakGhost(false);
        game.checkEndGame();
        assertEquals(1,game.getLives());
        assertEquals(10, pacMan.getPos().getPosX());
        assertEquals(10, pacMan.getPos().getPosY());
        pacMan.setBody(2,1);
        listOfGhost.get(0).setPos(2,1);
        listOfGhost.get(0).setWeakGhost(true);
        game.checkEndGame();
        assertEquals(1,game.getLives());
        assertEquals(2, pacMan.getPos().getPosX());
        assertEquals(1, pacMan.getPos().getPosY());
        assertEquals(10, listOfGhost.get(0).getPos().getPosX());
        assertEquals(7, listOfGhost.get(0).getPos().getPosY());
        listOfGhost.get(0).setWeakGhost(true);
        game.checkEndGame();
        assertTrue(listOfGhost.get(0).getWeak());
        pacMan.setBody(2,1);
        listOfGhost.get(0).setPos(5,5);
        listOfGhost.get(0).setWeakGhost(true);
        game.checkEndGame();
        assertEquals(1,game.getLives());
        assertEquals(2, pacMan.getPos().getPosX());
        assertEquals(1, pacMan.getPos().getPosY());
        assertEquals(5, listOfGhost.get(0).getPos().getPosX());
        assertEquals(5, listOfGhost.get(0).getPos().getPosY());
        pacMan.setBody(2,1);
        listOfGhost.get(0).setPos(5,5);
        listOfGhost.get(0).setWeakGhost(false);
        game.checkEndGame();
        assertEquals(1,game.getLives());
        assertEquals(2, pacMan.getPos().getPosX());
        assertEquals(1, pacMan.getPos().getPosY());
        assertEquals(5, listOfGhost.get(0).getPos().getPosX());
        assertEquals(5, listOfGhost.get(0).getPos().getPosY());
    }

    @Test
    void hitGhostTest() {
        pacMan.setBody(2,1);
        listOfGhost.get(0).setPos(2,1);
        assertTrue(game.hitGhost());
        pacMan.setBody(2,1);
        listOfGhost.get(0).setPos(2,1);
        listOfGhost.get(0).setWeakGhost(true);
        assertFalse(game.hitGhost());
        pacMan.setBody(2,1);
        listOfGhost.get(0).setPos(5,5);
        listOfGhost.get(0).setWeakGhost(false);
        assertFalse(game.hitGhost());
        pacMan.setBody(2,1);
        listOfGhost.get(0).setPos(5,5);
        listOfGhost.get(0).setWeakGhost(true);
        assertFalse(game.hitGhost());
    }

    @Test
    void isPowerUpTest() {
        pacMan.setBody(2,1);
        assertTrue(game.isPowerUp());
        game.tick();
        assertFalse(game.isPowerUp());
        assertEquals(32,game.getPower().getPowerUps()[0][1]);
        assertEquals(32,game.getPower().getPowerUps()[0][0]);
        pacMan.setBody(4,1);
        assertFalse(game.isPowerUp());
        game.tick();
        assertFalse(game.isPowerUp());
        pacMan.setBody(5,5);
        assertFalse(game.isPowerUp());
        game.tick();
        assertFalse(game.isPowerUp());
        pacMan.setBody(18, 4);
        assertFalse(game.isPowerUp());
        game.tick();
        assertFalse(game.isPowerUp());
        pacMan.setBody(18, 1);
        if (pacMan.getPos().getPosX() == game.getPower().getPowerUps()[1][1]
                && pacMan.getPos().getPosY() == game.getPower().getPowerUps()[1][0]) {
            assertTrue(game.isPowerUp());
            game.tick();
            assertFalse(game.isPowerUp());
        }
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                game.getPower().eatPowerUp(i,j);
            }
        }
        assertEquals(32,game.getPower().getPowerUps()[0][1]);
        assertEquals(32,game.getPower().getPowerUps()[1][1]);
        assertEquals(32,game.getPower().getPowerUps()[2][1]);
        assertEquals(32,game.getPower().getPowerUps()[3][1]);
        assertEquals(32,game.getPower().getPowerUps()[0][0]);
        assertEquals(32,game.getPower().getPowerUps()[1][0]);
        assertEquals(32,game.getPower().getPowerUps()[2][0]);
        assertEquals(32,game.getPower().getPowerUps()[3][0]);
    }

    @Test
    void eatGhostSuccessfulTest() {
        listOfGhost.get(0).setWeakGhost(true);
        listOfGhost.get(0).setPos(2,1);
        pacMan.setBody(2,1);
        assertTrue(game.hasCollidedWithGhost());
        game.hasCollidedWithGhost();
        game.eatWeakGhost();
        Ghost thisGhost = game.getThatGhost();
        thisGhost.setWeakGhost(true);
        thisGhost.setPos(15,15);
        assertTrue(thisGhost.getWeak());
        game.resetGhost();
        assertFalse(thisGhost.getWeak());
        assertEquals(10 ,thisGhost.getPos().getPosX());
        assertEquals(7,thisGhost.getPos().getPosY());
        assertFalse(thisGhost.getWeak());
        assertEquals(1, game.getPowerUpDurationTimer());
        thisGhost.setWeakGhost(false);
        thisGhost.setPos(15,15);
        assertFalse(thisGhost.getWeak());
        game.resetGhost();
        assertEquals(15 ,thisGhost.getPos().getPosX());
        assertEquals(15,thisGhost.getPos().getPosY());
        assertFalse(thisGhost.getWeak());

    }

    @Test
    void eatGhostFailedTest() {
        listOfGhost.get(0).setWeakGhost(true);
        listOfGhost.get(0).setPos(2,1);
        pacMan.setBody(10,10);
        game.eatWeakGhost();
        assertEquals(2 ,listOfGhost.get(0).getPos().getPosX());
        assertEquals(1,listOfGhost.get(0).getPos().getPosY());
        assertTrue(listOfGhost.get(0).getWeak());
    }

    @Test
    void eatGhostOnceThenDiesTest() {
        listOfGhost.get(0).setWeakGhost(true);
        listOfGhost.get(0).setPos(2,1);
        pacMan.setBody(2,1);
        game.eatWeakGhost();
        assertEquals(10 ,listOfGhost.get(0).getPos().getPosX());
        assertEquals(7,listOfGhost.get(0).getPos().getPosY());
        assertFalse(listOfGhost.get(0).getWeak());
        listOfGhost.get(0).setPos(2,1);
        game.eatWeakGhost();
        assertEquals(2, game.getLives());
    }

    @Test
    void powerUpTimerTimeOutTest() {
        listOfGhost.get(0).setWeakGhost(true);
        listOfGhost.get(0).setPos(2,1);
        pacMan.setBody(10,10);
        for(int i = 0; i < 400; i++){
            game.eatWeakGhost();
        }
        assertTrue(listOfGhost.get(0).getWeak());
        game.eatWeakGhost();
        assertFalse(listOfGhost.get(0).getWeak());
    }


    @Test
    void isWeakGhostTest() {
        listOfGhost.get(0).setWeakGhost(false);
        listOfGhost.get(1).setWeakGhost(false);
        listOfGhost.get(2).setWeakGhost(false);
        listOfGhost.get(3).setWeakGhost(false);
        assertFalse(game.isWeakGhost());
        listOfGhost.get(0).setWeakGhost(false);
        listOfGhost.get(1).setWeakGhost(false);
        listOfGhost.get(2).setWeakGhost(false);
        listOfGhost.get(3).setWeakGhost(true);
        assertTrue(game.isWeakGhost());
        listOfGhost.get(0).setWeakGhost(false);
        listOfGhost.get(1).setWeakGhost(false);
        listOfGhost.get(2).setWeakGhost(true);
        listOfGhost.get(3).setWeakGhost(false);
        assertTrue(game.isWeakGhost());
        listOfGhost.get(0).setWeakGhost(false);
        listOfGhost.get(1).setWeakGhost(false);
        listOfGhost.get(2).setWeakGhost(true);
        listOfGhost.get(3).setWeakGhost(true);
        assertTrue(game.isWeakGhost());
        listOfGhost.get(0).setWeakGhost(false);
        listOfGhost.get(1).setWeakGhost(true);
        listOfGhost.get(2).setWeakGhost(false);
        listOfGhost.get(3).setWeakGhost(false);
        assertTrue(game.isWeakGhost());
        listOfGhost.get(0).setWeakGhost(false);
        listOfGhost.get(1).setWeakGhost(true);
        listOfGhost.get(2).setWeakGhost(false);
        listOfGhost.get(3).setWeakGhost(true);
        assertTrue(game.isWeakGhost());
        listOfGhost.get(0).setWeakGhost(false);
        listOfGhost.get(1).setWeakGhost(true);
        listOfGhost.get(2).setWeakGhost(true);
        listOfGhost.get(3).setWeakGhost(false);
        assertTrue(game.isWeakGhost());
        listOfGhost.get(0).setWeakGhost(false);
        listOfGhost.get(1).setWeakGhost(true);
        listOfGhost.get(2).setWeakGhost(true);
        listOfGhost.get(3).setWeakGhost(true);
        assertTrue(game.isWeakGhost());
        listOfGhost.get(0).setWeakGhost(true);
        listOfGhost.get(1).setWeakGhost(false);
        listOfGhost.get(2).setWeakGhost(false);
        listOfGhost.get(3).setWeakGhost(false);
        assertTrue(game.isWeakGhost());
        listOfGhost.get(0).setWeakGhost(true);
        listOfGhost.get(1).setWeakGhost(false);
        listOfGhost.get(2).setWeakGhost(false);
        listOfGhost.get(3).setWeakGhost(true);
        assertTrue(game.isWeakGhost());
        listOfGhost.get(0).setWeakGhost(true);
        listOfGhost.get(1).setWeakGhost(false);
        listOfGhost.get(2).setWeakGhost(true);
        listOfGhost.get(3).setWeakGhost(false);
        assertTrue(game.isWeakGhost());
        listOfGhost.get(0).setWeakGhost(true);
        listOfGhost.get(1).setWeakGhost(false);
        listOfGhost.get(2).setWeakGhost(true);
        listOfGhost.get(3).setWeakGhost(true);
        assertTrue(game.isWeakGhost());
        listOfGhost.get(0).setWeakGhost(true);
        listOfGhost.get(1).setWeakGhost(true);
        listOfGhost.get(2).setWeakGhost(false);
        listOfGhost.get(3).setWeakGhost(false);
        assertTrue(game.isWeakGhost());
        listOfGhost.get(0).setWeakGhost(true);
        listOfGhost.get(1).setWeakGhost(true);
        listOfGhost.get(2).setWeakGhost(false);
        listOfGhost.get(3).setWeakGhost(true);
        assertTrue(game.isWeakGhost());
        listOfGhost.get(0).setWeakGhost(true);
        listOfGhost.get(1).setWeakGhost(true);
        listOfGhost.get(2).setWeakGhost(true);
        listOfGhost.get(3).setWeakGhost(false);
        assertTrue(game.isWeakGhost());
        listOfGhost.get(0).setWeakGhost(true);
        listOfGhost.get(1).setWeakGhost(true);
        listOfGhost.get(2).setWeakGhost(true);
        listOfGhost.get(3).setWeakGhost(true);
        assertTrue(game.isWeakGhost());
    }


    @Test
    void collisionWithGhostTest() {
        listOfGhost.get(0).setPos(5,5);
        pacMan.setBody(5,5);
        listOfGhost.get(0).setLastBody(5,4);
        pacMan.setLastBody(6,5);
        assertTrue(game.hasCollidedWithGhost());
        game.eatWeakGhost();
        assertEquals(2, game.getLives());
        listOfGhost.get(0).setPos(5,5);
        pacMan.setBody(5,5);
        listOfGhost.get(0).setLastBody(5,4);
        pacMan.setLastBody(6,5);
        game.eatWeakGhost();
        assertEquals(1, game.getLives());
        listOfGhost.get(0).setPos(5,5);
        pacMan.setBody(5,5);
        listOfGhost.get(0).setLastBody(5,4);
        pacMan.setLastBody(6,5);
        game.eatWeakGhost();
        game.checkEndGame();
        assertTrue(game.isEnded());
        listOfGhost.get(0).setPos(5,6);
        pacMan.setBody(5,5);
        listOfGhost.get(0).setLastBody(5,5);
        pacMan.setLastBody(4,5);
        assertTrue(game.hasCollidedWithGhost());
        listOfGhost.get(0).setPos(9,7);
        pacMan.setBody(5,4);
        listOfGhost.get(0).setLastBody(8,7);
        pacMan.setLastBody(4,4);
        assertFalse(game.hasCollidedWithGhost());
    }

    @Test
    void addGhostTest() {
        game.addGhosts();
        game.addGhosts();
        game.addGhosts();
        assertEquals(7, game.getListOfGhost().size());
    }


    @Test
    void gettersTest() {
        for (int i = 0; i < listOfGhost.size(); i++) {
            assertEquals(new Ghost().getPos().getPosX(), listOfGhost.get(i).getPos().getPosX());
            assertEquals(new Ghost().getPos().getPosY(), listOfGhost.get(i).getPos().getPosY());
        }
        assertEquals(new PacMan().getPos().getPosX(), game.getPacMan().getPos().getPosX());
        assertEquals(new PacMan().getPos().getPosY(), game.getPacMan().getPos().getPosY());
        assertEquals(50, game.getTickPerSec());
        assertEquals(165,game.getMap().getWalls().length);
        assertEquals(106,game.getPellets().getPellet().length);
        assertEquals(650, game.getWidth());
        assertEquals(600, game.getLength());
    }

}