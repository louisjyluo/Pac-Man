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
        assertEquals(10,pacMan.getPos().getPosX());
        assertEquals(10,pacMan.getPos().getPosY());
        assertEquals(10,pacMan.getLastBody().getPosX());
        assertEquals(10,pacMan.getLastBody().getPosY());
    }

    @Test
    void moveTest() {
        pacMan.moveLeft();
        pacMan.moveLeft();
        pacMan.moveLeft();
        assertEquals(7,pacMan.getPos().getPosX());
        assertEquals(10,pacMan.getPos().getPosY());
        pacMan.moveUp();
        assertEquals(7,pacMan.getPos().getPosX());
        assertEquals(9,pacMan.getPos().getPosY());
        pacMan.moveDown();
        assertEquals(7,pacMan.getPos().getPosX());
        assertEquals(10,pacMan.getPos().getPosY());
        pacMan.moveRight();
        assertEquals(8,pacMan.getPos().getPosX());
        assertEquals(10,pacMan.getPos().getPosY());

    }

    @Test
    // Pacman shouldn't move if there is a wall in the way.
    void moveAtWallTest() {

        assertTrue(pacMan.cantMoveUp());
        pacMan.moveUp();
        assertEquals(10,pacMan.getPos().getPosX());
        assertEquals(10,pacMan.getPos().getPosY());

        pacMan.moveDown();
        assertEquals(10,pacMan.getPos().getPosX());
        assertEquals(11,pacMan.getPos().getPosY());

        while(!pacMan.cantMoveRight()) {
            pacMan.moveRight();
        }
        assertEquals(10,pacMan.getPos().getPosX());
        assertEquals(11,pacMan.getPos().getPosY());

        while(!pacMan.cantMoveLeft()) {
            pacMan.moveLeft();
        }
        assertEquals(10,pacMan.getPos().getPosX());
        assertEquals(11,pacMan.getPos().getPosY());
    }


}
