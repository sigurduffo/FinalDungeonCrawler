package dungeoncrawler.impl;

import dungeoncrawler.Dungeon;
import dungeoncrawler.Location;
import dungeoncrawler.Portal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimpleDungeonBuilder {
	private final List<Location> allLocations;

	public SimpleDungeonBuilder() {
		this.allLocations = new ArrayList<>();
	}

	public Location createLocation(String name, String description) {
		Location res = new SimpleLocation(name, description);
		allLocations.add(res);
		return res;
	}

	public void createPortal(String name, Location from, Location to) {
		SimplePortal portal = new SimplePortal(name, to);
		((SimpleLocation) from).addPortal(portal);
	}

	public Dungeon build() {
		return new SimpleDungeon(allLocations);
	}

	public static Dungeon generateRandomDungeon() {
		SimpleDungeonBuilder builder = new SimpleDungeonBuilder();
		Random random = new Random();
		int numLocations = random.nextInt(11) + 10; // Random number of locations between 10 and 20

		List<Location> locations = new ArrayList<>();
		for (int i = 0; i < numLocations; i++) {
			locations.add(builder.createLocation("Room " + (i + 1), "This is room " + (i + 1)));
		}

		// Ensure every room is accessible by connecting each room to at least one other room
		for (int i = 0; i < numLocations - 1; i++) {
			builder.createPortal("Corridor", locations.get(i), locations.get(i + 1));
			builder.createPortal("Corridor", locations.get(i + 1), locations.get(i));
		}

		// Add additional random connections to make the dungeon more complex
		for (Location location : locations) {
			int numPortals = random.nextInt(4) + 1; // Each room has between 1 and 4 portals
			for (int j = 0; j < numPortals; j++) {
				Location target = locations.get(random.nextInt(locations.size()));
				if (!location.equals(target)) {
					builder.createPortal("Corridor", location, target);
				}
			}
		}

		return builder.build();
	}
}
