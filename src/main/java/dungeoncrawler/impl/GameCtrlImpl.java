package dungeoncrawler.impl;

import dungeoncrawler.*;
import textio.TextIO;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class GameCtrlImpl implements GameCtrl {
    private int encounterCount = 0;
    private Random random = new Random();

    @Override
    public void runGame(Dungeon dungeon, TextIO io) {
        io.put("Welcome to the Dungeon Crawler game! The goal is to avoid encountering the monster more than twice.");

        Entity player = new SimpleEntity("Player", null);
        Entity computer = new SimpleEntity("Monster", new RandomEntityCtrl());

        List<Location> locations = dungeon.getLocations();
        Location playerLocation = locations.get(random.nextInt(locations.size()));
        Location computerLocation = locations.get(random.nextInt(locations.size()));

        player.setLocation(playerLocation);
        computer.setLocation(computerLocation);

        playerLocation.addEntity(player);
        computerLocation.addEntity(computer);

        while (encounterCount < 3) {
            io.put("You are in " + playerLocation.getName());
            io.put("Available corridors:");

            List<Portal> corridors = playerLocation.getPortals();
            List<String> directions = List.of("west", "east", "north", "south");
            List<String> usedDirections = new ArrayList<>();
            for (int i = 0; i < corridors.size(); i++) {
                String direction;
                do {
                    direction = directions.get(random.nextInt(directions.size()));
                } while (usedDirections.contains(direction));
                usedDirections.add(direction);
                io.put((i + 1) + ": Corridor going " + direction);
            }

            int corridorIndex = -1;
            while (corridorIndex < 0 || corridorIndex >= corridors.size()) {
                String input = io.get("Enter the number of the corridor to move: ");
                try {
                    corridorIndex = Integer.parseInt(input) - 1;
                } catch (NumberFormatException e) {
                    io.put("Invalid input. Please enter a number between 1 and " + corridors.size());
                }
            }

            Portal chosenCorridor = corridors.get(corridorIndex);

            player.move(chosenCorridor);
            playerLocation = player.getLocation();

            //  empty line shift
            io.put("");

            io.put("You moved to " + playerLocation.getName());

            computer.getController().control(computer);
            computerLocation = computer.getLocation();

            if (playerLocation.equals(computerLocation)) {
                encounterCount++;
                io.put("You encountered the Monster!! you've met the monster: " + encounterCount);
            }

            if (encounterCount >= 3) {
                io.put("Game over! You lost.");
                break;
            }
        }
    }
}
