package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//Test for Pellet Class
public class PelletTest {
    private Pellets pellets;

    @BeforeEach
    void runBefore() {
        pellets = new Pellets();
    }

    @Test
    void makeMapTest(){
        int[][] walls = pellets.getPellet();
        assertEquals(3, walls[0][1]);
        assertEquals(1, walls[0][0]);
        assertEquals(4, walls[1][1]);
        assertEquals(1, walls[1][0]);
    }

    @Test
    void increaseScoreTest(){
        pellets.increaseScore();
        assertEquals(10,pellets.getScore());
        pellets.increaseScore();
        pellets.increaseScore();
        pellets.increaseScore();
        assertEquals(40,pellets.getScore());

    }

    @Test
    void increaseGhostScoreTest(){
        pellets.increaseScoreGhost();
        assertEquals(200,pellets.getScore());
        pellets.increaseScoreGhost();
        pellets.increaseScore();
        pellets.increaseScore();
        assertEquals(420,pellets.getScore());

    }

    @Test
    void eatPelletsTest(){
        pellets.eatPellet(3,1);
        int x = pellets.getPellet()[0][1];
        int y = pellets.getPellet()[0][0];
        assertEquals(32, x);
        assertEquals(32, y);
        pellets.eatPellet(8,1);
        x = pellets.getPellet()[5][1];
        y = pellets.getPellet()[5][0];
        assertEquals(32, x);
        assertEquals(32, y);

    }

}
