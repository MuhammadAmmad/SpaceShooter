package com.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScoreHandler {
	private int score;
	private BitmapFont font;
	private String string;
	private String highScoreString;
	private FileHandle file;
	private int currentHighScore;

	{
		score = 0;
		font = new BitmapFont(
				Gdx.files.internal("media/skinsandfonts/game/gamefont.fnt"),
				Gdx.files.internal("media/skinsandfonts/game/gamefont.png"),
				false);
		font.setColor(Color.WHITE);
		font.setScale(0.5f, 0.5f);
		string = new String("SCORE: " + score);

		font.setColor(font.getColor().r, font.getColor().g, font.getColor().b,
				125f);

		currentHighScore = 0;

		file = Gdx.files.local("data/data1.bin");
		if (file.exists()) {
			String scoreString = file.readString();
			currentHighScore = Integer.valueOf(scoreString.substring(
					scoreString.indexOf(":") + 1, scoreString.indexOf(";")));
		} else {
			currentHighScore = 0;
		}

		highScoreString = new String("HIGH SCORE: " + currentHighScore);
	}

	public ScoreHandler() {
	}

	public ScoreHandler(int startingScore) {
		score = startingScore;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int amount) {
		score = amount;
	}

	public void addScore(int amount) {
		score += amount;

	}

	public void reduceScore(int amount) {
		score -= amount;
	}

	public void displayScore(SpriteBatch batch, float x, float y) // displays
																	// the score
																	// on the
																	// screen.
																	// (use in
																	// main
																	// loop)
	{
		string = "SCORE: " + score + " FPS: "
				+ Gdx.graphics.getFramesPerSecond(); // dynamically set the
														// score.
		highScoreString = new String("HIGH SCORE: " + currentHighScore);
		font.draw(batch, string, x, y);
		font.draw(batch, highScoreString, x, y - 50); // show it later
	}

	public void saveScore() {

		if (score > currentHighScore) {
			currentHighScore = score;
			file.writeString("SCORE:" + score + ";", false);
		}
	}
}