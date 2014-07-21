package com.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class WeaponPickup extends Pickup {

	public WeaponPickup(float xPos, float yPos) {
		super(ResourceManager.getAssetManager().get(ResourceManager.WeaponPickup,Texture.class));
		setPosition(xPos,yPos);
	}

	@Override
	public void update() {
		translateY(-100 * Gdx.graphics.getDeltaTime());
		rotate(Gdx.graphics.getDeltaTime()*-100.f);
	}
}