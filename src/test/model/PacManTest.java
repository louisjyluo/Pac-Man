package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PacManTest {
    private PacMan pacMan;

    @BeforeEach
    void runBefore() {
        pacMan = new PacMan();
    }

    @Test
    void constructorTest() {
        assertEquals(13,pacMan.getPos().getPosX());
        assertEquals(18,pacMan.getPos().getPosY());
        assertEquals(13,pacMan.getLastBody().getPosX());
        assertEquals(18,pacMan.getLastBody().getPosY());
    }

    @Test
    void moveTest() {
        pacMan.moveLeft();
        pacMan.moveLeft();
        pacMan.moveLeft();
        pacMan.moveLeft();
        assertEquals(9,pacMan.getPos().getPosX());
        assertEquals(18,pacMan.getPos().getPosY());
        pacMan.moveUp();
        assertEquals(9,pacMan.getPos().getPosX());
        assertEquals(17,pacMan.getPos().getPosY());
        pacMan.moveDown();
        assertEquals(9,pacMan.getPos().getPosX());
        assertEquals(18,pacMan.getPos().getPosY());
        pacMan.moveRight();
        assertEquals(10,pacMan.getPos().getPosX());
        assertEquals(18,pacMan.getPos().getPosY());

    }

    @Test
    // Pacman shouldn't move if there is a wall in the way.
    void moveAtWallTest() {
        assertTrue(pacMan.cantMoveDown());
        pacMan.moveDown();
        assertEquals(13,pacMan.getPos().getPosX());
        assertEquals(18,pacMan.getPos().getPosY());

        assertTrue(pacMan.cantMoveUp());
        pacMan.moveUp();
        assertEquals(13,pacMan.getPos().getPosX());
        assertEquals(18,pacMan.getPos().getPosY());

        while(!pacMan.cantMoveRight()) {
            pacMan.moveRight();
        }
        assertEquals(18,pacMan.getPos().getPosX());
        assertEquals(18,pacMan.getPos().getPosY());

        while(!pacMan.cantMoveLeft()) {
            pacMan.moveLeft();
        }
        assertEquals(9,pacMan.getPos().getPosX());
        assertEquals(18,pacMan.getPos().getPosY());
    }


}
