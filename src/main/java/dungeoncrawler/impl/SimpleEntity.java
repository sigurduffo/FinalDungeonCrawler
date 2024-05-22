package dungeoncrawler.impl;

import dungeoncrawler.Action;
import dungeoncrawler.Entity;
import dungeoncrawler.EntityCtrl;
import dungeoncrawler.Location;
import dungeoncrawler.Portal;

import java.util.ArrayList;
import java.util.List;

public class SimpleEntity implements Entity {
	private String name;
	private Location location;
	private EntityCtrl controller;

	public SimpleEntity(String name, EntityCtrl controller) {
		this.name = name;
		this.controller = controller;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Location getLocation() {
		return location;
	}

	@Override
	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public void move(Portal portal) {
		moveToLocation(portal.getDestination());
	}

	@Override
	public void moveToLocation(Location location) {
		if (this.location != null) {
			((SimpleLocation) this.location).removeEntity(this);
		}
		this.location = location;
		if (location != null) {
			((SimpleLocation) location).addEntity(this);
		}
	}

	@Override
	public List<Action> getPossibleActions() {
		List<Action> actions = new ArrayList<>();
		for (Portal portal : location.getPortals()) {
			actions.add(new Move(this, portal));
		}
		return actions;
	}

	@Override
	public EntityCtrl getController() {
		return controller;
	}
}
