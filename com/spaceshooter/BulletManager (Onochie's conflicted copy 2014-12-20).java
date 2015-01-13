package com.spaceshooter;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;

public class BulletManager {
	private List<Bullet> bulletList; //LinkedList that handles all the bullets. 
	private Explosion explosion;
	float blowX, blowY; //X and Y position of the explosion when bullets collide. 
	float blowTimer;
	private boolean collision;
	/* Use a double LinkedList instead of an ArrayList to store Bullets to improve efficiency and make removing elements 
	  take constant time*/
	BulletManager() {
		bulletList = new LinkedList<Bullet>();
		blowX = 0.0f;
		blowY = 0.0f;
		blowTimer = 0.0f;
		collision = false;
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
						//Reduce the enemy's health by the specified amount determined by the player's level.
						e.hit(player.getWeaponLevel());
						
						if (e.isDead) {
							blowX = b.getX();
							blowY = b.getY();
							explosion = new Explosion(blowX, blowY);
							// Different enemies give out different score bonuses.	
							if (e instanceof EnemyTongue || e instanceof EnemyRedship || e instanceof EnemyCargo)
								scorehandler.addScore(3000); // 5k bonus for the hard ones. 
							else if (e instanceof EnemyFactory || e instanceof EnemyKiller1 || e instanceof EnemyEye
									|| e instanceof EnemyMissileShip)
								scorehandler.addScore(2000); // 3k bonus for the moderate ones. 
							else
								scorehandler.addScore(1000);
							// Power-ups will not drop every time. 
							int rand = MathUtils.random(8);
							if (rand == 0) {
								pickupManager.getList().add(new WeaponPickup(e.getX(),
										e.getY()));
							}
							else if (rand == 1) {
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
			if (b != null && (!(b instanceof PlayerBullet))) //Don't draw the actual sprite for the PlayerBullet, only the particle is drawn.
				b.draw(batch);
		}

		if (explosion != null)
			explosion.draw(batch); /*Having only one explosion active at a time eliminates the need for an ExplosionManager class and is
									not noticeable by the player.*/
	}
	public void clear() {
		bulletList.clear();
	}
	public void dispose() {
		clear();
	}
}