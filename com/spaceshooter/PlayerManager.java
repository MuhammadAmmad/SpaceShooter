package com.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;

/* Manages the player re-spawns, spawns, and explosions
*/

public class PlayerManager {
	private Player player;
	Explosion explosion = null;
	float respawnTimer;
	
	
	PlayerManager() {
		player = new Player(0,0,false);
		respawnTimer = 0.0f;
	}
	
	public void update(EnemyManager enemyManager,BulletManager bulletManager,PickupManager pickupManager,ScoreHandler scoreHandler) {
		if (player != null) {
			player.update(bulletManager);
			for (int i = 0; i < pickupManager.getList().size(); i++) {
				if (Intersector.overlaps(pickupManager.getList().get(i).getBoundingRectangle()
						,player.getShowSprite().getBoundingRectangle())) {
					if (pickupManager.getList().get(i) instanceof WeaponPickup)
						player.incrementLevel();
					else if (pickupManager.getList().get(i) instanceof MissilePickup)
						player.giveMissile();
					
					pickupManager.getList().remove(i);
				}
			}
			if (player.isDead()) {
				explosion = new Explosion(player.getX(),player.getY(),1);
				scoreHandler.saveScore();
				scoreHandler.setScore(0);
				player = null;
			}
		}
		else {
			respawnTimer += Gdx.graphics.getDeltaTime();
		}
		
		if (respawnTimer >= 2.0f && player == null) {
			player = new Player(0,-200,true);
			bulletManager.clear();
			enemyManager.clear();
			respawnTimer = 0.0f;
		}
	}
	public void drawPlayer(SpriteBatch batch) {
		if (player != null)
			player.draw(batch);
		if (explosion != null)
			explosion.draw(batch);
	}
	public void dispose() {
		if (player != null)
			player.getTexture().dispose();
	}
	public Player getPlayer() {
		return player;
	}
}