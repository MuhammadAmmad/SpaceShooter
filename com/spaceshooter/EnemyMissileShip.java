package com.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;

public class EnemyMissileShip extends Enemy {
	private EnemyManager enemyManager;
	private float missileTimer; // Time between each shot;
	private int state;

	private Texture textureM;
	private Texture textureL;
	private Texture textureR;
	private Texture texture;
	//Red ship that fires six missiles at the player (two at a time). 
	public EnemyMissileShip(EnemyManager enemyManager) {
		super(50, ResourceManager.MissileEnemy);
		texture = getTexture();
		textureM = ResourceManager.getAssetManager().get(
				ResourceManager.MissileEnemyM, Texture.class);
		textureL = ResourceManager.getAssetManager().get(
				ResourceManager.MissileEnemyL, Texture.class);
		textureR = ResourceManager.getAssetManager().get(
				ResourceManager.MissileEnemyR, Texture.class);
		this.enemyManager = enemyManager;
		state = 0; // unaware of player.
	}

	public void updateEnemy(Player player, BulletManager bulletManager) {
		if (state != 0)
			missileTimer += Gdx.graphics.getDeltaTime() / 5;

		if (state == 0) {
			setY(getY() - 150 * Gdx.graphics.getDeltaTime());
		} else if (state == 4) {
			setY(getY() - 200 * Gdx.graphics.getDeltaTime());
		}

		if (player != null && (getDistance(player) < 200) && state == 0) {
			setTexture(textureL);
			state++;
		} else if (player != null && state == 1 && missileTimer > 0.2f) {
			enemyManager.addEnemy(new EnemyMissile(), getX() - 30, getY());
			enemyManager.addEnemy(new EnemyMissile(), getX() + 50, getY());

			setTexture(textureM);
			state++;
			missileTimer = 0f;
		} else if (player != null && state == 2 && missileTimer > 0.2f) {
			enemyManager.addEnemy(new EnemyMissile(), getX() - 30, getY());
			enemyManager.addEnemy(new EnemyMissile(), getX() + 50, getY());

			setTexture(textureR);
			state++;
			missileTimer = 0f;
		} else if (state == 3 && missileTimer > 0.2f) {
			enemyManager.addEnemy(new EnemyMissile(), getX() - 30, getY());
			enemyManager.addEnemy(new EnemyMissile(), getX() + 50, getY());

			setTexture(texture);
			state++;
			missileTimer = 0f;
		}

	}
}