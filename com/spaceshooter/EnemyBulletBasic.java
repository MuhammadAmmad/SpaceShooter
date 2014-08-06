package com.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//Simple Enemy bullet, most enemies use this class to create their bullets due to its flexibility. 
public class EnemyBulletBasic extends Bullet {
	float velocityX;
	float velocityY;
	float rotation;

	public EnemyBulletBasic(float xPos,float yPos,float velocityX, float velocityY, float rotation) {
		super();
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		this.rotation = rotation;
		setRotation(-this.rotation);
		setPosition(xPos,yPos);
	}

	public void updateBullet(SpriteBatch batch,EnemyManager enemyManager) {
		setOrigin(getTexture().getWidth() / 2, getTexture().getHeight() / 2);
		translate(-velocityX * Gdx.graphics.getDeltaTime(), -velocityY
				* Gdx.graphics.getDeltaTime());
	}
}