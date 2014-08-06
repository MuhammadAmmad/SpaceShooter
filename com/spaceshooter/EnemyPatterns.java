package com.spaceshooter;

/* EnemyPatterns class holds static methods to spawn enemy patterns. Enemy names are passed as strings, as it allows for 
 * more flexibility. 
 * 
 */
public class EnemyPatterns {
	public static void sineWave(EnemyManager enemyManager,float x,float y, int amount, String type) {

		if (type.equals("EnemyPopcorn")) {
			for (int i = 0; i < amount; i++) {
				EnemyPopcorn enemy = new EnemyPopcorn(true, ((amount-1) * 15) - (i * 15));
				enemy.setPosition(x, y + (i * (enemy.getHeight())));
				enemyManager.getList().add(enemy);
			}
		}
		
		else if (type.equals("EnemyKiller1")) {
			for (int i = 0; i < amount; i++) {
				EnemyKiller1 enemy = new EnemyKiller1(true, ((amount-1) * 15) - (i * 15));
				enemy.setPosition(x, y + (i * (enemy.getHeight())));
				enemyManager.getList().add(enemy);
			}
		}

	}
	// x,y coordinate is the bottom left corner of the blockade
	public static void blockade(EnemyManager enemyManager,float x,float y, String type
			, int xSize, int ySize) {
		
		if (type.equals("EnemyPopcorn")) {
			for (int i = 0; i < xSize;i++) {
				for (int j = 0; j < ySize; j++) {
					EnemyPopcorn enemy = new EnemyPopcorn();
					enemy.setPosition(x + (enemy.getWidth() * i * 2f),y + (enemy.getHeight() * j * 2f));
					enemyManager.getList().add(enemy);
				}
			}
		}
		else if (type.equals("EnemyTurret")) {
			for (int i = 0; i < xSize;i++) {
				for (int j = 0; j < ySize; j++) {
					EnemyTurret enemy = new EnemyTurret();
					enemy.setPosition(x + (enemy.getWidth() * i * 2f),y + (enemy.getHeight() * j * 2f));
					enemyManager.getList().add(enemy);
				}
			}
		}
		else if (type.equals("EnemyEye")) {
			for (int i = 0; i < xSize;i++) {
				for (int j = 0; j < ySize; j++) {
					EnemyEye enemy = new EnemyEye();
					enemy.setPosition(x + (enemy.getWidth() * i * 2f),y + (enemy.getHeight() * j * 2f));
					enemyManager.getList().add(enemy);
				}
			}
		}
		else if (type.equals("EnemyHoming")) {
			for (int i = 0; i < xSize;i++) {
				for (int j = 0; j < ySize; j++) {
					EnemyHoming enemy = new EnemyHoming();
					enemy.setPosition(x + (enemy.getWidth() * i * 2f),y + (enemy.getHeight() * j * 2f));
					enemyManager.getList().add(enemy);
				}
			}
		}
		else if (type.equals("EnemyMissileShip")) {
			for (int i = 0; i < xSize;i++) {
				for (int j = 0; j < ySize; j++) {
					EnemyMissileShip enemy = new EnemyMissileShip(enemyManager);
					enemy.setPosition(x + (enemy.getWidth() * i * 2f),y + (enemy.getHeight() * j * 2f));
					enemyManager.getList().add(enemy);
				}
			}
		}
		else if (type.equals("EnemyKiller1")) {
			for (int i = 0; i < xSize;i++) {
				for (int j = 0; j < ySize; j++) {
					EnemyKiller1 enemy = new EnemyKiller1();
					enemy.setPosition(x + (enemy.getWidth() * i * 2f),y + (enemy.getHeight() * j * 2f));
					enemyManager.getList().add(enemy);
				}
			}
		}
		
		else if (type.equals("EnemySpinner")) {
			for (int i = 0; i < xSize;i++) {
				for (int j = 0; j < ySize; j++) {
					EnemySpinner enemy = new EnemySpinner();
					enemy.setPosition(x + (enemy.getWidth() * i * 2f),y + (enemy.getHeight() * j * 2f));
					enemyManager.getList().add(enemy);
				}
			}
		}
		
		
	}
}