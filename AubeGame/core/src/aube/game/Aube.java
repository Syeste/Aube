package aube.game;


import aube.screen.MainMenu;
import aube.screen.Screen1;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class Aube extends Game{

	private Music testMusique;
	
	
	@Override
	public void create() {
		
		testMusique = Gdx.audio.newMusic(Gdx.files.internal("music/TestMusic.mp3"));
		testMusique.setLooping(true);
//		testMusique.play();
//		this.setScreen(new Screen1());
		this.setScreen(new MainMenu(this));

	}
	
	public void setGameFirstConnect(){
		
		this.setScreen(new Screen1());		
	}
	
	
	

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		
	}

	@Override
	public void render() {
		super.render();
		
	}

	@Override
	public void pause() {
		super.pause();
		
	}

	@Override
	public void resume() {
		super.resume();
		
	}

	@Override
	public void dispose() {
		super.dispose();
		testMusique.dispose();
	}

}

