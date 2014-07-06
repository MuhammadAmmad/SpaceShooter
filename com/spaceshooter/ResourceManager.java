package com.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.assets.loaders.*;
import com.badlogic.gdx.assets.loaders.resolvers.*;
public class ResourceManager {
	private static final AssetManager assetManager = new AssetManager();
	private static final ParticleEffectLoader particleLoader = new ParticleEffectLoader(new InternalFileHandleResolver());
	public static final String Player = "media/playerspaceship.png";
	public static final String Hitbox = "media/hitbox.png";
	public static final String Thrusters = "media/thrusters.p";
	public static final String PlayerBullet = "media/bullet.p";
	public static final String PlayerBulletSprite = "media/bulletsprite.png";
	public static final String Missile = "media/missile.png";
	public static final String MissileParticle = "media/missile.p";
	public static final String WeaponPickup = "media/powerup/weaponpowerup.png";
	public static final String Bullet = "media/enemybulletorange.png";
	public static final String Coin = "media/powerup/coingreen.png";
	public static final String MissilePickup = "media/powerup/missile.png";
	public static final String Enemy = "media/enemy.png";
	public static final String EnemyCargo1 = "media/cargo1.png";
	
	// Next four are the same enemy, different "animations" :
	public static final String MissileEnemy = "media/enemy/missileenemy/enemy.png";
	public static final String MissileEnemyM = "media/enemy/missileenemy/enemym.png";
	public static final String MissileEnemyL = "media/enemy/missileenemy/enemyl.png";
	public static final String MissileEnemyR = "media/enemy/missileenemy/enemyr.png";
	
	public static final String EnemyEye = "media/enemyeye.png";
	public static final String EnemyFactory = "media/enemy/factory/factory.png";
	public static final String EnemyFactoryDestroyed = "media/enemy/factory/factorydestroyed.png";
	public static final String PrimaryBG1 = "media/bg/PrimaryBackground.png";
	public static final String ParallaxBG = "media/bg/ParallaxStars.png";
	public static final String EnemyRedShip1 = "media/redship1.png"; 
	public static final String EnemyRedShip1b = "media/redship1.png"; 
	
	public static final String EnemyTongue = "media/enemy/tongueship/ship.png"; 
	public static final String EnemyTongueOut = "media/enemy/tongueship/shiptongue.png"; 
	
	
	public static final String EnemyKiller1 = "media/Killer1.png"; 
	public static final String EnemyKiller1b = "media/Killer1b.png"; 
	public static final String EnemySpinner = "media/spinner1.png";
	public static final String ExplosionParticle1 = "media/explosion2.p";
	public static final String ExplosionSound1 = "media/explosionsound.mp3";
	private static ParticleEffectLoader.ParticleEffectParameter param;
	public ResourceManager() {
		param.imagesDir = Gdx.files.internal("media");
	}
	public static void loadAll() {
		assetManager.load(Player,Texture.class); 
		assetManager.load(Hitbox,Texture.class); 
		assetManager.load(PlayerBulletSprite,Texture.class);
		assetManager.load(Thrusters,ParticleEffect.class); 
		assetManager.load(PlayerBullet,ParticleEffect.class); 
		
		assetManager.load(Bullet,Texture.class);
		
		assetManager.load(Coin,Texture.class);
		
		assetManager.load(Missile,Texture.class); 
		assetManager.load(MissileParticle,ParticleEffect.class); 
		assetManager.load(MissilePickup,Texture.class);
		assetManager.load(WeaponPickup,Texture.class);
		
		assetManager.load(Enemy,Texture.class);
		assetManager.load(EnemyCargo1,Texture.class);
		
		assetManager.load(MissileEnemy,Texture.class);
		assetManager.load(MissileEnemyM,Texture.class);
		assetManager.load(MissileEnemyL,Texture.class);
		assetManager.load(MissileEnemyR,Texture.class);
			
		assetManager.load(EnemyEye,Texture.class);
		assetManager.load(EnemyFactory,Texture.class);
		assetManager.load(EnemyFactoryDestroyed,Texture.class);
		assetManager.load(PrimaryBG1,Texture.class);
		assetManager.load(ParallaxBG,Texture.class);
		assetManager.load(EnemyRedShip1,Texture.class);
		assetManager.load(EnemyRedShip1b,Texture.class);
		assetManager.load(EnemyTongue,Texture.class);
		assetManager.load(EnemyTongueOut,Texture.class);
		assetManager.load(EnemyKiller1,Texture.class);
		assetManager.load(EnemyKiller1b,Texture.class);
		assetManager.load(EnemySpinner,Texture.class);
		
		assetManager.load(ExplosionSound1,Sound.class);
		assetManager.load(ExplosionParticle1,ParticleEffect.class,param); 
		assetManager.finishLoading();
	}
	public static AssetManager getAssetManager() {
		return assetManager;
	}

}