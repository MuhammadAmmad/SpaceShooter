package com.spaceshooter;

import com.badlogic.gdx.Gdx;
//Simple enemy, either moves with a sine wave pattern or straight down. 
public class EnemyPopcorn extends Enemy {
	private float bulletTimer;
	private float angle;
	private boolean wave;
	
	EnemyPopcorn() {
		super(5);
		bulletTimer = 0f;
		wave = false;
		angle = 0f;
	}

	EnemyPopcorn(boolean wave, float startAngle) {
		super(2);
		this.wave = wave;
		bulletTimer = 0f;
		angle = startAngle;
	}

	public void updateEnemy(Player player, BulletManager bulletManager) {
		bulletTimer += (Gdx.graphics.getDeltaTime() / 5);
		angle += Gdx.graphics.getDeltaTime() * 100f;

		if (wave)
			setPosition(getX() + 4f * (float) Math.sin(Math.toRadians(angle)),
					getY() - 75 * Gdx.graphics.getDeltaTime());
		else
			setY(getY() - 150 * Gdx.graphics.getDeltaTime());

		if (bulletTimer > 0.5f) {
			bulletManager.getList().add(
					new EnemyBulletBasic(getX(), getY(), 0,SpaceShooter.getBulletSpeed(), 0));
			bulletTimer = 0;
		}
	}
}