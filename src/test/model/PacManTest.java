package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Test for PacMan Class
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
        pacMan.setDirection(Direction.RIGHT);
        assertEquals(Direction.RIGHT, pacMan.getDir());
    }

    @Test
    void MoveTest() {
        pacMan.setDirection(Direction.DOWN);
        pacMan.move();
        assertEquals(10,pacMan.getPos().getPosX());
        assertEquals(11,pacMan.getPos().getPosY());


        pacMan.setDirection(Direction.UP);
        if (pacMan.getDir() == Direction.UP) {
            pacMan.move();
            assertEquals(10,pacMan.getPos().getPosX());
            assertEquals(10,pacMan.getPos().getPosY());
        }

        pacMan.setDirection(Direction.RIGHT);
        if (pacMan.getDir() == Direction.RIGHT) {
            pacMan.move();
            assertEquals(11,pacMan.getPos().getPosX());
            assertEquals(10,pacMan.getPos().getPosY());
        }

        pacMan.setDirection(Direction.LEFT);
        if (pacMan.getDir() == Direction.LEFT) {
            pacMan.move();
            assertEquals(10,pacMan.getPos().getPosX());
            assertEquals(10,pacMan.getPos().getPosY());
        }

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

        while(!pacMan.cantMoveUp()) {
            pacMan.moveUp();
        }
        pacMan.moveUp();
        assertEquals(10,pacMan.getPos().getPosX());
        assertEquals(10,pacMan.getPos().getPosY());

        while(!pacMan.cantMoveDown()) {
            pacMan.moveDown();
        }
        pacMan.moveDown();
        assertEquals(10,pacMan.getPos().getPosX());
        assertEquals(12,pacMan.getPos().getPosY());

        while(!pacMan.cantMoveRight()) {
            pacMan.moveRight();
        }
        pacMan.moveRight();
        assertEquals(11,pacMan.getPos().getPosX());
        assertEquals(12,pacMan.getPos().getPosY());

        while(!pacMan.cantMoveLeft()) {
            pacMan.moveLeft();
        }
        pacMan.moveLeft();
        assertEquals(9,pacMan.getPos().getPosX());
        assertEquals(12,pacMan.getPos().getPosY());
    }


}
