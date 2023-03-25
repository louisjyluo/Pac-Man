package ui;


import model.Direction;
import model.PacManGame;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

//Class that starts the Gui for PacMan with code cited from both IntersectionGui, and SpaceInvadersBase
public class TerminalGui extends JFrame {
    private static final String JSON_STORE = "./data/pacManGame.json";
    private PacManGame game;
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);
    private GamePanel panel;
    private ScorePanel scoreDisplay;
    private static final int INTERVAL = 30;
    private KeyHandler key;

    // MODIFIES: this
    // EFFECTS: Sets up the Gui, creates board, and adds the panels.
    public TerminalGui() {
        super("Pac-Man");
        setUndecorated(false);
        game = new PacManGame();
        panel = new GamePanel(game);
        scoreDisplay = new ScorePanel(game);
        add(scoreDisplay, BorderLayout.SOUTH);
        add(panel);
        createBoard();
        addButtonPanel();
        key = new KeyHandler();
        addKeyListener(key);
        setFocusable(true);
        pack();
        centreOnScreen();
        setVisible(true);
        addTimer();
    }

    // MODIFIES: this
    // EFFECTS: creates the board for the panels to be put on
    private void createBoard() {
        setPreferredSize(new Dimension(game.getWidth(), game.getLength()));
        JPanel map = new JPanel();
        map.setLayout(new BoxLayout(map, BoxLayout.LINE_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(0,0,200));
    }

    // MODIFIES: none
    // EFFECTS:  initializes a timer that updates game each
    //           INTERVAL milliseconds
    private void addTimer() {
        Timer t = new Timer(INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                game.tick();
                panel.repaint();
                scoreDisplay.update();
                requestFocus();
            }
        });
        t.start();
    }


    // MODIFIES: this
    // EFFECTS: put the gui at the center of the screen
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }


    // MODIFIES: this
    // EFFECTS: handles all the different buttons.
    public void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,3));
        buttonPanel.add(saveButton());
        buttonPanel.add(loadButton());
        buttonPanel.add(addGhostButton());
        add(buttonPanel, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: A button that will save the game to file
    public JButton saveButton() {
        JButton saveButton = new JButton("Save file");
        saveButton.setActionCommand("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(game);
                    jsonWriter.close();
                    System.out.println("Successfully saved");
                } catch (FileNotFoundException error) {
                    System.out.println("Unable to write to file: " + game);
                }
            }
        });
        return saveButton;
    }

    // MODIFIES: this
    // EFFECTS: a button that will load the file to the game
    public JButton loadButton() {
        JButton loadButton = new JButton("Load file");
        loadButton.setActionCommand("Load");
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    game = jsonReader.read();
                    panel.setGame(game);
                    scoreDisplay.setGame(game);
                    System.out.println("Loaded " + "PacMan" + " from " + JSON_STORE);
                } catch (IOException error) {
                    System.out.println("Unable to read from file: " + JSON_STORE);
                }
            }
        });
        setFocusable(true);
        return loadButton;
    }

    // MODIFIES: this
    // EFFECTS: a button that will add a ghost to the game
    public JButton addGhostButton() {
        JButton ghostButton = new JButton("Add Ghosts");
        ghostButton.setActionCommand("Add Ghosts");
        ghostButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.addGhosts();
            }
        });
        return ghostButton;
    }

    //Class that handles all the key inputs
    private class KeyHandler extends KeyAdapter {
        // MODIFIES: this
        // EFFECTS: takes the key inputs and sent them to handle inputs
        public void keyPressed(KeyEvent e) {
            handleInput(e.getKeyCode());
        }
    }

    // MODIFIES: this
    // EFFECTS: handles all the hotkeys for save, load, and add, and also
    // pacman movement.
    public void handleInput(int keyCode) {
        if (keyCode == KeyEvent.VK_S) {
            savePacManGame();
        } else if (keyCode == KeyEvent.VK_L) {
            loadPacManGame();
        } else if (keyCode == KeyEvent.VK_A) {
            game.addGhosts();
        } else {
            handlePacManMovement(keyCode);
        }
    }


    // MODIFIES: this
    // EFFECTS: handles pacman movement
    public void handlePacManMovement(int keyCode) {
        if (keyCode == KeyEvent.VK_KP_LEFT || keyCode == KeyEvent.VK_LEFT) {
            game.getPacMan().setDirection(Direction.LEFT);
        } else if (keyCode == KeyEvent.VK_KP_RIGHT || keyCode == KeyEvent.VK_RIGHT) {
            game.getPacMan().setDirection(Direction.RIGHT);
        } else if (keyCode == KeyEvent.VK_KP_UP || keyCode == KeyEvent.VK_UP) {
            game.getPacMan().setDirection(Direction.UP);
        } else if (keyCode == KeyEvent.VK_KP_DOWN || keyCode == KeyEvent.VK_DOWN) {
            game.getPacMan().setDirection(Direction.DOWN);
        }
    }

    // MODIFIES: this
    // EFFECTS: saves game to file
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
            panel.setGame(game);
            scoreDisplay.setGame(game);
            System.out.println("Loaded " + "PacMan" + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: the system starts by running here.
    public static void main(String[] args) {
        new TerminalGui();
    }

}
