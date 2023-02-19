package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MapTest {
    private Map map;

    @BeforeEach
    void runBefore() {
        map = new Map();
    }

    @Test
    void makeMapTest(){
        ArrayList<Position> walls = map.makeMap();
        assertEquals(1, walls.get(0).getPosX());
        assertEquals(0, walls.get(0).getPosY());
        assertEquals(2, walls.get(1).getPosX());
        assertEquals(0, walls.get(1).getPosY());
    }

}
