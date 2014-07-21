package com.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Explosion extends Sprite {

	private ParticleEffect particle;
	private Sound explosionSound;
	
	{
		particle = ResourceManager.getAssetManager().get(
				ResourceManager.ExplosionParticle1, ParticleEffect.class);
		particle.start();
		explosionSound = ResourceManager.getAssetManager().get(
				ResourceManager.ExplosionSound1, Sound.class);
	}

	Explosion(float x, float y) {
		particle.setPosition(x, y);
		setPosition(x, y);
		if (!SpaceShooter.isMuted())
			explosionSound.play(0.2f);

	}

	Explosion(float x, float y, float volume) {
		particle.setPosition(x, y);
		setPosition(x, y);
		if (!SpaceShooter.isMuted())
			explosionSound.play(volume);
	}

	public void draw(SpriteBatch batch) {
		drawParticle(batch);
	}

	public void drawParticle(SpriteBatch batch) {
		particle.draw(batch, Gdx.graphics.getDeltaTime());
	}

}