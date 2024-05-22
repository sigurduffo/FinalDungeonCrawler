package dungeoncrawler.impl;

import dungeoncrawler.Action;
import dungeoncrawler.Entity;
import dungeoncrawler.Portal;

public class Move implements Action {
	private Entity entity;
	private Portal portal;

	public Move(Entity entity, Portal portal) {
		this.entity = entity;
		this.portal = portal;
	}

	@Override
	public void execute() {
		entity.move(portal);
	}

	@Override
	public String getDescription() {
		return "Move through " + portal.getName() + " to " + portal.getDestination().getName();
	}
}
