package persistence;

import model.*;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

//Class that parse the Json into code cited from JsonSerializationDemo-master
public class JsonReader {
    private String source;

    // REQUIRES: file to not be empty
    // MODIFIES: this
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // MODIFIES: this
    // EFFECTS: reads PacManGame from file and returns it;
    // throws IOException if an error occurs reading data from file
    public PacManGame read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePacManGame(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses PacManGame from JSON object and returns it
    private PacManGame parsePacManGame(JSONObject jsonObject) {
        PacManGame game = new PacManGame();
        addGhosts(game, jsonObject);
        addPacMan(game, jsonObject);
        addPellets(game, jsonObject);
        addPowerUp(game, jsonObject);
        int timer = jsonObject.getInt("timer");
        game.setPowerUpDurationTimer(timer);
        return game;
    }

    // MODIFIES: game
    // EFFECTS: parses Ghosts from JSON object and adds them to workroom
    private void addGhosts(PacManGame game, JSONObject jsonObject) {
        JSONArray listOfGhost = jsonObject.getJSONArray("ghosts");
        ArrayList<Ghost> ghosts = game.getListOfGhost();
        for (int i = 0; i < listOfGhost.length() - 4; i++) {
            game.addGhosts();
        }
        for (int i = 0; i < ghosts.size(); i++) {
            Ghost ghost = ghosts.get(i);
            JSONObject singleGhost = listOfGhost.getJSONObject(i);
            int posX = singleGhost.getJSONObject("position").getInt("X");
            int posY = singleGhost.getJSONObject("position").getInt("Y");
            int posLastX = singleGhost.getJSONObject("last position").getInt("X");
            int posLastY = singleGhost.getJSONObject("last position").getInt("Y");
            Boolean state = singleGhost.getBoolean("state");
            ghost.setPos(posX, posY);
            ghost.setWeakGhost(state);
            ghost.setLastBody(posLastX, posLastY);
        }
    }

    // MODIFIES: game
    // EFFECTS: parses PacMan from JSON object and adds it to workroom
    private void addPacMan(PacManGame game, JSONObject jsonObject) {
        PacMan man = game.getPacMan();
        JSONObject pacMan = jsonObject.getJSONObject("pacMan");
        JSONObject position = pacMan.getJSONObject("position");
        int posX = position.getInt("X");
        int posY = position.getInt("Y");
        man.setBody(posX, posY);
        JSONObject lastPosition = pacMan.getJSONObject("last position");
        int posLastX = lastPosition.getInt("X");
        int posLastY = lastPosition.getInt("Y");
        man.setBody(posLastX, posLastY);
        Direction three = Direction.valueOf(pacMan.getString("direction"));
        man.setDirection(three);
    }

    // MODIFIES: game
    // EFFECTS: parses pellets from JSON object and adds it to workroom
    private void addPellets(PacManGame game, JSONObject jsonObject) {
        int[][] map = game.getPellets().getPellet();
        JSONObject pellets = jsonObject.getJSONObject("pellet");
        int score = pellets.getInt("score");
        game.getPellets().setScore(score);
        JSONArray listOfPosX = pellets.getJSONArray("pelletX");
        JSONArray listOfPosY = pellets.getJSONArray("pelletY");
        for (int i = 0; i < listOfPosX.length(); i++) {
            int posX = listOfPosX.getInt(i);
            int posY = listOfPosY.getInt(i);
            map[i][0] = posY;
            map[i][1] = posX;
        }
    }

    // MODIFIES: game
    // EFFECTS: parses powerUp from JSON object and adds it to workroom
    private void addPowerUp(PacManGame game, JSONObject jsonObject) {
        int[][] map = game.getPower().getPowerUps();
        JSONObject powerUp = jsonObject.getJSONObject("power up");
        JSONArray powerX = powerUp.getJSONArray("powerUpX");
        JSONArray powerY = powerUp.getJSONArray("powerUpY");
        for (int i = 0; i < powerX.length(); i++) {
            int x = powerX.getInt(i);
            int y = powerY.getInt(i);
            map[i][1] = x;
            map[i][0] = y;
        }
    }
}



