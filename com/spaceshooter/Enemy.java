package com.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/* All in-game enemies derive from the Enemy class, including the EnemyMissile. The enemies are managed by the EnemyManager class. */
public class Enemy extends Sprite {
	protected boolean gotHit;
	protected int health;
	public boolean isDead;
	//When the enemy gets hit by a bullet, let the enemy blink for a split second to let the player know the enemy ship was hit. 
	private float redTimer;
	
	protected boolean isInvincible;

	Enemy() {
		super(ResourceManager.getAssetManager().get(ResourceManager.Enemy,
				Texture.class));
		health = 1;
		redTimer = 0f;
		isDead = false;
		isInvincible = false;
	}

	Enemy(int health) {
		super(ResourceManager.getAssetManager().get(ResourceManager.Enemy,
				Texture.class));
		this.health = health;
		redTimer = 0f;
		isDead = false;
		isInvincible = false;
	}

	Enemy(String filePath) {
		super(ResourceManager.getAssetManager().get(filePath, Texture.class));
	}

	Enemy(int health, String filePath) {
		super(ResourceManager.getAssetManager().get(filePath, Texture.class));
		this.health = health;
	}

	public void updateEnemy(Player player, BulletManager bulletManager) {
		setY(getY() - 150 * Gdx.graphics.getDeltaTime());
	}

	public void processHits() {
		if (gotHit) {
			redTimer += (Gdx.graphics.getDeltaTime() / 5);
			if (redTimer > 0.005f) {
				setColor(Color.WHITE);
				redTimer = 0f;
				gotHit = false;
			}
		}
	}
	public void hit(int damage) {
		gotHit = true;
		if (!isInvincible) {
			setColor(Color.RED);
			health -= damage;
			if (health <= 0)
				isDead = true;
		}
		else
			setColor(Color.BLUE);
	}
	//hit() defaults to -1 instead of - damage
	public void hit() {
		gotHit = true;
		if (!isInvincible) {
			setColor(Color.RED);
			health--;
			if (health <= 0)
				isDead = true;
		}
		else
			setColor(Color.BLUE); //Invincible enemies turn blue, to let the player know that the ship was NOT hit.
	}
	public boolean isInvincible() {
		return isInvincible;
	}
	
	// Helper functions to save time and to avoid repeating code. 
	public float getDistance(Sprite other) {
		float dist = (float) Math.sqrt(Math.pow(
				getX() - other.getX(), 2)
				+ Math.pow(getY() - other.getY(), 2));
		
		return dist;
	}
	public Vector2 moveTowards(double speed, Sprite other) {
		float directionX = getX() - other.getX();
		float directionY = getY() - other.getY();
		double sq = Math
				.sqrt(directionX * directionX + directionY * directionY);

		float velocityX = (float) (directionX
				* (speed * Gdx.graphics.getDeltaTime()) / sq);
		float velocityY = (float) (directionY
				* (speed * Gdx.graphics.getDeltaTime()) / sq);

		translate(-velocityX, -velocityY);
		return new Vector2(-velocityX,-velocityY);
	}
	
	public void moveTowardsX(double speed, Sprite other) {
		float directionX = getX() - other.getX();
		float directionY = getY() - other.getY();
		double sq = Math
				.sqrt(directionX * directionX + directionY * directionY);

		float velocityX = (float) (directionX
				* (speed * Gdx.graphics.getDeltaTime()) / sq);
		
		translateX(-velocityX);
	}

	public void rotateTowards(Sprite other) {
		float angle = (float) Math.atan2(getY() - other.getY(),
				getX() - other.getX());
		float degrees = (float) (angle * (180 / Math.PI));
		setRotation(degrees - 90);
	}
	//Useful for an upside-down sprite. 
	public void rotateTowardsFlipped(Sprite other) {
		float angle = (float) Math.atan2(getY() - other.getY(),
				getX() - other.getX());
		float degrees = (float) (angle * (180 / Math.PI));
		setRotation(degrees + 90);
	}
}
	
	

/*
 * Potentially useful health bar code: private BitmapFont m_Font; private
 * BitmapFont m_Font; private String m_String; private Sprite healthBar; private
 * Sprite healthBarBk;
 * 
 * healthBar = new Sprite(new Texture("media/healthBar.png"));
 * healthBar.setOrigin(getTexture().getWidth()/2,getTexture().getHeight()/2);
 * healthBar.setRotation(90); //magic numbers...
 * healthBar.setBounds((Gdx.graphics.getWidth() / 3f) +
 * 50f,(Gdx.graphics.getHeight() / 3) - 400,m_Energy*3,8);
 * 
 * healthBar.setColor(getColor().r,getColor().g,getColor().b,125);
 * 
 * healthBarBk = new Sprite(new Texture("media/healthBar.png"));
 * healthBarBk.setOrigin(getTexture().getWidth()/2,getTexture().getHeight()/2);
 * healthBarBk.setRotation(90); //magic numbers...
 * healthBarBk.setBounds((Gdx.graphics.getWidth() / 3f) +
 * 50f,(Gdx.graphics.getHeight() / 3) - 400,300,8);
 * healthBarBk.setColor(Color.RED);
 * 
 * if (gotHit == true) { blinkTimer = 0.50f; gotHit = false;
 * healthBar.setBounds((Gdx.graphics.getWidth() / 3f) +
 * 50f,(Gdx.graphics.getHeight() / 3) - 400,m_Energy*3,8); isBlinking = true; //
 * must blink now bonusScore = 0; powerupTimer = 30f; }
 * 
 * m_Font.draw(batch,m_String,Gdx.graphics.getWidth() / 3 -
 * 50,(Gdx.graphics.getHeight() / 3) + 50); healthBarBk.draw(batch);
 * healthBar.draw(batch);
 */