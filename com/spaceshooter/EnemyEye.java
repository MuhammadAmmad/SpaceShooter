package com.spaceshooter;

import com.badlogic.gdx.Gdx;

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
			float angle = (float) Math.atan2(getY() - player.getY(), getX()
					- player.getX());
			float degrees = (float) (angle * (180 / Math.PI));

			float directionX = getX() - player.getX();
			float directionY = getY() - player.getY();

			double sq = Math.sqrt(directionX * directionX + directionY
					* directionY);

			float velocityX = (float) (directionX
					* (HOMING_SPEED * Gdx.graphics.getDeltaTime()) / sq);
			float velocityY = (float) (directionY
					* (HOMING_SPEED * Gdx.graphics.getDeltaTime()) / sq);

			float playerDist = (float) Math.sqrt(Math.pow(
					getX() - player.getX(), 2)
					+ Math.pow(getY() - player.getY(), 2));

			if (isAngry == false) {
				translate(-velocityX, -velocityY);
				setRotation(degrees - 90);
				if (playerDist < 200 && !isAngry) {
					isAngry = true;
				}
			} else if (isAngry == true) {
				if (getX() > player.getX())
					rotate(-Gdx.graphics.getDeltaTime() * 100);
				else
					rotate(Gdx.graphics.getDeltaTime() * 100);

				bulletTimer += (Gdx.graphics.getDeltaTime() / 5);

				if (bulletTimer > 0.03f) {
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
			this.setY(this.getY() - 10 * Gdx.graphics.getDeltaTime());
		}

		else
			this.setY(this.getY() - 150 * Gdx.graphics.getDeltaTime());

	}
}