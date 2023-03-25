package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//Test for Walls Class
public class WallsTest {
    private Walls map;

    @BeforeEach
    void runBefore() {
        map = new Walls();
    }

    @Test
    void makeMapTest(){
        int[][] walls = map.getWalls();
        assertEquals(1, walls[0][1]);
        assertEquals(0, walls[0][0]);
        assertEquals(2, walls[1][1]);
        assertEquals(0, walls[1][0]);
    }

}
