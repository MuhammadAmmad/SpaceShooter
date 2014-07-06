package com.spaceshooter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;

public class BulletManager {
	private List<Bullet> bulletList;
	private Explosion explosion = null;
	float blowX = 0.0f, blowY = 0.0f;
	float blowTimer = 0.0f;
	private boolean collision = false;

	BulletManager() {
		bulletList = new LinkedList<Bullet>();
	}

	public void update(EnemyManager enemyManager, SpriteBatch batch,
		ScoreHandler scorehandler, Player player,PickupManager pickupManager) {
		// Where 'b' = bullet && 'e' = enemy
		if (player != null) {
			ListIterator<Bullet> bulletIter = bulletList.listIterator();
			while (bulletIter.hasNext()) {
				Bullet b = bulletIter.next();
				b.updateBullet(batch,enemyManager);
				ListIterator<Enemy> enemyIter = enemyManager.getList().listIterator();
				
				while (enemyIter.hasNext()) {
					Enemy e = enemyIter.next();
					if (Intersector.overlaps(b.getBoundingRectangle(), e.getBoundingRectangle()) == true
							&& b instanceof PlayerBullet) {
					
						e.hit(player.getWeaponLevel());
						
						if (e.isDead) {
							blowX = b.getX();
							blowY = b.getY();
							explosion = new Explosion(blowX, blowY);
							int rand = MathUtils.random(8);
							if (rand == 0) {
								/*pickupManager.getList().add(new CoinPickup(e.getX(),
										e.getY()));*/
								
								scorehandler.addScore(1000);
							}
							else if (rand == 1) {
								pickupManager.getList().add(new WeaponPickup(e.getX(),
										e.getY()));
							}
							else if (rand == 2) {
								pickupManager.getList().add(new MissilePickup(e.getX(),
										e.getY()));
							}
							enemyIter.remove();
						}
							
						
						collision = true; // tells that the bullet has collided w/
											// enemy
						if (e != null && !e.isInvincible)
							scorehandler.addScore(10); // Add 10pts to score.
						/*
						 * In the future, check for enemy types and add a higher or
						 * lower score Depending on the enemy
						 */
					} 
					else if (Intersector.overlaps(b
							.getBoundingRectangle(), player.getBoundingRectangle()) == true
							&& (!(b instanceof PlayerBullet))) {
				
						if (!(player.isBlinking || collision)) { //Why must I check the collision boolean?
							player.killPlayer();
							collision = true;
						}
						/*
						 * In the future, check for enemy types and add a higher or
						 * lower score Depending on the enemy
						 */
					}
	
				}
	
				if (b.getY() > SpaceShooter.getTopBound() || b.getY() < SpaceShooter.getBottomBound()) {
					bulletIter.remove();
				} 
				else if (collision) {
					bulletIter.remove();
					collision = false; // follows up and removes the bullet, then
										// resets boolean.
				}
			}
		}
		else {
			ListIterator<Bullet> bulletIter = bulletList.listIterator();
			while (bulletIter.hasNext()) {
				Bullet b = bulletIter.next();
				b.updateBullet(batch,enemyManager);
			}
		}

	}
	public List<Bullet> getList() {
		return bulletList;
	}
	
	public void draw(SpriteBatch batch) {
		// Looping through the Linked List with O(n).
		for (Bullet b : bulletList) {
			if (b != null && (!(b instanceof PlayerBullet))) //Don't draw the actual hitbox sprite
				b.draw(batch);
		}

		if (explosion != null)
			explosion.draw(batch);
	}
	public void clear() {
		bulletList.clear();
	}
	public void dispose() {
		bulletList.clear();
	}
}