package com.spaceshooter;

import com.badlogic.gdx.Gdx;

public class EnemyTurret extends Enemy {
	private float shootTimer;
	private float degrees;
	EnemyTurret() {
		super(5);
		shootTimer = 0;
		setOrigin(getWidth()/2,getHeight()/2);
		degrees = 0.0f;
	}

	public void updateEnemy(Player player, BulletManager bulletManager) {
		
		if (player != null) {
			// Point the enemy towards the player.
			float angle = (float) Math.atan2(getY() - player.getY(), getX()
					- player.getX());
			float degrees = (float) (angle * (180 / Math.PI));
			setRotation(degrees - 90);
			super.updateEnemy(player, bulletManager);
	
			// Make a bullet
			shootTimer += (Gdx.graphics.getDeltaTime() / 5);
			if (shootTimer > 0.4f) {
				
				float directionX = getX() - player.getX();
				float directionY = getY() - player.getY();
				double sq = Math
						.sqrt(directionX * directionX + directionY * directionY);
	
				float velocityX = (float) (directionX
						* (SpaceShooter.getBulletSpeed()) / sq);
				float velocityY = (float) (directionY
						* (SpaceShooter.getBulletSpeed()) / sq);
				
				bulletManager.getList().add(new EnemyBulletBasic(getX(), getY(),velocityX,velocityY,
						0));
				shootTimer = 0f;
			}
		}
		else {
			setRotation(degrees - 90);
			super.updateEnemy(player, bulletManager);
		}
	}
}