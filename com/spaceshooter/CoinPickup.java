package com.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CoinPickup extends Pickup {
	
	private static final int FRAME_COLS = 6;
	private static final int FRAME_ROWS = 1;
	
	private Animation pickupAnimation;
	private TextureRegion[] pickupFrames;
	private TextureRegion currentFrame;
	float stateTime;
	public CoinPickup(float xPos,float yPos) {
		super(ResourceManager.getAssetManager().get(ResourceManager.Coin,Texture.class));
		
		setPosition(xPos,yPos);
		
		TextureRegion[][] tmp = TextureRegion.split(getTexture(),
				getTexture().getWidth() / FRAME_COLS, getTexture().getHeight()
						/ FRAME_ROWS);
		pickupFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				pickupFrames[index++] = tmp[i][j];
			}
		}
		pickupAnimation = new Animation(0.07f, pickupFrames);
		stateTime = 0f;
	}
	public void update() {
		translateY(-100*Gdx.graphics.getDeltaTime());
		currentFrame = pickupAnimation.getKeyFrame(stateTime,true);
	}
	
	public void draw(SpriteBatch batch) {
		stateTime += Gdx.graphics.getDeltaTime();
		
		batch.draw(currentFrame, getX(), getY());
	}
}