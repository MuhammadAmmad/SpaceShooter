package com.spaceshooter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;

public class EnemyManager {
	public List<Enemy> enemyList;
	private List<Enemy> enemyQueue; /*holds references to enemies that need to be added.
									 since you cannot add while iterating through a list */
	private float enemyTimer;
	private boolean collision = false;
	EnemyManager() {
		enemyList = new LinkedList<Enemy>();
		enemyQueue = new LinkedList<Enemy>();
		enemyTimer = 0.0f;
	}

	public void update(Player player, ScoreHandler scorehandler,
		BulletManager bulletManager) {
		enemyTimer += Gdx.graphics.getDeltaTime() / 5;
		
		createEnemy(MathUtils.random(SpaceShooter.getLeftBound()/2,SpaceShooter.getRightBound()/2),
									SpaceShooter.getTopBound() + 100);
		
		ListIterator<Enemy> enemyIter = enemyList.listIterator();
		while (enemyIter.hasNext()) {
			Enemy e = enemyIter.next();
			e.updateEnemy(player, bulletManager);
			
			if (player != null) {
				if (Intersector.overlaps(e.getBoundingRectangle(),
						player.getBoundingRectangle()) == true) {
					
					if (!player.isBlinking) {
						
						/*
						 * In the future, check for enemy types and add a higher or
						 * lower score Depending on the enemy
						 */
						
						player.killPlayer();
						collision = true;
					}
	
				}
			}

			if ((e.getY() < SpaceShooter.getBottomBound() - e.getHeight()) || 
					(e.getY() > SpaceShooter.getTopBound() + e.getHeight() + 500) ||
					(e.getX() < SpaceShooter.getLeftBound() - e.getWidth() - 100) ||
					(e.getX() > SpaceShooter.getRightBound() + e.getWidth() + 100) )
				enemyIter.remove();
			else if (collision) {
				e.hit();
				
				if (e.isDead) {
					enemyIter.remove();
				}		
				
				collision = false;
			}

		}
		
		//Add enemies that are part of the queue now that we are not iterating.
		for (Enemy e : enemyQueue) {
			if (e != null)
				enemyList.add(e);
		}
		
		//Clear the queue:
		enemyQueue.clear();
		
	}
	public void addEnemy(Enemy enemy,float x,float y) {
		enemy.setPosition(x, y);
		enemyQueue.add(enemy);
	}
	
	public List<Enemy> getList() {
		return enemyList;
	}
	
	public void createEnemy(float x, float y) {
		if (enemyTimer > 0.7f) {
			int rand = MathUtils.random(11);
			rand = 0;
			if (rand == 0) {
				//Enemy enemy = new EnemyTurret();
				EnemyMissileShip enemy = new EnemyMissileShip(this);
				enemy.setPosition(x, y);
				enemyList.add(enemy);
				enemyTimer = 0.0f;
			} else if (rand == 1) {
				EnemyPatterns.blockade(this, x, y,"EnemySpinner",5,0);
				enemyTimer = 0.0f;
			} 
			else if (rand == 2) {
				EnemyPatterns.blockade(this, x, y,"EnemyPopcorn",5,2);
				enemyTimer = 0.0f;
			}
			else if (rand == 3) {
				EnemyPatterns.sineWave(this, x, y,15,"EnemyPopcorn");
				enemyTimer = 0.0f;
			}
			else if (rand == 4) {
				//EnemyPatterns.sineWave(this, x, y,2,"EnemyKiller1");
				EnemyPatterns.blockade(this, x, y,"EnemyKiller1",3,0);
				enemyTimer = 0.0f;
			}
			else if (rand == 5) {
				EnemyPatterns.sineWave(this, x, y,2,"EnemyKiller1");
				enemyTimer = 0.0f;
			}
			else if (rand == 6) {
				EnemyRedship enemy = new EnemyRedship();
				enemy.setPosition(x, y);
				enemyList.add(enemy);
				enemyTimer = 0.0f;
			}
			else if (rand == 7) {
				EnemyEye enemy = new EnemyEye();
				enemy.setPosition(x, y);
				enemyList.add(enemy);
				enemyTimer = 0.0f;
			}
			else if (rand == 8) {
				EnemyCargo enemy = new EnemyCargo(this);
				enemy.setPosition(x, y);
				enemyList.add(enemy);
				enemyTimer = 0.0f;
			}
			else if (rand == 9) {
				EnemyFactory enemy = new EnemyFactory(this);
				enemy.setPosition(x, y);
				enemyList.add(enemy);
				
				EnemyFactory enemy2 = new EnemyFactory(this);
				enemy2.setPosition(x + enemy2.getWidth() + 20, y);
				enemyList.add(enemy2);
				
				enemyTimer = 0.0f;
			}
			else if (rand == 10) {
			
				EnemyHoming enemy = new EnemyHoming();
				enemy.setPosition(x, y);
				enemyList.add(enemy);
				enemyTimer = 0.0f;
			}
			else if (rand == 11) {
				EnemyTongue enemy = new EnemyTongue(this);
				enemy.setPosition(x, y);
				enemyList.add(enemy);
			}

			
		}

	}

	public void draw(SpriteBatch batch) {
		for (Enemy e : enemyList) {
			if (e != null) {
				e.draw(batch);
				
				if (e instanceof EnemyMissile)
					((EnemyMissile) e).drawParticle(batch);
				
				e.processHits();
				
			}
		}
		
	}
	
	public void clear() {
		enemyList.clear();
	}
}