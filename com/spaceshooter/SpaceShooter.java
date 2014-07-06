package com.spaceshooter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpaceShooter extends Game {

	private MenuScreen menuScreen;
	private static boolean isMuted;
	@Override
	public void create() {
		isMuted = false;
		menuScreen = new MenuScreen(this);
		setScreen(menuScreen);
	
	}
	public static boolean isMuted() {
		return isMuted;
	}
	public static void muteSound() {
		isMuted = true;
	}
	public static int getLeftBound() {
		return -1 * (Gdx.graphics.getWidth() / 2);
	}

	public static int getRightBound() {
		return (Gdx.graphics.getWidth() / 2);
	}

	public static int getTopBound() {
		return (Gdx.graphics.getHeight() / 2);
	}

	public static int getBottomBound() {
		return -1 * (Gdx.graphics.getHeight() / 2);
	}
	//Normalize the speed of bullets across the game.
	public static float getBulletSpeed() {
		return 175f;
	}
}
