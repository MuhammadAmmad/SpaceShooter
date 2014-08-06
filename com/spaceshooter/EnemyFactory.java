package com.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

/*EnemyFactory spawns its own enemies*/
public class EnemyFactory extends Enemy {
	private float spawnTimer;
	private EnemyManager enemyManager;
	private boolean hasChanged; //Returns true if the texture has changed.
	
	private int type;
	
	public EnemyFactory(EnemyManager enemyManager) {
		super(50,ResourceManager.EnemyFactory);
		spawnTimer = 0.0f;
		hasChanged = false;
		this.enemyManager = enemyManager;
		type = MathUtils.random(0,2);
	}


	public void updateEnemy(Player player, BulletManager bulletManager) {
		spawnTimer += (Gdx.graphics.getDeltaTime() / 5);
		
		setY(getY() - 50 * Gdx.graphics.getDeltaTime());
		
		if (player != null) {
			
			float playerDist = (float) Math.sqrt(Math.pow(
					getX() - player.getX(), 2)
					+ Math.pow(getY() - player.getY(), 2));
			//3 different types.
			if (type == 0) {
				if ((spawnTimer > 0.2f) && (playerDist < 600) && (getY() > player.getY())) {
					enemyManager.addEnemy(new EnemyHoming(),getX() + (getWidth()/2) - 15,getY());
					spawnTimer = 0f;
				}
			}
			else if (type == 1) {
				if ((spawnTimer > 0.2f) && (playerDist < 600) && (getY() > player.getY())) {
					enemyManager.addEnemy(new EnemyPopcorn(),getX() + (getWidth()/2 - 15),getY());
					spawnTimer = 0f;
				}
			}
			else if (type == 2) {
				if ((spawnTimer > 0.2f) && (playerDist < 600) && (getY() > player.getY())) {
					enemyManager.addEnemy(new EnemySpinner(),getX() + (getWidth()/2 - 15),getY());
					spawnTimer = 0f;
				}
			}
			
		}
		//Once it is almost destroyed, change to a fitting texture.
		if (health < 15 && !hasChanged) {
			setTexture(ResourceManager.getAssetManager().get(ResourceManager.EnemyFactoryDestroyed,Texture.class));
			hasChanged = true;
		}
			
	}
}