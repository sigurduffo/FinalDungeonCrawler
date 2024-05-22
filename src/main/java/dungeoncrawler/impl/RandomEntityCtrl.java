package dungeoncrawler.impl;

import dungeoncrawler.Entity;
import dungeoncrawler.EntityCtrl;
import dungeoncrawler.Portal;

import java.util.List;
import java.util.Random;

public class RandomEntityCtrl implements EntityCtrl {
	private Random random = new Random();

	@Override
	public void control(Entity entity) {
		List<Portal> portals = entity.getLocation().getPortals();
		if (!portals.isEmpty()) {
			Portal portal = portals.get(random.nextInt(portals.size()));
			entity.move(portal);
		}
	}
}
