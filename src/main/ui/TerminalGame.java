package ui;


import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.input.*;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

//Creates the ui and draws the game using Lanterna
public class TerminalGame {
    private static final String JSON_STORE = "./data/pacManGame.json";
    private PacManGame game;
    private TerminalScreen screen;
    private WindowBasedTextGUI endGui;
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);

    //MODIFIES: this
    //EFFECTS: starts the game and ticks.
    public void start() throws IOException, InterruptedException {
        screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();
        screen.doResizeIfNecessary();
        game = new PacManGame();
        beginTicks();
    }

    //MODIFIES: this
    //EFFECTS: Determines the speed of the ticks and when to end it.
    private void beginTicks() throws IOException, InterruptedException {
        while (!game.isEnded() || endGui.getActiveWindow() != null) {
            tick();
            Thread.sleep(1000L / game.getTickPerSec());
        }

        System.exit(0);
    }

    //MODIFIES: this
    //EFFECTS: which methods should each tick include
    private void tick() throws IOException {
        handleUserInput();
        game.tick();
        screen.clear();
        render();
        screen.refresh();
    }

    //MODIFIES: this
    //EFFECTS: handles user input
    private void handleUserInput() throws IOException {
        KeyStroke stroke = screen.pollInput();

        if (stroke == null) {
            return;
        }


        if (stroke.getCharacter() != null) {
            return;
        }

        Direction dir = directionFrom(stroke.getKeyType());
        saveLoadAddKeys(stroke.getKeyType());


        if (dir == null) {
            return;
        }

        game.getPacMan().setDirection(dir);
    }

    //MODIFIES: this
    //EFFECTS: handles the input of directions and returns the direction type
    private Direction directionFrom(KeyType type) {
        switch (type) {
            case ArrowUp:
                return Direction.UP;
            case ArrowDown:
                return Direction.DOWN;
            case ArrowRight:
                return Direction.RIGHT;
            case ArrowLeft:
                return Direction.LEFT;
            default:
                return null;
        }
    }

    public void saveLoadAddKeys(KeyType type) {
        switch (type) {
            case F1:
                System.out.println("Saving...");
                savePacManGame();
                break;
            case F2:
                System.out.println("Loading old save");
                loadPacManGame();
                break;
            case F3:
                game.addGhosts();
            default:
        }
    }


    //MODIFIES: this
    //EFFECTS: renders the game on the lanterna terminal
    private void render() {
        if (game.isEnded()) {
            if (endGui == null) {
                drawEndScreen();
            }

            return;
        }
        drawTopScreen();
        drawPowerUps();
        drawMap();
        drawGhostGate();
        drawPellets();
        drawPacMan();
        drawBlinky();
        drawInky();
        drawPinky();
        drawClyde();
        drawExtraGhosts();
    }

    //MODIFIES: this
    //EFFECTS: when game has ended, show Game over screen with score.
    private void drawEndScreen() {
        endGui = new MultiWindowTextGUI(screen);

        new MessageDialogBuilder()
                .setTitle("Game over!")
                .setText("You finished with a score of " + game.getPellets().getScore() + "!")
                .addButton(MessageDialogButton.Close)
                .build()
                .showDialog(endGui);
    }

    //MODIFIES: this
    //EFFECTS: draws the score on the top left of the terminal as a pellet gets collected, and
    //also the keys to save and load the file.
    private void drawTopScreen() {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(TextColor.ANSI.BLUE);
        text.putString(1, 0, "Score: ");

        text = screen.newTextGraphics();
        text.setForegroundColor(TextColor.ANSI.WHITE);
        text.putString(8, 0, String.valueOf(game.getPellets().getScore()));

        text = screen.newTextGraphics();
        text.setForegroundColor(TextColor.ANSI.WHITE);
        text.putString(24, 0, "Lives: " + game.getLives());

        text = screen.newTextGraphics();
        text.setForegroundColor(TextColor.ANSI.WHITE);
        text.putString(20, 0, "F1: save - F2:load - F3: add Ghost");
    }

    //MODIFIES: this
    //EFFECTS: draws PacMan on the map and updates everytime he moves
    private void drawPacMan() {
        PacMan pacMan = game.getPacMan();
        if (pacMan.getDir() == Direction.RIGHT) {
            drawPosition(pacMan.getPos(), TextColor.ANSI.WHITE, '<');
        } else if (pacMan.getDir() == Direction.LEFT) {
            drawPosition(pacMan.getPos(), TextColor.ANSI.WHITE, '>');
        } else if (pacMan.getDir() == Direction.UP) {
            drawPosition(pacMan.getPos(), TextColor.ANSI.WHITE, 'v');
        } else if (pacMan.getDir() == Direction.DOWN) {
            drawPosition(pacMan.getPos(), TextColor.ANSI.WHITE, '^');
        }


    }

    //MODIFIES: this
    //EFFECTS: draws the walls on the terminal
    private void drawMap() {
        for (int i = 0; i < game.getMap().getWalls().length; i++) {
            drawBoard(game.getMap().getWalls()[i][1],
                    game.getMap().getWalls()[i][0], TextColor.ANSI.GREEN, 'H');
        }
    }

    //MODIFIES: this
    //EFFECTS: draws Blinky the ghost on the map, and updates with his path
    private void drawBlinky() {
        Ghost blinky = game.getListOfGhost().get(0);

        if (blinky.getWeak()) {
            drawPosition(blinky.getPos(), TextColor.ANSI.CYAN, 'n');
        } else {
            drawPosition(blinky.getPos(), TextColor.ANSI.RED, 'n');
        }

    }

    //MODIFIES: this
    //EFFECTS: draws Inky the ghost on the map, and updates with his path
    private void drawInky() {
        Ghost inky = game.getListOfGhost().get(1);

        if (inky.getWeak()) {
            drawPosition(inky.getPos(), TextColor.ANSI.CYAN, 'n');
        } else {
            drawPosition(inky.getPos(), TextColor.ANSI.BLUE, 'n');
        }

    }

    //MODIFIES: this
    //EFFECTS: draws Pinky the ghost on the map, and updates with her path
    private void drawPinky() {
        Ghost pinky = game.getListOfGhost().get(2);

        if (pinky.getWeak()) {
            drawPosition(pinky.getPos(), TextColor.ANSI.CYAN, 'n');
        } else {
            drawPosition(pinky.getPos(), TextColor.ANSI.MAGENTA, 'n');
        }

    }

    //MODIFIES: this
    //EFFECTS: draws Clyde the ghost on the map, and updates with his path
    private void drawClyde() {
        Ghost clyde = game.getListOfGhost().get(3);

        if (clyde.getWeak()) {
            drawPosition(clyde.getPos(), TextColor.ANSI.CYAN, 'n');
        } else {
            drawPosition(clyde.getPos(), TextColor.ANSI.YELLOW, 'n');
        }

    }

    //MODIFIES: this
    //EFFECTS: draws ghosts that were added during the game, and updates with his path
    private void drawExtraGhosts() {
        for (int i = 4; i < game.getListOfGhost().size(); i++) {
            Ghost ghost = game.getListOfGhost().get(i);
            if (ghost.getWeak()) {
                drawPosition(ghost.getPos(), TextColor.ANSI.CYAN, 'n');
            } else {
                drawPosition(ghost.getPos(), TextColor.ANSI.YELLOW, 'n');
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: draws all the pellets on the terminal
    private void drawPellets() {
        for (int i = 0; i < game.getPellets().getPellet().length; i++) {
            drawBoard(game.getPellets().getPellet()[i][1],
                    game.getPellets().getPellet()[i][0], TextColor.ANSI.WHITE, '•');
        }

    }

    //MODIFIES: this
    //EFFECTS: draws all the powerUps on the terminal
    private void drawPowerUps() {
        for (int i = 0; i < game.getPower().getPowerUps().length; i++) {
            drawBoard(game.getPower().getPowerUps()[i][1],
                    game.getPower().getPowerUps()[i][0], TextColor.ANSI.WHITE, 'o');
        }
    }

    //MODIFIES: this
    //EFFECTS: draws the ghost gate that only ghosts can pass through
    private void drawGhostGate() {
        drawPosition(new Position(10,6), TextColor.ANSI.WHITE, '~');
    }

    //MODIFIES: this
    //EFFECTS: the actual method that puts the input on the terminal screen.
    private void drawPosition(Position pos, TextColor color, char c) {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(color);
        text.putString(pos.getPosX() * 2 + 18, pos.getPosY() + 4, String.valueOf(c));
    }

    //MODIFIES: this
    //EFFECTS: the actual method that puts the input on the terminal screen.
    private void drawBoard(int posX, int posY, TextColor color, char c) {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(color);
        text.putString(posX * 2 + 18,posY + 4, String.valueOf(c));
    }

    // MODIFIES: this
    // EFFECTS: saves the game to file
    private void savePacManGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(game);
            jsonWriter.close();
            System.out.println("Successfully saved");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + game);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads game from file
    private void loadPacManGame() {
        try {
            game = jsonReader.read();
            System.out.println("Loaded " + "PacMan" + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}