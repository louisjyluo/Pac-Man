package ui;

import java.io.FileNotFoundException;

//Main class, the code starts here.
public class Main {
    public static void main(String[] args) throws Exception {
        try {
            TerminalGame game = new TerminalGame();
            game.start();
        } catch (FileNotFoundException e) {
            System.out.println("No files found");
        }

    }
}
