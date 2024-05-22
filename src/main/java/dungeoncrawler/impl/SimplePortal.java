package dungeoncrawler.impl;

import dungeoncrawler.Location;
import dungeoncrawler.Portal;

public class SimplePortal implements Portal {
	private String name;
	private Location destination;

	public SimplePortal(String name, Location destination) {
		this.name = name;
		this.destination = destination;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Location getDestination() {
		return destination;
	}
}
