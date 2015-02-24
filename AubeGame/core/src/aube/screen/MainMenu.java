package aube.screen;

import aube.game.Aube;


import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenu implements Screen{

	private static final String titreJeu = "Aube : Mémoire d'anciens";
	private Aube fenetrePrincipal;
	
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table;
	private TextButton buttonPlay,buttonExit;
	private Label labelTitre;

	
	public MainMenu(Aube g1){		
		fenetrePrincipal = g1;		
	}
	
	
	@Override
	public void render(float delta) {
		
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
 //       table.drawDebug(stage);
        
        stage.act();
        stage.draw();
	}

	@Override
	public void resize (int width, int height) {
	    stage.getViewport().update(width, height, true);
	}

	@Override
	public void show() {
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		atlas = new TextureAtlas(Gdx.files.internal("ui/button.pack"));
		skin = new Skin(Gdx.files.internal("ui/menuSkin.json"),atlas);
		
		table = new Table(skin);
		
		// A voir sens 
		
		table.setFillParent(true);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		buttonPlay = new TextButton("Start",skin);		
		buttonPlay.addListener(new ClickListener(){
		       @Override
		       public void clicked(InputEvent event, float x, float y) {
		    	   fenetrePrincipal.setGameFirstConnect();
		       }
		  });
		
		
		buttonExit = new TextButton("Exit",skin);
		buttonExit.addListener(new ClickListener(){
		       @Override
		       public void clicked(InputEvent event, float x, float y) {
		    	   System.out.println("Exit a faire");
		       }
		  });
		
		buttonPlay.pad(10);
		buttonExit.pad(10);


		labelTitre = new Label(titreJeu, skin);
		labelTitre.setFontScale(2);

			
		table.add(labelTitre).spaceBottom(100).colspan(2);
		table.row();
		table.add(buttonPlay).colspan(2).spaceBottom(10);
		table.row();
		table.add(buttonExit).colspan(2);
		table.debug();
		stage.addActor(table);
	}

	@Override
	public void hide() {
		
	}
	
	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		stage.dispose();
		atlas.dispose();
		skin.dispose();
	}

}
