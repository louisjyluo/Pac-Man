package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {
    private Position pos;

    @BeforeEach
    void runBefore() {
        pos = new Position(0,0);


    }

    @Test
    void constructorTest() {
        assertEquals(0,pos.getPosX());
        assertEquals(0,pos.getPosY());
    }
}
