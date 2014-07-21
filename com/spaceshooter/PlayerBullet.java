package com.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayerBullet extends Bullet {
	private ParticleEffect particle;
	float velocityX;
	float velocityY;
	private int weaponLevel;
	
	public PlayerBullet(float xPos,float yPos,float velocityX,float velocityY,int weaponLevel) {
		super(ResourceManager.PlayerBulletSprite);
		particle = new ParticleEffect();
		particle.load(Gdx.files.internal(ResourceManager.PlayerBullet),Gdx.files.internal("media"));
		setPosition(xPos,yPos);
		
		this.velocityY = velocityY;
		this.velocityX = velocityX;
		this.weaponLevel = weaponLevel;
		
		if (weaponLevel == 1) {
			for (int i = 0; i < particle.getEmitters().size;i++) {
	            particle.getEmitters().get(i).getTint().setColors(new float[] {125f,0f,0f,125f});
			}
		}
		// If weaponLevel == 2, leave as blue
		else if (weaponLevel == 3) {
			for (int i = 0; i < particle.getEmitters().size;i++) {
	            particle.getEmitters().get(i).getTint().setColors(new float[] {0f,125f,0f,125f});
			}
		}
		else if (weaponLevel >= 4) {
			for (int i = 0; i < particle.getEmitters().size;i++) {
	            particle.getEmitters().get(i).getTint().setColors(new float[] {125f,125f,125f,125f});
			}
		}
			
	}
	//Constructor for subclasses i.e. missiles :
	public PlayerBullet(String texture) {
		super(texture);
	}
	public void updateBullet(SpriteBatch batch,EnemyManager enemyManager) {
		setOrigin(getTexture().getWidth() / 2, getTexture().getHeight() / 2);
		translate(velocityX * Gdx.graphics.getDeltaTime(),velocityY * Gdx.graphics.getDeltaTime());
		drawParticle(batch);
	}
	public void drawParticle(SpriteBatch batch) {
		float angle = (float) Math.toDegrees(Math.atan2(velocityY,velocityX));
		particle.setPosition(getX(), getY()); // set the particle's position to
		for (int i = 0; i < particle.getEmitters().size;i++) {
			particle.getEmitters().get(i).getAngle().setLow(angle - 180); //min
            particle.getEmitters().get(i).getAngle().setHigh(angle - 180); //max
		}
		// the bullet's
		particle.draw(batch, Gdx.graphics.getDeltaTime());
	}
	public int getWeaponLevel() {
		return weaponLevel;
	}
}