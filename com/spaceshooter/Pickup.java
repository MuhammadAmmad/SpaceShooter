package com.spaceshooter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

//ALL power-ups derive from this class.
public abstract class Pickup extends Sprite {
	public Pickup(Texture texture) {
		super(texture);
	}
	public abstract void update();	
}