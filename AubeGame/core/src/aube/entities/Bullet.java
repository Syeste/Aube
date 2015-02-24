package aube.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Bullet {

	private TextureRegion currentFrameBullet;
	private Texture AppaBullet;
	private Float PosX,PosY;
	
	public Bullet(int startPointX,int startPointY){
		
		PosX = (float)startPointX;
		PosY = (float)startPointY;
		AppaBullet = new Texture("img/Bullet.png");
	}
}
