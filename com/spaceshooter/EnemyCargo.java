package com.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class EnemyCargo extends Enemy {
	//Cargo enemies shoot two missiles once they pass the player.
	private boolean hasShot;
	private EnemyManager enemyManager;
	public EnemyCargo(EnemyManager enemyManager)
	{
		super(100,ResourceManager.EnemyCargo1);
		hasShot = false;
		this.enemyManager = enemyManager;
	}

	public void updateEnemy(Player player, BulletManager bulletManager) {
		setY(getY() - 400 * Gdx.graphics.getDeltaTime());
		
		if (player != null) {
			if (getY() < player.getY() && !hasShot) {
				enemyManager.addEnemy(new EnemyMissile(),getX(),getY());
				enemyManager.addEnemy(new EnemyMissile(),getX() + getWidth(),getY());
				hasShot = true;
			}
		}
			
	}
}