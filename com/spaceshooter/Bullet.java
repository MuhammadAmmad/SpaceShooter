package com.spaceshooter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/* Abstract Bullet class, all bullets, both enemy and player, derive from this class. In addition, Bullets are handled by the 
	BulletManager class*/ 

public abstract class Bullet extends Sprite {
	public Bullet(String filepath) {
		super(ResourceManager.getAssetManager().get(filepath,Texture.class));
	}
	public Bullet() {
		super(ResourceManager.getAssetManager().get(ResourceManager.Bullet,Texture.class));
	}
	abstract void updateBullet(SpriteBatch batch, EnemyManager enemyManager);
}