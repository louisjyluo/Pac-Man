package ui;

import model.PacManGame;

import javax.swing.*;
import java.awt.*;

import static java.awt.Font.BOLD;

//Class that adds a panel that handles all the text with code cited SpaceInvadersBase
public class ScorePanel extends JPanel {
    private static final String SCORE = "Score: ";
    private static final String POWERUP = "PowerUp Duration Timer: ";
    private static final String GHOSTS = "Ghosts: ";
    private static final String TOTALTIME = "/350";
    private static final int LBL_WIDTH = 100;
    private static final int LBL_HEIGHT = 20;
    private PacManGame game;
    private JLabel score;
    private JLabel powerUp;
    private JLabel ghosts;

    // MODIFIES: g
    // EFFECTS: sets the background colour and draws the initial labels;
    //          sets up the amount of ghosts, score, and the timer for the
    //          power up.
    public ScorePanel(PacManGame g) {
        game = g;
        setBackground(new Color(220, 150, 0));
        Font font = new Font("comic sans", BOLD, 15);
        score = new JLabel(SCORE + game.getPellets().getScore());
        score.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        score.setFont(font);
        powerUp = new JLabel(POWERUP + game.getPowerUpDurationTimer() + TOTALTIME);
        powerUp.setPreferredSize(new Dimension(300, LBL_HEIGHT));
        powerUp.setFont(font);
        ghosts = new JLabel(GHOSTS + game.getListOfGhost().size());
        ghosts.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        ghosts.setFont(font);
        add(score, BorderLayout.EAST);
        add(ghosts, BorderLayout.WEST);
        add(powerUp, BorderLayout.WEST);

    }

    // MODIFIES: this
    // EFFECT:  updates the amount of ghosts, score, and the timer for the power up.
    public void update() {
        score.setText(SCORE + game.getPellets().getScore());
        powerUp.setText(POWERUP + game.getPowerUpDurationTimer() + TOTALTIME);
        ghosts.setText(GHOSTS + game.getListOfGhost().size());
        repaint();
    }

    public void setGame(PacManGame game) {
        this.game = game;
    }
}
