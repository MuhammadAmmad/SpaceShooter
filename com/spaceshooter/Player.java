package com.spaceshooter;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//Player class. 
public class Player extends Sprite {

	public ParticleEffect particle;
	public boolean isBlinking;
	private float blinkTimer;
	private Sprite playerSprite;
	private boolean isDead;
	private int weaponLevel;
	private boolean hasMissile;
	private float missileTimer;
	private float bulletTimer;
	private boolean hideHitbox;
	private Sound shootSound;
	private float hasMissileTimer; //when this reaches a peak, the player loses missile ability until the player acquires the powerup again.
	private static final int MAX_LEVEL = 4;
	
	private float levelTimer; //After some time, decrement the player's level to promote pick-ups.
	Player(float x, float y,boolean justDied) {
		super(ResourceManager.getAssetManager().get(ResourceManager.Hitbox,Texture.class));
		playerSprite = new Sprite(ResourceManager.getAssetManager().get(ResourceManager.Player,Texture.class));
		shootSound = ResourceManager.getAssetManager().get(ResourceManager.PlayerShootSound,Sound.class);
		bulletTimer = 0f;
		missileTimer = 0f;
		levelTimer = 0f;
		hasMissileTimer = 0f;
		setOrigin(4,4);
		setPosition(x, y);
		Gdx.input.setCursorPosition((int)x, (int)y);
		hideHitbox = false;
		particle = ResourceManager.getAssetManager().get(ResourceManager.Thrusters,
				ParticleEffect.class);
		isDead = false;
		weaponLevel = 1;
		hasMissile = false;
		if (justDied) {
			blinkTimer = 0.50f;
			isBlinking = true;
		}
		else {
			blinkTimer = 0.0f;
			isBlinking = false;
		}
		
		playerSprite.setPosition(getX()-27,getY()-10);
		particle.setPosition(playerSprite.getX() + 30, playerSprite.getY() - 5);
	}
	public Sprite getShowSprite() {
		return playerSprite;
	}
	public void update(BulletManager bulletManager) {
		System.out.println("XPos: " + getX() + " YPos:" + getY());
		if (getX() >= SpaceShooter.getRightBound())
			setX(SpaceShooter.getRightBound());
		else if (getY() >= SpaceShooter.getTopBound())
			setY(SpaceShooter.getTopBound());
		else if (getX() <= SpaceShooter.getLeftBound())
			setX(SpaceShooter.getLeftBound());
		else if (getY() <= SpaceShooter.getBottomBound())
			setY(SpaceShooter.getBottomBound());
		
		bulletTimer += Gdx.graphics.getDeltaTime() / 5;
		
		if (weaponLevel > 1)
			levelTimer += Gdx.graphics.getDeltaTime();
		
		System.out.println(levelTimer);
		if (hasMissile) {
			missileTimer += Gdx.graphics.getDeltaTime() / 5;
			hasMissileTimer += Gdx.graphics.getDeltaTime();
		}
		
		translate(Gdx.input.getDeltaX()*1.5f+(Gdx.graphics.getDeltaTime()),-Gdx.input.getDeltaY()*1.5f+(Gdx.graphics.getDeltaTime()));
		if (Gdx.app.getType() == ApplicationType.Desktop) {
			if (Gdx.input.isButtonPressed(Buttons.LEFT) && bulletTimer > 0.02f) {
				shootSound.play(0.2f);
				bulletManager.getList().add(new PlayerBullet(getX() - 20,getY(),0,600f,weaponLevel));
				bulletManager.getList().add(new PlayerBullet(getX() + 25,getY(),0,600f,weaponLevel));
					
				bulletTimer = 0.0f;	
			}
		}
		else if (Gdx.app.getType() == ApplicationType.Android) {
			if (Gdx.input.isTouched() && bulletTimer > 0.04f) {
				shootSound.play(0.2f);
				bulletManager.getList().add(new PlayerBullet(getX() - 20,getY(),0,600f,weaponLevel));
				bulletManager.getList().add(new PlayerBullet(getX() + 25,getY(),0,600f,weaponLevel));
					
				
				bulletTimer = 0.0f;	
			}
		}
		
		if (hasMissileTimer > 10f) {
			hasMissile = false; 
			hasMissileTimer = 0f;
		}
		if (levelTimer > 20f) {
			decrementLevel();
		}
		if (missileTimer > 0.2f && hasMissile) {
			bulletManager.getList().add(new PlayerMissile(getX() - 20,getY()));
			bulletManager.getList().add(new PlayerMissile(getX() + 20,getY()));
			
			bulletManager.getList().add(new PlayerMissile(getX() - 40,getY()));
			bulletManager.getList().add(new PlayerMissile(getX() + 40,getY()));
			missileTimer = 0f;
		}
			
		playerSprite.setPosition(getX()-27,getY()-10);
		particle.setPosition(playerSprite.getX() + 30, playerSprite.getY() - 5);

		if (blinkTimer > 0.0f && isBlinking) {
			blinkTimer -= Gdx.graphics.getDeltaTime() / 5;

			if (blinkTimer > 0.42f && blinkTimer < 0.50f) {
				setColor(Color.CLEAR);
				playerSprite.setColor(Color.CLEAR);
			}
			else if (blinkTimer > 0.17f && blinkTimer < 0.25f) {
				setColor(Color.CLEAR);
				playerSprite.setColor(Color.CLEAR);
			}
			else if (blinkTimer > 0.0f && blinkTimer < 0.08f) {
				setColor(Color.CLEAR);
				playerSprite.setColor(Color.CLEAR);	
			}
			else {
				setColor(Color.WHITE);
				playerSprite.setColor(Color.WHITE);
			}

			if (blinkTimer <= 0.0f) {
				blinkTimer = 0;
				isBlinking = false;
			}
		}
			
		if (!isBlinking) {
			setColor(Color.WHITE);
			playerSprite.setColor(Color.WHITE);
		}
		
		
		
	}
	public void killPlayer() {
		if (!isBlinking) 
			isDead = true;
	}
	public boolean isDead() {
		return isDead;
	}
	public void decrementLevel()  {
			levelTimer = 0f;
			weaponLevel--;
			if (weaponLevel < 1)
				weaponLevel = 1;
	}
	public void incrementLevel() {
			levelTimer = 0f; //reset the level timer.
			weaponLevel++;
			if (weaponLevel > MAX_LEVEL)
				weaponLevel = MAX_LEVEL;
	}
	public int getWeaponLevel() {
		return weaponLevel;
	}
	public void giveMissile() {
		hasMissile = true;
	}
	public void hideHitbox() {
		hideHitbox = true;
	}
	public void draw(SpriteBatch batch) {	
		particle.draw(batch,Gdx.graphics.getDeltaTime());
		playerSprite.draw(batch);
		
		if (!hideHitbox)
			super.draw(batch);
	}
}
