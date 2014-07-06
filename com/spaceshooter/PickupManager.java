package com.spaceshooter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class PickupManager {
	private List<Pickup> pickupList;
	
	public PickupManager() {
		pickupList = new LinkedList<Pickup>();
	}
	public List<Pickup> getList() {
		return pickupList;
	}
	public void draw(SpriteBatch batch) {
		for (Pickup p : pickupList) {
			p.draw(batch);
		}
	}
	public void update() {
		ListIterator<Pickup> pickupIter = pickupList.listIterator();
		while (pickupIter.hasNext()) {
			Pickup p = pickupIter.next();
			p.update();
			
			if (p.getY() < SpaceShooter.getBottomBound() - p.getHeight())
				pickupIter.remove();
				
		}
	}
	public void clear() {
		pickupList.clear();
	}
	public void dispose() {
		pickupList.clear();
	}
}