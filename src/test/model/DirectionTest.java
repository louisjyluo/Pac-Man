package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.Direction.RIGHT;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectionTest {
    private Position pos;
    private Position pos1;

    @BeforeEach
    void runBefore() {
        pos = new Position(1,1);
        pos1 = new Position(2,1);

    }

    @Test
    void moveTest() {
        Direction dir = RIGHT;
        assertEquals(pos1.getPosX(), dir.move(pos).getPosX());
        assertEquals(pos1.getPosY(), dir.move(pos).getPosY());
    }



}
