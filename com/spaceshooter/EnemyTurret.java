package com.spaceshooter;

import com.badlogic.gdx.Gdx;

public class EnemyTurret extends Enemy {
	private float shootTimer;
	EnemyTurret() {
		super(5);
		shootTimer = 0;
	}

	public void updateEnemy(Player player, BulletManager bulletManager) {
		
		if (player != null) {
			// Point the enemy towards the player.
			rotateTowards(player);
			super.updateEnemy(player, bulletManager);
	
			// Make a bullet
			shootTimer += (Gdx.graphics.getDeltaTime() / 5);
			if (shootTimer > 0.4f) {
				
				float directionX = getX() - player.getX();
				float directionY = getY() - player.getY();
				double sq = Math
						.sqrt(directionX * directionX + directionY * directionY);
	
				float velocityX = (float) (directionX
						* (SpaceShooter.getBulletSpeed()*1.5f) / sq);
				float velocityY = (float) (directionY
						* (SpaceShooter.getBulletSpeed()*1.5f) / sq);
				
				bulletManager.getList().add(new EnemyBulletBasic(getX(), getY(),velocityX,velocityY,
						0));
				shootTimer = 0f;
			}
		}
		else {
			super.updateEnemy(player, bulletManager);
		}
	}
}