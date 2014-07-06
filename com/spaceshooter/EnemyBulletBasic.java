package com.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EnemyBulletBasic extends Bullet {
	float m_velocityX;
	float m_velocityY;
	float m_rotation;

	public EnemyBulletBasic(float xPos,float yPos,float velocityX, float velocityY, float rotation) {
		super();
		m_velocityX = velocityX;
		m_velocityY = velocityY;
		m_rotation = rotation;
		setRotation(-m_rotation);
		setPosition(xPos,yPos);
	}

	public void updateBullet(SpriteBatch batch,EnemyManager enemyManager) {
		setOrigin(getTexture().getWidth() / 2, getTexture().getHeight() / 2);
		translate(-m_velocityX * Gdx.graphics.getDeltaTime(), -m_velocityY
				* Gdx.graphics.getDeltaTime());
	}
}