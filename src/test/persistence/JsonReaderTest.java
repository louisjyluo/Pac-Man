package persistence;

import model.PacManGame;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            PacManGame game = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderStartingState() {
        JsonReader reader = new JsonReader("./data/beginningOfGame.json");
        try {
            PacManGame game = reader.read();
            assertEquals(10, game.getPacMan().getPos().getPosX());
            assertEquals(10, game.getPacMan().getPos().getPosY());
            assertEquals(10, game.getListOfGhost().get(1).getPos().getPosX());
            assertEquals(7, game.getListOfGhost().get(1).getPos().getPosY());
            assertFalse(game.getListOfGhost().get(0).getWeak());
            assertFalse(game.getListOfGhost().get(2).getWeak());
            assertEquals(107, game.getPellets().getPellet().length);
            assertEquals(4, game.getPower().getPowerUps().length);
            assertEquals(0, game.getPowerUpDurationTimer());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderRandomSave() {
        JsonReader reader = new JsonReader("./data/testerFile.json");
        try {
            PacManGame game = reader.read();
            assertEquals(13, game.getPacMan().getPos().getPosX());
            assertEquals(8, game.getPacMan().getPos().getPosY());
            assertEquals(9, game.getListOfGhost().get(1).getPos().getPosX());
            assertEquals(7, game.getListOfGhost().get(1).getPos().getPosY());
            assertTrue(game.getListOfGhost().get(0).getWeak());
            assertTrue(game.getListOfGhost().get(2).getWeak());
            assertEquals(107, game.getPellets().getPellet().length);
            assertEquals(4, game.getPower().getPowerUps().length);
            assertEquals(102, game.getPowerUpDurationTimer());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
