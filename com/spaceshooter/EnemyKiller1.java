package com.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class EnemyKiller1 extends Enemy {
	private float bulletTimer;
	private float angryTimer;
	private Texture angryTexture;
	private Texture happyTexture;
	private boolean isAngry;
	private float angle;
	
	private boolean wave;
	
	EnemyKiller1() {
		super(50, ResourceManager.EnemyKiller1);
		angryTexture = ResourceManager.getAssetManager().get(
				ResourceManager.EnemyKiller1b, Texture.class);
		happyTexture = ResourceManager.getAssetManager().get(
				ResourceManager.EnemyKiller1, Texture.class);
		bulletTimer = 0f;
		angryTimer = 0f;
		isAngry = false;
		angle = 0.0f;
		this.wave = false;
	}
	EnemyKiller1(boolean wave, float startAngle) {
		super(50, ResourceManager.EnemyKiller1);
		angryTexture = ResourceManager.getAssetManager().get(
				ResourceManager.EnemyKiller1b, Texture.class);
		happyTexture = ResourceManager.getAssetManager().get(
				ResourceManager.EnemyKiller1, Texture.class);
		bulletTimer = 0f;
		angryTimer = 0f;
		isAngry = false;
		angle = startAngle;
		this.wave = wave;
	}

	public void updateEnemy(Player player, BulletManager bulletManager) {
		angle += Gdx.graphics.getDeltaTime() * 100f;
		angryTimer += (Gdx.graphics.getDeltaTime() / 5);
		if (isAngry == false) {
			if (angryTimer > 0.5f) {
				setTexture(angryTexture);
				isAngry = true;
			}
		} else if (isAngry == true) {
			bulletTimer += (Gdx.graphics.getDeltaTime() / 5);

			if (bulletTimer > 0.06f) {
				bulletManager.getList().add(
						new EnemyBulletBasic(getX() + 118, getY(), 0,SpaceShooter.getBulletSpeed(), 0));
				bulletManager.getList().add(
						new EnemyBulletBasic(getX() + 10, getY(), 0,SpaceShooter.getBulletSpeed(), 0));
				bulletTimer = 0;
			}
			if (angryTimer > 1.0f) {
				isAngry = false;
				setTexture(happyTexture);
				angryTimer = 0f;
			}
		}
		if (player != null) {
			if (wave) {
				setPosition(getX() + 4f * (float) Math.sin(Math.toRadians(angle)),
						getY() - 75 * Gdx.graphics.getDeltaTime());
			}
			else {
				setY(getY() - 75 * Gdx.graphics.getDeltaTime());
			}
		}
		
		else {
			if (wave) {
				setPosition(getX() + 4f * (float) Math.sin(Math.toRadians(angle)),
						getY() - 150 * Gdx.graphics.getDeltaTime());
			}
			else {
				setY(getY() - 200 * Gdx.graphics.getDeltaTime());
			}
		}

	}
}