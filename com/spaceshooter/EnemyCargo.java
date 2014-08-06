package com.spaceshooter;

import com.badlogic.gdx.Gdx;

//Cargo enemies shoot two missiles once they pass the player. They have a lot of health and they are quick.
public class EnemyCargo extends Enemy {
	
	private boolean hasShot;
	private EnemyManager enemyManager;
	public EnemyCargo(EnemyManager enemyManager) {
		super(100,ResourceManager.EnemyCargo1); // 100 initial health
		hasShot = false;
		this.enemyManager = enemyManager;
	}

	public void updateEnemy(Player player, BulletManager bulletManager) {
		setY(getY() - 300 * Gdx.graphics.getDeltaTime());
		if (player != null) {
			// Once the ship passes the player (y direction) it fires its missiles. 
			if (getY() < player.getY() && !hasShot) {
				enemyManager.addEnemy(new EnemyMissile(),getX(),getY());
				enemyManager.addEnemy(new EnemyMissile(),getX() + getWidth(),getY());
				hasShot = true;
			}
		}
			
	}
}