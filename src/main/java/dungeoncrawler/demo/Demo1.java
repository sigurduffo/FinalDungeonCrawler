package dungeoncrawler.demo;

import dungeoncrawler.*;
import dungeoncrawler.impl.*;
import dungeoncrawler.impl.GameCtrlImpl;
import dungeoncrawler.impl.RandomEntityCtrl;
import textio.*;

import java.util.List;
import java.util.Random;

public class Demo1 {
    public static void main(String[] args) {
        // TextIO
        TextIO io = new SystemTextIO();

        // Display game description
        displayGameDescription(io);

        // Generate a random dungeon
        Dungeon dungeon = SimpleDungeonBuilder.generateRandomDungeon();

        // Add random entities to random locations
        addRandomEntities(dungeon);

        // Run the game
        GameCtrl gameCtrl = new GameCtrlImpl();
        gameCtrl.runGame(dungeon, io);
    }

    // Method to display game description
    private static void displayGameDescription(TextIO io) {
        io.put("Welcome to my Dungeon Crawler Game!");
        io.put("In this game, you'll explore a randomly generated dungeon.");
        io.put("Your goal is to navigate through the dungeon and avoid encounters with the monster hiding in the dungeon.");
        io.put("You'll be presented with a list of available corridor at each room.");
        io.put("Enter the number associated with the corridor to move to the next room.");
        io.put(""); // Add an empty line for spacing
    }

    // Method to add random entity to a random location
    private static void addRandomEntities(Dungeon dungeon) {
        Random random = new Random();

        for (Location location : dungeon.getLocations()) {
            EntityCtrl controller = new RandomEntityCtrl();
            Entity entity = new SimpleEntity("Catcher" + (random.nextInt(100) + 1), controller);

            location.addEntity(entity);
            entity.setLocation(location);
        }
    }
}
