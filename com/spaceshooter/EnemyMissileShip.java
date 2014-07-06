package com.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;

public class EnemyMissileShip extends Enemy {
	private EnemyManager enemyManager;
	private float missileTimer; //Time between each shot;
	private int state;
	private boolean hasChanged;
	
	private Texture textureM;
	private Texture textureL;
	private Texture textureR;
	private Texture texture;
	
	public EnemyMissileShip(EnemyManager enemyManager) {
		super(50,ResourceManager.MissileEnemy);
		texture = getTexture();
		textureM = ResourceManager.getAssetManager().get(ResourceManager.MissileEnemyM,Texture.class);
		textureL = ResourceManager.getAssetManager().get(ResourceManager.MissileEnemyL,Texture.class);
		textureR = ResourceManager.getAssetManager().get(ResourceManager.MissileEnemyR,Texture.class);
		this.enemyManager = enemyManager;
		state = 0; //unaware of player.
		hasChanged = false;
	}
	public void updateEnemy(Player player, BulletManager bulletManager) {
		if (state != 0)
			missileTimer += Gdx.graphics.getDeltaTime() / 5;
		
		if (state == 0) {
			setY(getY() - 150 * Gdx.graphics.getDeltaTime());
		}
		
		
		if (player != null && (getDistance(player) < 200) && state == 0) {
			setTexture(textureM);
			state++;
		}
		else if (player != null && state == 1 && missileTimer > 0.3f) {
			enemyManager.addEnemy(new EnemyMissile(),getX() - 10,getY());
			enemyManager.addEnemy(new EnemyMissile(),getX() + 10,getY());
			missileTimer = 0f;
		}
		
		//if ()
		
	}
}