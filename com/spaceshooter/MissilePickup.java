package com.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class MissilePickup extends Pickup {

	public MissilePickup(float xPos, float yPos) {
		super(ResourceManager.getAssetManager().get(ResourceManager.MissilePickup,Texture.class));
		setPosition(xPos,yPos);
	}

	@Override
	public void update() {
		translateY(-100 * Gdx.graphics.getDeltaTime());
	}
}