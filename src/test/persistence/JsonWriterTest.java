package persistence;

import model.PacManGame;
import org.junit.jupiter.api.Test;

import javax.management.monitor.GaugeMonitor;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

//Test for JsonWriter
public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            PacManGame game = new PacManGame();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyFile() {
        try {
            PacManGame game = new PacManGame();
            game.getPower().getPowerUps();
            game.getPellets().getPellet();
            JsonWriter writer = new JsonWriter("./data/testWriterBeginningOfGame.json");
            writer.open();
            writer.write(game);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterBeginningOfGame.json");
            game = reader.read();

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            PacManGame game = new PacManGame();
            game.getPower().getPowerUps();
            game.getPellets().getPellet();
            game.getPacMan().setBody(10,9);
            game.getListOfGhost().get(0).setPos(10,5);
            game.getListOfGhost().get(0).setWeakGhost(true);
            JsonWriter writer = new JsonWriter("./data/testWriterFile.json");
            writer.open();
            writer.write(game);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterFile.json");
            game = reader.read();
            assertEquals(10, game.getPacMan().getPos().getPosX());
            assertEquals(10, game.getPacMan().getPos().getPosY());
            assertEquals(10, game.getListOfGhost().get(1).getPos().getPosX());
            assertEquals(5, game.getListOfGhost().get(0).getPos().getPosY());
            assertTrue(game.getListOfGhost().get(0).getWeak());
            assertFalse(game.getListOfGhost().get(2).getWeak());
            assertEquals(107, game.getPellets().getPellet().length);
            assertEquals(4, game.getPower().getPowerUps().length);
            assertEquals(0, game.getPowerUpDurationTimer());


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
