package com.spaceshooter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScoreHandler {
	private int m_Score;
	private BitmapFont m_Font;
	private String m_String;
	private String m_HighScoreString;
	private FileHandle m_File;
	private int m_CurrentHighScore;

	public ScoreHandler() {
		m_Score = 0;
		m_Font = new BitmapFont(Gdx.files.internal("media/skinsandfonts/game/gamefont.fnt"),Gdx.files.internal("media/skinsandfonts/game/gamefont.png"),false);
		m_Font.setColor(Color.WHITE);
		m_Font.setScale(0.5f,0.5f);
		m_String = new String("SCORE: " + m_Score);
		
		m_Font.setColor(m_Font.getColor().r,m_Font.getColor().g,m_Font.getColor().b,125f);

		m_CurrentHighScore = 0;

		m_File = Gdx.files.local("data/data1.bin");
		if (m_File.exists()) {
			String scoreString = m_File.readString();
			m_CurrentHighScore = Integer.valueOf(scoreString.substring(scoreString.indexOf(":") + 1, 
			scoreString.indexOf(";")));
		}
		else {
			m_CurrentHighScore = 0;
		}
	
		m_HighScoreString = new String("HIGH SCORE: " + m_CurrentHighScore);
	}

	public ScoreHandler(int startingScore) {
		m_Score = startingScore;
		m_Font = new BitmapFont(Gdx.files.internal("media/skinsandfonts/game/gamefont.fnt"),Gdx.files.internal("media/skinsandfonts/game/gamefont.png"),false);
		m_Font.setColor(Color.WHITE);
		m_Font.setScale(0.5f,0.5f);
		m_String = new String("SCORE: " + m_Score);
		
		m_Font.setColor(m_Font.getColor().r,m_Font.getColor().g,m_Font.getColor().b,125f);

		m_CurrentHighScore = 0;

		m_File = Gdx.files.local("data/data1.bin");
		if (m_File.exists()) {
			String scoreString = m_File.readString();
			m_CurrentHighScore = Integer.valueOf(scoreString.substring(scoreString.indexOf(":") + 1, 
			scoreString.indexOf(";")));
		}
		else {
			m_CurrentHighScore = 0;
		}
	
		m_HighScoreString = new String("HIGH SCORE: " + m_CurrentHighScore);
	}

	public int getScore() {
		return m_Score;
	}

	public void setScore(int amount) {
		m_Score = amount;
	}

	public void addScore(int amount) {
		m_Score += amount; 
							
	}
	public void reduceScore(int amount) {
		m_Score -= amount;
	}

	public void displayScore(SpriteBatch batch, float x, float y) // displays
																	// the score
																	// on the
																	// screen.
																	// (use in
																	// main
																	// loop)
	{
		m_String = "SCORE: " + m_Score + " FPS: " + Gdx.graphics.getFramesPerSecond(); // dynamically set the score.
		m_HighScoreString = new String("HIGH SCORE: " + m_CurrentHighScore);
		m_Font.draw(batch, m_String, x, y);
		m_Font.draw(batch, m_HighScoreString, x, y - 50); //show it later
	}

	public void saveScore() {

		if (m_Score > m_CurrentHighScore) {
			m_CurrentHighScore = m_Score;
			m_File.writeString("SCORE:" + m_Score + ";", false);
		}
	}
}