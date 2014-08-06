package com.spaceshooter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;

//EnemyManager class handles and updates all enemies. 
public class EnemyManager {
	public List<Enemy> enemyList;
	private List<Enemy> enemyQueue; /*holds references to enemies that need to be added.
									 since you cannot add while iterating through a list */
	private float enemyTimer;
	private boolean collision = false;
	private final int MIDDLE_LEFT = SpaceShooter.getLeftBound()/2;
	private final int MIDDLE_RIGHT = SpaceShooter.getRightBound()/2;
	private float enemyTimerLimit; //The number the enemyTimer reaches before it goes back to zero.
	EnemyManager() {
		enemyList = new ArrayList<Enemy>();
		enemyQueue = new ArrayList<Enemy>();
		enemyTimer = 0.0f;
		enemyTimerLimit = 1.1f;
	}

	public void update(Player player, ScoreHandler scorehandler,
		BulletManager bulletManager) {
		enemyTimer += Gdx.graphics.getDeltaTime() / 5;
		
		// Enemies either have an initial x position of 0, MIDDLE_LEFT, or MIDDLE_RIGHT.
		int type = MathUtils.random(0,2);
		int x = 0;
		if (type == 1)
			x = MIDDLE_LEFT;
		else if (type == 2)
			x = MIDDLE_RIGHT;
		
		/*createEnemy(MathUtils.random(SpaceShooter.getLeftBound()/2,SpaceShooter.getRightBound()/2),
									SpaceShooter.getTopBound() + 100);*/
		if (scorehandler.getScore() < 20000)
			enemyTimerLimit = 1.1f;
		else if (scorehandler.getScore() > 20000 && scorehandler.getScore() < 40000)
			enemyTimerLimit = 1.0f;
		else if (scorehandler.getScore() > 40000 && scorehandler.getScore() < 60000)
			enemyTimerLimit = 1.0f;
		else if (scorehandler.getScore() > 60000 && scorehandler.getScore() < 80000)
			enemyTimerLimit = 0.9f;
		else if (scorehandler.getScore() > 100000 && scorehandler.getScore() < 120000)
			enemyTimerLimit = 0.8f;
		else if (scorehandler.getScore() > 120000 && scorehandler.getScore() < 140000)
			enemyTimerLimit = 0.8f;
		else if (scorehandler.getScore() > 140000 && scorehandler.getScore() < 160000)
			enemyTimerLimit = 0.7f;
		else if (scorehandler.getScore() > 160000 && scorehandler.getScore() < 200000)
			enemyTimerLimit = 0.7f;
		else if (scorehandler.getScore() > 200000 && scorehandler.getScore() < 300000) // SUPER HARD!!
			enemyTimerLimit = 0.6f;
		else if (scorehandler.getScore() > 300000 && scorehandler.getScore() < 400000) // SUPER HARD!!
			enemyTimerLimit = 0.6f;
		else if (scorehandler.getScore() > 400000 && scorehandler.getScore() < 500000) // SUPER HARD!!
			enemyTimerLimit = 0.5f;
		else if (scorehandler.getScore() > 600000 && scorehandler.getScore() < 700000) // SUPER HARD!!
			enemyTimerLimit = 0.4f;
		else if (scorehandler.getScore() > 700000) // SUPER HARD!!
			enemyTimerLimit = 0.3f;
		
		
		
		createEnemy(x,
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
					(e.getX() < SpaceShooter.getLeftBound() - e.getWidth()) ||
					(e.getX() > SpaceShooter.getRightBound() + e.getWidth()) )
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
		
		
		if (enemyTimer > enemyTimerLimit) {
			int rand = MathUtils.random(12);
			if (rand == 0) {
				// More randomization to vary up the game.
				int rand2 = MathUtils.random(2);
				if (rand2 == 0)
					EnemyPatterns.blockade(this,x, y,"EnemyTurret",1,4);
				else if (rand2 == 1)
					EnemyPatterns.blockade(this,x, y,"EnemyTurret",2,3);
				else if (rand2 == 2)
					EnemyPatterns.blockade(this,x, y,"EnemyTurret",2,2);
				
				
				enemyTimer = 0.0f;
			} else if (rand == 1) {
				int rand2 = MathUtils.random(2);
				if (rand2 == 0)
					EnemyPatterns.blockade(this,SpaceShooter.getLeftBound(), y,"EnemySpinner",4,2);
				else if (rand2 == 1)
					EnemyPatterns.blockade(this,x, y,"EnemySpinner",1,4);
				else if (rand2 == 2)
					EnemyPatterns.blockade(this,SpaceShooter.getLeftBound(), y,"EnemySpinner",4,1);
				
				enemyTimer = 0.0f;
			} 
			else if (rand == 2) {		
				if (x != MIDDLE_RIGHT)
					EnemyPatterns.blockade(this, x, y,"EnemyPopcorn",5,3);
				else
					EnemyPatterns.blockade(this, x, y,"EnemyPopcorn",3,3);
				
				enemyTimer = 0.0f;
			}
			else if (rand == 3) {
				
				int rand2 = MathUtils.random(2);
				if (rand2 == 0)
					EnemyPatterns.sineWave(this, x, y,20,"EnemyPopcorn");
				else if (rand2 == 1)
					EnemyPatterns.sineWave(this, x, y,15,"EnemyPopcorn");
				else if (rand2 == 2)
					EnemyPatterns.sineWave(this, x, y,25,"EnemyPopcorn");
				
				enemyTimer = 0.0f;
			}
			else if (rand == 4) {
				//EnemyPatterns.sineWave(this, x, y,2,"EnemyKiller1");
				
				int rand2 = MathUtils.random(2);
				if (rand2 == 0)
					EnemyPatterns.blockade(this,SpaceShooter.getLeftBound(), y,"EnemyKiller1",2,1);
				else if (rand2 == 1)
					EnemyPatterns.blockade(this,x, y,"EnemyKiller1",1,1);
				else if (rand2 == 2)
					EnemyPatterns.blockade(this,x, y,"EnemyKiller1",1,2);
				
				
				enemyTimer = 0.0f;
			}
			else if (rand == 5) {
				int rand2 = MathUtils.random(1);
				if (rand2 == 0)
					EnemyPatterns.sineWave(this,MIDDLE_LEFT, y,2,"EnemyKiller1");
				else if (rand2 == 1)
					EnemyPatterns.sineWave(this,MIDDLE_LEFT, y,1,"EnemyKiller1");
			
				enemyTimer = 0.0f;
			}
			else if (rand == 6) {
				EnemyRedship enemy = new EnemyRedship();
				enemy.setPosition(MIDDLE_LEFT, y);
				enemyList.add(enemy);
				enemyTimer = 0.0f;
			}
			else if (rand == 7) {
				int rand2 = MathUtils.random(1);
				if (rand2 == 0) {
					EnemyEye enemy = new EnemyEye();
					enemy.setPosition(x, y);
					enemyList.add(enemy);
					enemyTimer = 0.0f;
				}
				else {
					EnemyPatterns.blockade(this,SpaceShooter.getLeftBound(), y,"EnemyEye",4,1);
				}
				
			}
			else if (rand == 8) {
				int rand2 = MathUtils.random(8); //One eighth chance of a hard cargo spawn
				if (rand2 == 0) {
					EnemyCargo enemy = new EnemyCargo(this);
					enemy.setPosition(SpaceShooter.getLeftBound(), y);
					enemyList.add(enemy);
					
					EnemyCargo enemy2 = new EnemyCargo(this);
					enemy2.setPosition(SpaceShooter.getRightBound() - enemy2.getWidth(), y);
					enemyList.add(enemy2);
					
					enemyTimer = 0.0f;
				}
				else {
					EnemyCargo enemy = new EnemyCargo(this);
					enemy.setPosition(x, y);
					enemyList.add(enemy);
					enemyTimer = 0.0f;
				}
			}
			else if (rand == 9) {
				EnemyFactory enemy = new EnemyFactory(this);
				enemy.setPosition(x, y);
				enemyList.add(enemy);
				
				EnemyFactory enemy2 = new EnemyFactory(this);
				
				if (x == MIDDLE_LEFT)
					enemy2.setPosition(x + enemy2.getWidth() + 20, y);
				else
					enemy2.setPosition(x - enemy2.getWidth() - 20, y);
				
				enemyList.add(enemy2);
				
				enemyTimer = 0.0f;
			}
			else if (rand == 10) {
				int rand2 = MathUtils.random(1);
				if (rand2 == 0)
					EnemyPatterns.blockade(this,MIDDLE_LEFT, y,"EnemyHoming",5,1);
				else {
					EnemyHoming enemy = new EnemyHoming();
					enemy.setPosition(x, y);
					enemyList.add(enemy);
					enemyTimer = 0.0f;
				}
				
			}
			else if (rand == 11) {
				EnemyTongue enemy = new EnemyTongue(this);
				enemy.setPosition(0, y);
				enemyList.add(enemy);
				enemyTimer = 0.0f;
			}
			else if (rand == 12) {
				int rand2 = MathUtils.random(1);
				if (rand2 == 0) {
					EnemyPatterns.blockade(this,SpaceShooter.getLeftBound(), y,"EnemyMissileShip",3,1);
				}
				else {
					EnemyMissileShip enemy = new EnemyMissileShip(this);
					enemy.setPosition(x, y);
					enemyList.add(enemy);
					enemyTimer = 0.0f;
				}
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