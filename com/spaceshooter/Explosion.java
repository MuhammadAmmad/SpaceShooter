package com.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
public class Explosion extends Sprite {

	private ParticleEffect m_Particle;
	private Sound explosionSound;

	Explosion(float x, float y) {
		//m_Particle = new ParticleEffect();
		//m_Particle.load(Gdx.files.internal("media/explosion2.p"),
				//Gdx.files.internal("media"));
		
		m_Particle = ResourceManager.getAssetManager().get(ResourceManager.ExplosionParticle1,
				ParticleEffect.class);
		
		m_Particle.setPosition(x,y); 
		m_Particle.start();
		setPosition(x,y);
		explosionSound = ResourceManager.getAssetManager().get(ResourceManager.ExplosionSound1,
				Sound.class);
		if (!SpaceShooter.isMuted())
			explosionSound.play(0.2f);
		
	}
	Explosion(float x, float y,float volume) {
		m_Particle = ResourceManager.getAssetManager().get(ResourceManager.ExplosionParticle1,
				ParticleEffect.class);
		m_Particle.setPosition(x,y); 
		m_Particle.start();
		setPosition(x,y);
		explosionSound = ResourceManager.getAssetManager().get(ResourceManager.ExplosionSound1,
				Sound.class);
		if (!SpaceShooter.isMuted())
			explosionSound.play(volume);

	}
	public void draw(SpriteBatch batch) {
		drawParticle(batch);
	}
	public void drawParticle(SpriteBatch batch) {
		m_Particle.draw(batch, Gdx.graphics.getDeltaTime());
	}

}