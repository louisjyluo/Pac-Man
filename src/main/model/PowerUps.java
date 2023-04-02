package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

//Represents the power ups that can be collected by PacMan for a boost.
public class PowerUps implements Writable {
    private int[][] powerUps = {{1,2},{1,18},{13, 4},{13,16}};

    private int[][] backUp = {{1,2},{1,18},{13, 4},{13,16}};

    //MODIFIES: this
    //EFFECTS: When PacMan reaches a powerUp, removes it off the map.
    public void eatPowerUp(int posX, int posY) {
        for (int i = 0;  i < powerUps.length; i++) {
            if (powerUps[i][1] == posX
                    && powerUps[i][0] == posY) {
                powerUps[i][1] = 32;
                powerUps[i][0] = 32;
                break;
            }

        }
    }

    //MODIFIES: this
    //EFFECTS: parse the power Ups into Json
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("powerUpX", pupXToJson());
        json.put("powerUpY", pupYToJson());
        return json;
    }

    //MODIFIES: this
    //EFFECTS: converts each power Up array value into Position Json
    private JSONArray pupXToJson() {
        JSONArray jsonArray = new JSONArray();
        for (int w = 0; w < powerUps.length; w++) {
            jsonArray.put(powerUps[w][1]);
        }
        return jsonArray;
    }

    //MODIFIES: this
    //EFFECTS: converts each power Up array value into Position Json
    private JSONArray pupYToJson() {
        JSONArray jsonArray = new JSONArray();
        for (int w = 0; w < powerUps.length; w++) {
            jsonArray.put(powerUps[w][0]);
        }
        return jsonArray;
    }

    public int[][] getPowerUps() {
        return powerUps;
    }

    public int[][] getBackUp() {
        return backUp;
    }
}
