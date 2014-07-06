package com.spaceshooter;

import com.badlogic.gdx.Gdx;

public class EnemyHoming extends Enemy {
	private final int HOMING_SPEED;
	private float velocityX;
	private float velocityY;
	EnemyHoming() {
		super(3);
		HOMING_SPEED = 200;
		velocityX = 0.0f;
		velocityY = 0.0f;
	}

	public void updateEnemy(Player player, BulletManager bulletManager) {
		if (player != null) {
			// Point the enemy towards the player.
			float angle = (float) Math.atan2(getY() - player.getY(), getX()
					- player.getX());
			float degrees = (float) (angle * (180 / Math.PI));
			setRotation(degrees - 90);
			/*
			 * translate(100 * Gdx.graphics.getDeltaTime() * (float)
			 * Math.cos(degrees - 90), 100 * Gdx.graphics.getDeltaTime() * (float)
			 * Math.sin(degrees - 90));
			 */
			// this.setY(this.getY() - 150 * Gdx.graphics.getDeltaTime());
	
			// Move the enemy towards the player. Clean this up *
			float directionX = getX() - player.getX();
			float directionY = getY() - player.getY();
			double sq = Math
					.sqrt(directionX * directionX + directionY * directionY);
	
			velocityX = (float) (directionX
					* (HOMING_SPEED * Gdx.graphics.getDeltaTime()) / sq);
			velocityY = (float) (directionY
					* (HOMING_SPEED * Gdx.graphics.getDeltaTime()) / sq);
			
			translate(-velocityX, -velocityY);
		}
		else {
			translate(-velocityX,-velocityY);
		}

		

	}
}