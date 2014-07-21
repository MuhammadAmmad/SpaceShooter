package com.spaceshooter;

import com.badlogic.gdx.math.Vector2;

public class EnemyHoming extends Enemy {
	private final int HOMING_SPEED;
	private Vector2 velocityV;
	EnemyHoming() {
		super(3);
		HOMING_SPEED = 200;
		velocityV = new Vector2();
	}

	public void updateEnemy(Player player, BulletManager bulletManager) {
		if (player != null) {
			// Point and move the enemy towards the player.
			rotateTowards(player);
			velocityV = moveTowards(HOMING_SPEED,player);
		}
		else {
			translate(velocityV.x,velocityV.y);
		}
	}
}