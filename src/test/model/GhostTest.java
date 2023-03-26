package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Test for Ghost Class
public class GhostTest {
    private Ghost blinky;

    @BeforeEach
    void runBefore() {
        blinky = new Ghost();
    }

    @Test
    void constructorTest() {
        assertEquals(10,blinky.getPos().getPosX());
        assertEquals(7,blinky.getPos().getPosY());
        assertEquals(10,blinky.getLastBody().getPosX());
        assertEquals(7,blinky.getLastBody().getPosY());
    }

    @Test
    void randomMoveTest() {
        if (blinky.getRandomNum() < 0.25) {
            blinky.moveDown();
            assertEquals(10,blinky.getPos().getPosX());
            assertEquals(8,blinky.getPos().getPosY());
        }

        if (blinky.getRandomNum() >= 0.25 && blinky.getRandomNum() < 0.5) {
            blinky.moveUp();
            assertEquals(10,blinky.getPos().getPosX());
            assertEquals(7,blinky.getPos().getPosY());
        }

        if (blinky.getRandomNum() >= 0.5 && blinky.getRandomNum() < 0.75) {
            blinky.moveRight();
            assertEquals(11,blinky.getPos().getPosX());
            assertEquals(7,blinky.getPos().getPosY());
        }

        if (blinky.getRandomNum() >= 0.75) {
            blinky.moveLeft();
            assertEquals(9,blinky.getPos().getPosX());
            assertEquals(7,blinky.getPos().getPosY());
        }
    }

    @Test
    void moveTest() {
        blinky.moveDown();
        assertEquals(10,blinky.getPos().getPosX());
        assertEquals(8,blinky.getPos().getPosY());
        blinky.moveUp();
        assertEquals(10,blinky.getPos().getPosX());
        assertEquals(7,blinky.getPos().getPosY());
        blinky.moveLeft();
        assertEquals(9,blinky.getPos().getPosX());
        assertEquals(7,blinky.getPos().getPosY());
        blinky.moveRight();
        assertEquals(10,blinky.getPos().getPosX());
        assertEquals(7,blinky.getPos().getPosY());
    }

    @Test
    void moveAtWallTest() {
        while(!blinky.cantMoveLeft()) {
            blinky.moveLeft();
        }
        blinky.moveLeft();
        assertEquals(9,blinky.getPos().getPosX());
        assertEquals(7,blinky.getPos().getPosY());

        while(!blinky.cantMoveRight()) {
            blinky.moveRight();
        }
        blinky.moveRight();
        assertEquals(11,blinky.getPos().getPosX());
        assertEquals(7,blinky.getPos().getPosY());

        while(!blinky.cantMoveUp()) {
            blinky.moveUp();
        }
        blinky.moveUp();
        assertEquals(11,blinky.getPos().getPosX());
        assertEquals(7,blinky.getPos().getPosY());

        while(!blinky.cantMoveDown()) {
            blinky.moveDown();
        }
        blinky.moveDown();
        assertEquals(11,blinky.getPos().getPosX());
        assertEquals(8,blinky.getPos().getPosY());

    }
}
