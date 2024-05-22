package dungeoncrawler.impl;

import dungeoncrawler.Dungeon;
import dungeoncrawler.Location;

import java.util.List;

public class SimpleDungeon implements Dungeon {
	private List<Location> locations;

	public SimpleDungeon(List<Location> locations) {
		this.locations = locations;
	}

	@Override
	public List<Location> getLocations() {
		return locations;
	}
}
