package com.spaceshooter;

import com.badlogic.gdx.Gdx;

//Eye enemy follows the player, and, once it reaches a certain distance, it shoots off bullets in a circular pattern.
public class EnemyEye extends Enemy {
	private float bulletTimer;
	private final int HOMING_SPEED;
	private boolean isAngry;

	EnemyEye() {
		super(50, ResourceManager.EnemyEye);
		setOrigin(getWidth() / 2, getHeight() / 2);
		bulletTimer = 0f;
		isAngry = false;
		HOMING_SPEED = 150;
		setOrigin(getWidth() / 2, getHeight() / 2);
	}

	public void updateEnemy(Player player, BulletManager bulletManager) {

		if (player != null) {
			
			float playerDist = getDistance(player);	
			if (isAngry == false) {
				moveTowards(HOMING_SPEED,player);
				rotateTowards(player);
				if (playerDist < 200 && !isAngry) {
					isAngry = true;
				}
			} 
			else if (isAngry == true) {
				if (getX() > player.getX())
					rotate(-Gdx.graphics.getDeltaTime() * 100);
				else
					rotate(Gdx.graphics.getDeltaTime() * 100);

				bulletTimer += (Gdx.graphics.getDeltaTime() / 5);

				if (bulletTimer > 0.03f) {
					// Creates a circular bullet pattern.
					bulletManager
							.getList()
							.add(new EnemyBulletBasic(
									getX() + getWidth() / 2,
									getY() + getHeight() / 2,
									(-1f * SpaceShooter.getBulletSpeed())
											* (float) Math.cos(Math
													.toRadians(getRotation() - 90)),
									(-1f * SpaceShooter.getBulletSpeed())
											* (float) Math.sin(Math
													.toRadians(getRotation() - 90)),
									0));
					bulletTimer = 0;
				}
			}
			setY(this.getY() - 10 * Gdx.graphics.getDeltaTime());
		}

		else
			setY(this.getY() - 150 * Gdx.graphics.getDeltaTime());

	}
}