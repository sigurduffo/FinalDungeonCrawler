package dungeoncrawler.impl;

import dungeoncrawler.Location;
import dungeoncrawler.Portal;
import dungeoncrawler.Entity;

import java.util.ArrayList;
import java.util.List;

public class SimpleLocation implements Location {
	private String name;
	private String description;
	private List<Portal> portals;
	private List<Entity> entities;

	public SimpleLocation(String name, String description) {
		this.name = name;
		this.description = description;
		this.portals = new ArrayList<>();
		this.entities = new ArrayList<>();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public List<Portal> getPortals() {
		return portals;
	}

	@Override
	public List<Entity> getEntities() {
		return List.of();
	}

	@Override
	public void addPortal(Portal portal) {
		portals.add(portal);
	}

	@Override
	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	@Override
	public void removeEntity(Entity entity) {
		entities.remove(entity);
	}
}
