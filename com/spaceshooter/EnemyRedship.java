package com.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class EnemyRedship extends Enemy {
	private float bulletTimer;
	private float angryTimer;
	private Texture angryTexture;
	private Texture happyTexture;
	private boolean isAngry;
	private boolean alternateShot;
	private float angle;
	EnemyRedship() {
		super(50, ResourceManager.EnemyRedShip1); // tons of health! (45)
		angryTexture = ResourceManager.getAssetManager().get(
				ResourceManager.EnemyRedShip1b, Texture.class);
		happyTexture = ResourceManager.getAssetManager().get(
				ResourceManager.EnemyRedShip1, Texture.class);
		setOrigin(getWidth() / 2, getHeight() / 2);
		bulletTimer = 0f;
		angryTimer = 0f;
		isAngry = false;
		alternateShot = false;
		angle= 0.0f;
	}

	public void updateEnemy(Player player, BulletManager bulletManager) {
		angle += Gdx.graphics.getDeltaTime()*100f;
		angryTimer += (Gdx.graphics.getDeltaTime() / 5);
		if (isAngry == false) {
			if (angryTimer > 0.5f) {
				setTexture(angryTexture);
				isAngry = true;
			}
		} else if (isAngry == true) {
			bulletTimer += (Gdx.graphics.getDeltaTime() / 5);

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
			if (angryTimer > 1.0f) {
				isAngry = false;
				setTexture(happyTexture);
				angryTimer = 0f;
			}
		}
		if (player != null)
			setPosition(getX() + 4f*(float)Math.sin(Math.toRadians(angle)),getY() - 30*Gdx.graphics.getDeltaTime());
		else
			setPosition(getX() + 4f*(float)Math.sin(Math.toRadians(angle)),getY() - 100*Gdx.graphics.getDeltaTime());
	}

	@Override
	public void hit() { // Make it easier for the player to see hits. Turns to
						// pink instead of red.
		// setY(getY() + 150 * Gdx.graphics.getDeltaTime()); Possible impact
		// powerup??
		gotHit = true;
		setColor(Color.CLEAR);
		health--;
		if (health <= 0)
			isDead = true;
	}
}