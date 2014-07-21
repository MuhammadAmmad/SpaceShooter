package com.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class EnemySpinner extends Enemy {

	private final int HOMING_SPEED;
	private boolean isAngry;
	private float directionX;
	private	float directionY;
	private float bulletTimer;
	private Texture hardTexture;
	EnemySpinner() {
		super(5,ResourceManager.EnemySpinner);
		hardTexture = ResourceManager.getAssetManager().get(ResourceManager.EnemySpinnerHard,Texture.class);
		HOMING_SPEED = 100;
		isAngry = false;
		setOrigin(getWidth() / 2, getHeight() / 2);
		directionX = 0f;
		directionY = 0f;
		bulletTimer = 0f;
	}
	public void updateEnemy(Player player, BulletManager bulletManager) {

		rotate(Gdx.graphics.getDeltaTime()*300);
		bulletTimer += (Gdx.graphics.getDeltaTime()/5); //Easier to work with as a smaller #
		if (player != null) {
			// Move the enemy towards the player. Clean this up *
			float playerDist = (float) Math
					.sqrt(Math.pow(getX() - player.getX(), 2)
							+ Math.pow(getY() - player.getY(), 2));
	
			if (playerDist < 200 || isAngry) {
	
				if (!isAngry) {
					isAngry = true;
					setTexture(hardTexture);
					directionX = getX() - player.getX();
					directionY = getY() - player.getY();
				}
	
				double sq = Math.sqrt(directionX * directionX + directionY
						* directionY);
	
				float velocityX = (float) (directionX
						* (HOMING_SPEED * Gdx.graphics.getDeltaTime()) / sq);
				float velocityY = (float) (directionY
						* (HOMING_SPEED * Gdx.graphics.getDeltaTime()) / sq);
	
				translate(-velocityX, -velocityY);
				if (isAngry) {
					// Make bullets
					if (bulletTimer > 0.009f) {
						bulletManager.getList().add(new EnemyBulletBasic(getX()+getWidth()/2,getY()+getHeight()/2,
								(-1f*SpaceShooter.getBulletSpeed())*(float)Math.cos(Math.toRadians(getRotation()-90)),
								(-1f*SpaceShooter.getBulletSpeed())*(float)Math.sin(Math.toRadians(getRotation()-90)),0));
						
						bulletManager.getList().add(new EnemyBulletBasic(getX()+getWidth()/2,getY()+getHeight()/2,
								(-1f * SpaceShooter.getBulletSpeed())*(float)Math.cos(Math.toRadians(getRotation()+90)),
								(-1f*SpaceShooter.getBulletSpeed())*(float)Math.sin(Math.toRadians(getRotation()+90)),0));
						
						
						bulletTimer = 0f;
					}
				}
			} 
			else {
				setY(getY() - 150 * Gdx.graphics.getDeltaTime());
			}
		}
		else {
			setY(getY() - 150 * Gdx.graphics.getDeltaTime());
		}

	}

}