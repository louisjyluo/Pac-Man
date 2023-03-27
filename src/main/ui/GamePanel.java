package ui;

import model.Direction;
import model.Ghost;
import model.PacManGame;

import javax.swing.*;
import java.awt.*;
import static java.awt.Font.BOLD;

// Class that will create the panel for the game itself with code cited from SpaceInvadersBase
public class GamePanel extends JPanel {
    private static final String OVER = "Game Over!";
    private static final String BEGIN = "Press Space to Begin!";
    private PacManGame game;
    private ImageIcon blinkyImage;
    private ImageIcon pinkyImage;
    private ImageIcon inkyImage;
    private ImageIcon clydeImage;
    private ImageIcon weakGhostImage;
    private ImageIcon wallImage;
    private ImageIcon pelletImage;
    private ImageIcon pacManUpImage;
    private ImageIcon pacManDownImage;
    private ImageIcon pacManLeftImage;
    private ImageIcon pacManRightImage;
    private ImageIcon powerUpImage;


    // MODIFIES: this
    // EFFECTS: calls the game and loads the images
    public GamePanel(PacManGame game) {
        super();
        this.game = game;
        loadImages();
    }


    // MODIFIES: g
    // EFFECTS: JPanel graphics that will be used to draw the images
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawWall(g);
        if (!game.isEnded()) {
            drawGame(g);
        } else if (game.isEnded()) {
            gameOver(g);
        }
    }


    // MODIFIES: g
    // EFFECTS: shows "game over" when the game ends
    private void gameOver(Graphics g) {
        Color saved = new Color(250,200,0);
        g.setColor(saved);
        g.setFont(new Font("Courier", BOLD, 40));
        FontMetrics fm = g.getFontMetrics();
        centreString(OVER, g, fm);
        g.setColor(saved);
    }

    // MODIFIES: g
    // EFFECTS:  centres the string str horizontally onto g at vertical position yPos
    private void centreString(String str, Graphics g, FontMetrics fm) {
        int width = fm.stringWidth(str);
        g.drawString(str, (game.getWidth() - width) / 2, (game.getLength() - width) / 2);
    }

    // MODIFIES: g
    // EFFECTS:  draws the game onto g
    private void drawGame(Graphics g) {
        setBackground(new Color(0,0,100));
        drawPacMan(g);
        drawClyde(g);
        drawPinky(g);
        drawBlinky(g);
        drawInky(g);
        drawPellet(g);
        drawPowerUp(g);
        drawExtraGhosts(g);
    }


    // MODIFIES: g
    // EFFECTS:  draws pacman depending on the direction he's facing
    private void drawPacMan(Graphics g) {
        int posX = game.getPacMan().getPos().getPosX();
        int posY = game.getPacMan().getPos().getPosY();
        if (game.getPacMan().getDir() == Direction.UP) {
            drawPosition(g, pacManUpImage, posX * 30, posY * 30 + 3);
        } else if (game.getPacMan().getDir() == Direction.DOWN) {
            drawPosition(g, pacManDownImage, posX * 30, posY * 30 + 3);
        } else if (game.getPacMan().getDir() == Direction.LEFT) {
            drawPosition(g, pacManRightImage, posX * 30, posY * 30 + 3);
        } else if (game.getPacMan().getDir() == Direction.RIGHT) {
            drawPosition(g, pacManLeftImage, posX * 30, posY * 30 + 3);
        }
    }

    // MODIFIES: g
    // EFFECTS: draws Blinky to the game
    private void drawBlinky(Graphics g) {
        Ghost blinky = game.getListOfGhost().get(0);
        int posX = blinky.getPos().getPosX();
        int posY = blinky.getPos().getPosY();
        if (blinky.getWeak()) {
            drawPosition(g, weakGhostImage, posX * 30, posY * 30);
        } else {
            drawPosition(g, blinkyImage, posX * 30, posY * 30);
        }
    }

    // MODIFIES: g
    // EFFECTS: draws Pinky to the game
    private void drawPinky(Graphics g) {
        Ghost pinky = game.getListOfGhost().get(1);
        int posX = pinky.getPos().getPosX();
        int posY = pinky.getPos().getPosY();
        if (pinky.getWeak()) {
            drawPosition(g, weakGhostImage, posX * 30, posY * 30);
        } else {
            drawPosition(g, pinkyImage, posX * 30, posY * 30);
        }
    }

    // MODIFIES: g
    // EFFECTS: draws Inky to the game
    private void drawInky(Graphics g) {
        Ghost inky = game.getListOfGhost().get(2);
        int posX = inky.getPos().getPosX();
        int posY = inky.getPos().getPosY();
        if (inky.getWeak()) {
            drawPosition(g, weakGhostImage, posX * 30, posY * 30);
        } else {
            drawPosition(g, inkyImage, posX * 30, posY * 30);
        }
    }

    // MODIFIES: g
    // EFFECTS: draws Clyde to the game
    private void drawClyde(Graphics g) {
        Ghost clyde = game.getListOfGhost().get(3);
        int posX = clyde.getPos().getPosX();
        int posY = clyde.getPos().getPosY();
        if (clyde.getWeak()) {
            drawPosition(g, weakGhostImage, posX * 30, posY * 30);
        } else {
            drawPosition(g, clydeImage, posX * 30, posY * 30);
        }
    }

    // MODIFIES: g
    // EFFECTS: draws a ghost that's added to the game
    private void drawExtraGhosts(Graphics g) {
        for (int i = 4; i < game.getListOfGhost().size(); i++) {
            int posX = game.getListOfGhost().get(i).getPos().getPosX();
            int posY = game.getListOfGhost().get(i).getPos().getPosY();
            if (game.getListOfGhost().get(i).getWeak()) {
                drawPosition(g, weakGhostImage, posX * 30, posY * 30);
            } else {
                drawPosition(g, clydeImage, posX * 30, posY * 30);
            }
        }

    }

    // MODIFIES: g
    // EFFECTS: draws the wall
    private void drawWall(Graphics g) {
        int[][] wall = game.getMap().getWalls();
        for (int i = 0; i < game.getMap().getWalls().length; i++) {
            int posX = wall[i][1];
            int posY = wall[i][0];
            drawPosition(g, wallImage, posX * 30, posY * 30);
        }
    }

    // MODIFIES: g
    // EFFECTS: draws the power ups
    private void drawPowerUp(Graphics g) {
        int[][] powerUp = game.getPower().getPowerUps();
        for (int i = 0; i < game.getPower().getPowerUps().length; i++) {
            int posX = powerUp[i][1];
            int posY = powerUp[i][0];
            drawPosition(g, powerUpImage, posX * 30 + 2, posY * 30 + 2);
        }
    }

    // MODIFIES: g
    // EFFECTS: draws the pellets
    private void drawPellet(Graphics g) {
        int[][] pellet = game.getPellets().getPellet();
        for (int i = 0; i < game.getPellets().getPellet().length; i++) {
            int posX = pellet[i][1];
            int posY = pellet[i][0];
            drawPosition(g, pelletImage, posX * 30 + 13, posY * 30 + 13);
        }
    }


    //MODIFIES: this
    //EFFECTS: the actual method that puts the input on the terminal screen.
    private void drawPosition(Graphics g, ImageIcon image, int x, int y) {
        image.paintIcon(this, g, x, y);
    }

    //MODIFIES: this
    //EFFECTS: loads all the images from the Images folder.
    private void loadImages() {
        String sep = System.getProperty("file.separator");
        String sys = System.getProperty("user.dir");
        blinkyImage = new ImageIcon(sys + sep + "data" + sep + "Images" + sep + "blinky.png");
        pinkyImage = new ImageIcon(sys + sep + "data" + sep + "Images" + sep + "pinky.png");
        inkyImage = new ImageIcon(sys + sep + "data" + sep + "Images" + sep + "inky.png");
        clydeImage = new ImageIcon(sys + sep + "data" + sep + "Images" + sep + "clyde.png");
        pacManUpImage = new ImageIcon(sys + sep + "data" + sep + "Images" + sep + "pacmanup.png");
        pacManDownImage = new ImageIcon(sys + sep + "data" + sep + "Images" + sep + "pacmandown.png");
        pacManLeftImage = new ImageIcon(sys + sep + "data" + sep + "Images" + sep + "pacmanleft.png");
        pacManRightImage = new ImageIcon(sys + sep + "data" + sep + "Images" + sep + "pacmanright.png");
        pelletImage = new ImageIcon(sys + sep + "data" + sep + "Images" + sep + "pacmanpellet.png");
        powerUpImage = new ImageIcon(sys + sep + "data" + sep + "Images" + sep + "pacmanpowerup.png");
        weakGhostImage = new ImageIcon(sys + sep + "data" +  sep + "Images" + sep + "weakghost.png");
        wallImage = new ImageIcon(sys + sep + "data" + sep + "Images" + sep + "pacmanwall.png");
    }

    public void setGame(PacManGame game) {
        this.game = game;
    }


}
