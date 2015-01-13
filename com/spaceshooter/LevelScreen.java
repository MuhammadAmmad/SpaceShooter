package com.spaceshooter;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
<<<<<<< HEAD
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
=======
>>>>>>> 0d81a170628515199babf17e63fd265a564eaea1
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class LevelScreen implements Screen {

	private OrthographicCamera camera;
	private SpriteBatch batch;
<<<<<<< HEAD
	private Rectangle viewport;
	
=======
>>>>>>> 0d81a170628515199babf17e63fd265a564eaea1
	private Texture bgtexture;
	private Sprite bgsprite;

	private Texture bgtexture2;
	private Sprite bgsprite2;

	private float scrollTimer = 0.0f;
	
	private BulletManager bulletManager;
	private EnemyManager enemyManager;
	private PlayerManager playerManager;
	private ScoreHandler scoreHandler;
	private PickupManager pickupManager;
	
<<<<<<< HEAD
	private static final int VIRTUAL_WIDTH = 480;
	private static final int VIRTUAL_HEIGHT = 800;
	private static final float ASPECT_RATIO = (float)VIRTUAL_WIDTH/(float)VIRTUAL_HEIGHT;
	
=======
>>>>>>> 0d81a170628515199babf17e63fd265a564eaea1
	//Main level screen.
	public LevelScreen() {
		Gdx.input.setCursorCatched(true);
		Gdx.input.setCatchBackKey(false);
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

<<<<<<< HEAD
		camera = new OrthographicCamera(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
=======
		camera = new OrthographicCamera(w, h);
>>>>>>> 0d81a170628515199babf17e63fd265a564eaea1
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
		
		// second scroll to make parallax
		bgsprite2 = new Sprite(bgtexture2);
		
		bgsprite2.setPosition(SpaceShooter.getLeftBound() - (bgsprite2.getWidth()/4), SpaceShooter.getBottomBound());
		bgsprite2.setColor(bgsprite2.getColor().r, bgsprite2.getColor().g,
				bgsprite2.getColor().b, 125);
		
		bulletManager = new BulletManager();
		enemyManager = new EnemyManager();
		
		
		playerManager = new PlayerManager();

		scoreHandler = new ScoreHandler();
		pickupManager = new PickupManager();
	}
<<<<<<< HEAD
	@Override
    public void resize(int width, int height) {
		
		scoreHandler.saveScore();
        // calcualte the viewport.
        float aspectRatio = (float)width/(float)height;
        float scale = 1f;
        Vector2 crop = new Vector2(0f, 0f); 
        if(aspectRatio > ASPECT_RATIO)
        {
            scale = (float)height/(float)VIRTUAL_HEIGHT;
            crop.x = (width - VIRTUAL_WIDTH*scale)/2f;
        }
        else if(aspectRatio < ASPECT_RATIO)
        {
            scale = (float)width/(float)VIRTUAL_WIDTH;
            crop.y = (height - VIRTUAL_HEIGHT*scale)/2f;
        }
        else
        {
            scale = (float)width/(float)VIRTUAL_WIDTH;
        }

        float w = (float)VIRTUAL_WIDTH*scale;
        float h = (float)VIRTUAL_HEIGHT*scale;
        viewport = new Rectangle(crop.x, crop.y, w, h);
    }
=======
	
>>>>>>> 0d81a170628515199babf17e63fd265a564eaea1
	@Override
	public void render(float delta) {
		//For desktop version.
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			 Gdx.app.exit();
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
<<<<<<< HEAD
		
		camera.update();
		// We need to set the viewport:
		Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                (int) viewport.width, (int) viewport.height);
		
=======
>>>>>>> 0d81a170628515199babf17e63fd265a564eaea1
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
<<<<<<< HEAD
=======
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		scoreHandler.saveScore();
	}

	@Override
>>>>>>> 0d81a170628515199babf17e63fd265a564eaea1
	public void show() {
		ResourceManager.reLoad();
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
		bgsprite2.setPosition(SpaceShooter.getLeftBound() - (bgsprite2.getWidth()/4), SpaceShooter.getBottomBound());
		bgsprite2.setColor(bgsprite2.getColor().r, bgsprite2.getColor().g,
				bgsprite2.getColor().b, 125);
		
		bulletManager = new BulletManager();
		enemyManager = new EnemyManager();
		
		
		playerManager = new PlayerManager();
		pickupManager = new PickupManager();
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		scoreHandler.saveScore();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		ResourceManager.reLoad();
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
		bgsprite2.setPosition(SpaceShooter.getLeftBound() - (bgsprite2.getWidth()/4), SpaceShooter.getBottomBound());
		bgsprite2.setColor(bgsprite2.getColor().r, bgsprite2.getColor().g,
				bgsprite2.getColor().b, 125);
		
		bulletManager = new BulletManager();
		enemyManager = new EnemyManager();
		
		
		playerManager = new PlayerManager();
		pickupManager = new PickupManager();
	}

	@Override
	public void dispose() {
		scoreHandler.saveScore();
		batch.dispose();
		bulletManager.dispose();
		enemyManager.clear();
		bgtexture.dispose();
		bgtexture2.dispose();
		pickupManager.dispose();
		ResourceManager.getAssetManager().dispose();
		
	}
	
}