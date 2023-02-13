package model;

import model.Position;

import java.util.ArrayList;

public class Pellets {
    private Position pellet;
    private ArrayList<Position> pellets;
    private int score;

    public Pellets() {
        pellet = new Position(0,0);
        pellets = new ArrayList<Position>();
        score = 0;
    }


    public int getScore() {
        return score;
    }

    public ArrayList<Position> getPellets() {
        return pellets;
    }

    public Position getPellet() {
        return pellet;
    }


}
