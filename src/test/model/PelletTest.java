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
        ArrayList<Position> walls = pellets.makePellets();
        assertEquals(3, walls.get(0).getPosX());
        assertEquals(1, walls.get(0).getPosY());
        assertEquals(4, walls.get(1).getPosX());
        assertEquals(1, walls.get(1).getPosY());
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
        pellets.makePellets();
        pellets.eatPellet(3,1);
        int x = pellets.getMap().get(0).getPosX();
        int y = pellets.getMap().get(0).getPosY();
        assertEquals(32, x);
        assertEquals(32, y);
        pellets.eatPellet(8,1);
        x = pellets.getMap().get(5).getPosX();
        y = pellets.getMap().get(5).getPosY();
        assertEquals(32, x);
        assertEquals(32, y);

    }

}
