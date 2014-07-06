package com.spaceshooter;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class LevelScreen implements Screen {
	private SpaceShooter game;
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture bgtexture;
	private Sprite bgsprite;

	private Texture bgtexture2;
	private Sprite bgsprite2;

	private float scrollTimer = 0.0f;

	private Music bgm;

	private BulletManager bulletManager;
	private EnemyManager enemyManager;
	private PlayerManager playerManager;
	private ScoreHandler scoreHandler;
	private PickupManager pickupManager;
	
	public LevelScreen(SpaceShooter game) {
		this.game = game;
		Gdx.input.setCursorCatched(true);
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(w, h);
		batch = new SpriteBatch();
		// Load the stars with alpha channel
		bgtexture = ResourceManager.getAssetManager().get(
				ResourceManager.PrimaryBG1, Texture.class);
		bgtexture.setWrap(Texture.TextureWrap.Repeat,
				Texture.TextureWrap.Repeat);
		
		bgtexture2 = ResourceManager.getAssetManager().get(
				ResourceManager.ParallaxBG, Texture.class);
		bgtexture2.setWrap(Texture.TextureWrap.Repeat,
				Texture.TextureWrap.Repeat);
	
		// first scroll
		bgsprite = new Sprite(bgtexture);
		bgsprite.setPosition(SpaceShooter.getLeftBound(), SpaceShooter.getBottomBound());
		// Load the stars with alpha channel
		
		// second scroll to make parallax
		bgsprite2 = new Sprite(bgtexture2);
		
		//bgsprite2.translate(getLeftBound()*2,getBottomBound());
		bgsprite2.setPosition(SpaceShooter.getLeftBound() - (bgsprite2.getWidth()/4), SpaceShooter.getLeftBound());
		bgsprite2.setColor(bgsprite2.getColor().r, bgsprite2.getColor().g,
				bgsprite2.getColor().b, 125);
		
		bulletManager = new BulletManager();
		enemyManager = new EnemyManager();
		bgm = Gdx.audio.newMusic(Gdx.files.internal("media/ethervapor.mp3"));
		bgm.setLooping(true);
		
		if (!SpaceShooter.isMuted())
			bgm.play();
		
		playerManager = new PlayerManager();

		scoreHandler = new ScoreHandler();
		pickupManager = new PickupManager();
	}
	
	@Override
	public void render(float delta) {
		
		if (Gdx.input.isKeyPressed(Keys.BACK) || Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			  game.setScreen(new MenuScreen(game));
		}
		
		scrollTimer += (Gdx.graphics.getDeltaTime() / 5);

		if (scrollTimer > 2.0f)
			scrollTimer = 0.0f;

		bgsprite.setV(0.5f * scrollTimer);
		bgsprite.setV2(0.5f * scrollTimer - 1);

		bgsprite2.setV(2f * scrollTimer);
		bgsprite2.setV2(2f * scrollTimer - 1);
		enemyManager.update(playerManager.getPlayer(), scoreHandler,
				bulletManager);
		playerManager.update(enemyManager,bulletManager, pickupManager, scoreHandler);
		pickupManager.update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		bgsprite.draw(batch);
		bgsprite2.draw(batch);
		bulletManager.update(enemyManager, batch, scoreHandler,
				playerManager.getPlayer(), pickupManager);
		enemyManager.draw(batch);
		playerManager.drawPlayer(batch);
		pickupManager.draw(batch);
		bulletManager.draw(batch);
		
		scoreHandler.displayScore(batch,SpaceShooter.getLeftBound(),
				SpaceShooter.getTopBound()); // @ Top left of screen
		
		batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		dispose();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		ResourceManager.loadAll();
	}

	@Override
	public void dispose() {
		scoreHandler.saveScore();
		batch.dispose();
		bulletManager.dispose();
		enemyManager.clear();
		bgtexture.dispose();
		bgtexture2.dispose();
		playerManager.dispose();
		pickupManager.dispose();
		ResourceManager.getAssetManager().dispose();
		
	}
	
}