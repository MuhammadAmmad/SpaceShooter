package com.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class EnemyTongue extends Enemy {
	private float bulletTimer;
	private float tongueTimer;
	private Texture normalTexture;
	private Texture tongueTexture;
	private boolean tongueOut;
	private boolean alternateShot;
	private float angle;
	private EnemyManager enemyManager;
	private boolean straightShot;
	//Can only get hurt when its tongue is out. 
	EnemyTongue(EnemyManager enemyManager) {
		super(40, ResourceManager.EnemyTongue); 
		this.enemyManager = enemyManager;
		normalTexture = ResourceManager.getAssetManager().get(
				ResourceManager.EnemyTongue, Texture.class);
		tongueTexture = ResourceManager.getAssetManager().get(
				ResourceManager.EnemyTongueOut, Texture.class);
		setOrigin(getWidth() / 2, getHeight() / 2);
		bulletTimer = 0f;
		tongueTimer = 0f;
		tongueOut = false;
		alternateShot = false;
		angle= 0.0f;
		straightShot = false;
	}

	public void updateEnemy(Player player, BulletManager bulletManager) {
		angle += Gdx.graphics.getDeltaTime()*100f;
		tongueTimer += (Gdx.graphics.getDeltaTime() / 5);
		if (tongueOut == true) {
			if (tongueTimer > 0.5f) {
				setTexture(normalTexture);
				isInvincible = true;
				tongueOut = false;
				tongueTimer = 0f;
				
				if (!straightShot)
					straightShot = true;
				else
					straightShot = false;
			}
		}
		else if (tongueOut == false) {
			bulletTimer += (Gdx.graphics.getDeltaTime() / 5);
			
			if (!straightShot) {
				if (bulletTimer > 0.10f && alternateShot == false) {
					bulletManager.getList().add(
							new EnemyBulletBasic(getX() + 118, getY(),
									SpaceShooter.getBulletSpeed() * (float) Math.cos(Math.PI / 3),SpaceShooter.getBulletSpeed(), 0));
					bulletManager.getList().add(
							new EnemyBulletBasic(getX() + 118, getY(), 0, SpaceShooter.getBulletSpeed(), 0));
					bulletManager.getList().add(
							new EnemyBulletBasic(getX() + 118, getY(), (-1f * SpaceShooter.getBulletSpeed())
									* (float) Math.cos(Math.PI / 3),SpaceShooter.getBulletSpeed(), 0));
	
					bulletManager.getList().add(
							new EnemyBulletBasic(getX() + 10, getY(),
									SpaceShooter.getBulletSpeed() * (float) Math.cos(Math.PI / 3),SpaceShooter.getBulletSpeed(), 0));
					bulletManager.getList().add(
							new EnemyBulletBasic(getX() + 10, getY(), 0, SpaceShooter.getBulletSpeed(), 0));
					bulletManager.getList().add(
							new EnemyBulletBasic(getX() + 10, getY(), (-1f * SpaceShooter.getBulletSpeed())
									* (float) Math.cos(Math.PI / 3),SpaceShooter.getBulletSpeed(), 0));
					alternateShot = true;
					bulletTimer = 0;
				}
				if (bulletTimer > 0.10f && alternateShot == true) {
					bulletManager.getList().add(
							new EnemyBulletBasic(getX() + 118, getY(),
									SpaceShooter.getBulletSpeed() * (float) Math.sin(Math.PI / 3),SpaceShooter.getBulletSpeed(), 0));
					bulletManager.getList().add(
							new EnemyBulletBasic(getX() + 118, getY(), 0,SpaceShooter.getBulletSpeed(), 0));
					bulletManager.getList().add(
							new EnemyBulletBasic(getX() + 118, getY(), (-1f * SpaceShooter.getBulletSpeed())
									* (float) Math.sin(Math.PI / 3),SpaceShooter.getBulletSpeed(), 0));
	
					bulletManager.getList().add(
							new EnemyBulletBasic(getX() + 10, getY(),
									SpaceShooter.getBulletSpeed() * (float) Math.sin(Math.PI / 3),SpaceShooter.getBulletSpeed(), 0));
					bulletManager.getList().add(
							new EnemyBulletBasic(getX() + 10, getY(), 0,SpaceShooter.getBulletSpeed(), 0));
					bulletManager.getList().add(
							new EnemyBulletBasic(getX() + 10, getY(), (-1f * SpaceShooter.getBulletSpeed())
									* (float) Math.sin(Math.PI / 3),SpaceShooter.getBulletSpeed(), 0));
					alternateShot = false;
					bulletTimer = 0;
				}
			}
			else {
				if (player != null) {
					if (bulletTimer > 0.5f && (getY() > player.getY())) {
						enemyManager.addEnemy(
								new EnemyMissile(),getX() + 118, getY());
						enemyManager.addEnemy(
								new EnemyMissile(),getX() + 10, getY());
						bulletTimer = 0.0f;
					}
				}
					
			}
			if (tongueTimer > 1.0f && !tongueOut) {
				tongueOut = true;
				isInvincible = false;
				setTexture(tongueTexture);
				tongueTimer = 0f;
			}
		}
		if (player != null) {
			if (getY() > (player.getY() + 200))
				setPosition(getX() + 2f*(float)Math.sin(Math.toRadians(angle)),getY() - 30*Gdx.graphics.getDeltaTime());
			else
				setPosition(getX() + 2f*(float)Math.sin(Math.toRadians(angle)),getY() + 60*Gdx.graphics.getDeltaTime());
		}
			else
			setPosition(getX() + 4f*(float)Math.sin(Math.toRadians(angle)),getY() - 100*Gdx.graphics.getDeltaTime());
	}

}