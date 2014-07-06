//Enemy missile acts as an enemy instead of a bullet, since it must be shot by the player.
package com.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EnemyMissile extends Enemy {
	private final float HOMING_SPEED;
	private float velocityX;
	private float velocityY;
	private float angle;
	private ParticleEffect particle;

	private float downTimer;// shoot downwards for a limited time to give the
							// player time to react.

	EnemyMissile() {
		super(1, ResourceManager.Missile);
		setRotation(getRotation() + 180);
		HOMING_SPEED = SpaceShooter.getBulletSpeed() * 2.0f;
		velocityX = 0.0f;
		velocityY = 0.0f;
		particle = new ParticleEffect();
		particle.load(Gdx.files.internal(ResourceManager.MissileParticle),
				Gdx.files.internal("media"));
		for (int i = 0; i < particle.getEmitters().size; i++) {
			particle.getEmitters().get(i).getTint()
					.setColors(new float[] { 125, 0f, 0f, 125f });
		}

	}

	public void updateEnemy(Player player, BulletManager bulletManager) {
		downTimer += Gdx.graphics.getDeltaTime() / 5;
		if (player != null) {

			// Point the enemy towards the player.
			angle = (float) Math.atan2(getY() - player.getY(),
					getX() - player.getX());
			float degrees = (float) (angle * (180 / Math.PI));
			setRotation(degrees + 90);
			/*
			 * translate(100 * Gdx.graphics.getDeltaTime() * (float)
			 * Math.cos(degrees - 90), 100 * Gdx.graphics.getDeltaTime() *
			 * (float) Math.sin(degrees - 90));
			 */
			// this.setY(this.getY() - 150 * Gdx.graphics.getDeltaTime());

			// Move the enemy towards the player. Clean this up *
			float directionX = getX() - player.getX();
			float directionY = getY() - player.getY();
			double sq = Math.sqrt(directionX * directionX + directionY
					* directionY);

			velocityX = (float) (directionX
					* (HOMING_SPEED * Gdx.graphics.getDeltaTime()) / sq);
			velocityY = (float) (directionY
					* (HOMING_SPEED * Gdx.graphics.getDeltaTime()) / sq);

			translate(-velocityX, -velocityY);

		} 
		else {
			translate(-velocityX, -velocityY);
		}

	}

	public void drawParticle(SpriteBatch batch) {
		particle.setPosition(getX() + (getTexture().getWidth() / 2), getY()
				+ (getTexture().getHeight() / 2)); // set the particle's
													// position to
		for (int i = 0; i < particle.getEmitters().size; i++) {
			particle.getEmitters().get(i).getAngle().setLow(angle - 180); // min
			particle.getEmitters().get(i).getAngle().setHigh(angle - 180); // max

		}
		particle.draw(batch, Gdx.graphics.getDeltaTime());
	}

	public void draw(SpriteBatch batch) {
		super.draw(batch);
		drawParticle(batch);
	}
}
