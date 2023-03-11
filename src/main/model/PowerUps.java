package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//Represents the power ups that can be collected by PacMan for a boost.
public class PowerUps implements Writable {
    private ArrayList<Position> map;
    private final int[][] powerUps = {{1,2},{1,18},{13, 4},{13,16}};

    //MODIFIES: this
    //EFFECTS: sets up the position of where all the power ups will be.
    public PowerUps() {
        map = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: puts the powerUps on the map.
    public ArrayList<Position> makePowerUps() {
        for (int i = 0; i < powerUps.length; i++) {
            Position pixel = new Position(powerUps[i][1], powerUps[i][0]);
            map.add(pixel);
        }
        return map;
    }

    //MODIFIES: this
    //EFFECTS: When PacMan reaches a powerUp, removes it off the map.
    public void eatPowerUp(int posX, int posY) {
        for (int i = 0;  i < powerUps.length; i++) {
            if (map.get(i).getPosX() == posX
                    && map.get(i).getPosY() == posY) {
                map.set(i, new Position(33,33));
                break;
            }

        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("power ups", pupToJson());
        return json;
    }

    private JSONArray pupToJson() {
        JSONArray jsonArray = new JSONArray();
        for (int w = 0; w < powerUps.length; w++) {
            jsonArray.put(map.get(w).toJson());
        }
        return jsonArray;
    }

    public void setMap(ArrayList<Position> map) {
        this.map = map;
    }


    public int[][] getPowerUps() {
        return powerUps;
    }
}
