package ui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import model.*;

import java.awt.*;
import java.io.IOException;

public class TerminalGame {
    private PacManGame game;
    private Screen screen;
    private WindowBasedTextGUI endGui;



    public void start() throws IOException, InterruptedException {
        screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();

        TerminalSize terminalSize = screen.getTerminalSize();
        int maxX = terminalSize.getColumns() / 2;
        int maxY = terminalSize.getRows() - 1;
        game = new PacManGame(maxX, maxY);
        beginTicks();
    }

    private void beginTicks() throws IOException, InterruptedException {
        while (!game.isEnded() || endGui.getActiveWindow() != null) {
            tick();
            Thread.sleep(1000L / game.getTickPerSec());
        }

        System.exit(0);
    }

    private void tick() throws IOException {
        handleUserInput();

        game.tick();

        screen.setCursorPosition(new TerminalPosition(0, 0));
        screen.clear();
        render();
        screen.refresh();

        screen.setCursorPosition(new TerminalPosition(screen.getTerminalSize().getColumns() - 1, 0));
    }

    private void handleUserInput() throws IOException {
        KeyStroke stroke = screen.pollInput();

        if (stroke == null) {
            return;
        }

        if (stroke.getCharacter() != null) {
            return;
        }

        Direction dir = directionFrom(stroke.getKeyType());

        if (dir == null) {
            return;
        }

        game.getPacMan().setDirection(dir);
    }

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

    private void render() {
        if (game.isEnded()) {
            if (endGui == null) {
                drawEndScreen();
            }

            return;
        }

        drawScore();
        drawPacMan();
        drawBlinky();
        drawInky();
        drawPinky();
        drawClyde();
    }

    private void drawEndScreen() {
        endGui = new MultiWindowTextGUI(screen);

        new MessageDialogBuilder()
                .setTitle("Game over!")
                .setText("You finished with a score of " + game.getScore() + "!")
                .addButton(MessageDialogButton.Close)
                .build()
                .showDialog(endGui);
    }

    private void drawScore() {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(TextColor.ANSI.BLUE);
        text.putString(1, 0, "Score: ");

        text = screen.newTextGraphics();
        text.setForegroundColor(TextColor.ANSI.WHITE);
        text.putString(8, 0, String.valueOf(game.getScore()));
    }

    private void drawPacMan() {
        PacMan pacMan = game.getPacMan();

        drawPosition(pacMan.getPos(), TextColor.ANSI.WHITE, '\u2588', true);

    }

    private void drawBlinky() {
        Ghost blinky = game.getBlinky();

        drawPosition(blinky.getPos(), TextColor.ANSI.RED, '\u2588', true);

    }

    private void drawInky() {
        Ghost blinky = game.getInky();

        drawPosition(blinky.getPos(), TextColor.ANSI.BLUE, '\u2588', true);

    }

    private void drawPinky() {
        Ghost blinky = game.getPinky();

        drawPosition(blinky.getPos(), TextColor.ANSI.MAGENTA, '\u2588', true);

    }

    private void drawClyde() {
        Ghost blinky = game.getClyde();

        drawPosition(blinky.getPos(), TextColor.ANSI.YELLOW, '\u2588', true);

    }

    private void drawPosition(Position pos, TextColor color, char c, boolean wide) {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(color);
        text.putString(pos.getPosX() * 2, pos.getPosY() + 1, String.valueOf(c));


    }
}