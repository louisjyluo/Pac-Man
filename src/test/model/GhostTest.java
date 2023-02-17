package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GhostTest {
    private Ghost blinky;

    @BeforeEach
    void runBefore() {
        blinky = new Ghost();
    }

    @Test
    void constructorTest() {
        assertEquals(13,blinky.getPos().getPosX());
        assertEquals(15,blinky.getPos().getPosY());
        assertEquals(13,blinky.getLastBody().getPosX());
        assertEquals(15,blinky.getLastBody().getPosY());
    }

    @Test
    void moveTest() {
        blinky.moveDown();
        assertEquals(13,blinky.getPos().getPosX());
        assertEquals(16,blinky.getPos().getPosY());
        blinky.moveUp();
        assertEquals(13,blinky.getPos().getPosX());
        assertEquals(15,blinky.getPos().getPosY());
        blinky.moveLeft();
        assertEquals(12,blinky.getPos().getPosX());
        assertEquals(15,blinky.getPos().getPosY());
        blinky.moveRight();
        assertEquals(13,blinky.getPos().getPosX());
        assertEquals(15,blinky.getPos().getPosY());
    }

    @Test
    void moveAtWallTest() {
        while(!blinky.cantMoveLeft()) {
            blinky.moveLeft();
        }
        assertEquals(11,blinky.getPos().getPosX());
        assertEquals(15,blinky.getPos().getPosY());

        while(!blinky.cantMoveRight()) {
            blinky.moveRight();
        }
        assertEquals(16,blinky.getPos().getPosX());
        assertEquals(15,blinky.getPos().getPosY());

        while(!blinky.cantMoveUp()) {
            blinky.moveUp();
        }
        assertEquals(16,blinky.getPos().getPosX());
        assertEquals(14,blinky.getPos().getPosY());

        while(!blinky.cantMoveDown()) {
            blinky.moveDown();
        }
        assertEquals(16,blinky.getPos().getPosX());
        assertEquals(16,blinky.getPos().getPosY());

    }
}
