//Enemy missile acts as an enemy instead of a bullet, since it must be shot by the player.
package com.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class EnemyMissile extends Enemy {
	private final float HOMING_SPEED;
	private ParticleEffect particle;
	private Vector2 vectorV;
	EnemyMissile() {
		super(1, ResourceManager.Missile);
		setRotation(getRotation() + 180);
		HOMING_SPEED = SpaceShooter.getBulletSpeed() * 2.0f;
		particle = new ParticleEffect();
		vectorV = new Vector2();
		particle.load(Gdx.files.internal(ResourceManager.MissileParticle),
				Gdx.files.internal("media"));
		for (int i = 0; i < particle.getEmitters().size; i++) {
			particle.getEmitters().get(i).getTint()
					.setColors(new float[] { 125, 0f, 0f, 125f });
		}

	}

	public void updateEnemy(Player player, BulletManager bulletManager) {
		if (player != null) {
			// Point the enemy towards the player.
			rotateTowardsFlipped(player);
			// Move the enemy towards the player. 
			vectorV = moveTowards(HOMING_SPEED,player);
		} 
		else {
			translate(vectorV.x,vectorV.y);
		}

	}

	public void drawParticle(SpriteBatch batch) {
		particle.setPosition(getX() + (getTexture().getWidth() / 2), getY()
				+ (getTexture().getHeight() / 2)); // set the particle's
													// position to
		for (int i = 0; i < particle.getEmitters().size; i++) {
			particle.getEmitters().get(i).getAngle().setLow(getRotation() - 270); // minimum angle
			particle.getEmitters().get(i).getAngle().setHigh(getRotation() - 270); // maximum angle

		}
		particle.draw(batch, Gdx.graphics.getDeltaTime());
	}

	public void draw(SpriteBatch batch) {
		super.draw(batch);
		drawParticle(batch);
	}
}
