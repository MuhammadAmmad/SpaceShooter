package com.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayerMissile extends PlayerBullet {
	private ParticleEffect particle;
	private float velocityX;
	private float velocityY;
	private float angle;
	private double minDist;
	private boolean hasFound;
	Enemy closestEnemy;
	
	public PlayerMissile(float xPos,float yPos) {
		super(ResourceManager.Missile);
		/*particle = ResourceManager.getAssetManager().get(ResourceManager.MissileParticle,
				ParticleEffect.class);*/
		
		particle = new ParticleEffect();
		//particle.load(new FileHandle(ResourceManager.MissileParticle),new FileHandle("media"));
		particle.load(Gdx.files.internal(ResourceManager.MissileParticle),Gdx.files.internal("media"));
		setPosition(xPos,yPos);
        velocityX = 0f;
        velocityY = 0f;
        angle = 0;
        minDist = 99999.0;
        hasFound = false;
	}

	public void updateBullet(SpriteBatch batch,EnemyManager enemyManager) {
		
		//Find the closest enemy once the bullet has been shot.
		if (!hasFound) {
			for (Enemy e: enemyManager.getList()) {
				float distX = getX() - e.getX();
				float distY = getY() - e.getY();
				double dist = Math
						.sqrt(distX * distX + distY * distY);
				
				if (dist < minDist) {
					minDist = dist;
					closestEnemy = e;
				}
			}
			hasFound = true;
		}
		if (minDist == 99999.0 || closestEnemy.isDead) {
			velocityX = 0f;
			velocityY = -400f * Gdx.graphics.getDeltaTime();
		}
		else {
			float directionX = getX() - closestEnemy.getX();
			float directionY = getY() - closestEnemy.getY();
			double sq = Math
					.sqrt(directionX * directionX + directionY * directionY);
	
			velocityX = (float) (directionX
					* (400 * Gdx.graphics.getDeltaTime()) / sq);
			velocityY = (float) (directionY
					* (400 * Gdx.graphics.getDeltaTime()) / sq);
		}
		
		
		angle = (float) Math.toDegrees(Math.atan2(velocityY,velocityX));
		setRotation(angle + 90);
		setOrigin(getTexture().getWidth() / 2, getTexture().getHeight() / 2);
		translate(-velocityX,-velocityY);
		this.draw(batch);
		drawParticle(batch);
		
	}
	public void drawParticle(SpriteBatch batch) {
		particle.setPosition(getX()+(getTexture().getWidth() / 2), getY()+(getTexture().getHeight() / 2)); // set the particle's position to
		for (int i = 0; i < particle.getEmitters().size;i++) {
			particle.getEmitters().get(i).getAngle().setLow(angle - 180); //min
            particle.getEmitters().get(i).getAngle().setHigh(angle - 180); //max
           
		}
		particle.draw(batch, Gdx.graphics.getDeltaTime());
	}
}