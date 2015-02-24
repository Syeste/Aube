package aube.screen;


import aube.entities.CarteTiledMap;
import aube.entities.Personnage;
import aube.game.User;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Screen1 implements Screen, InputProcessor{

	private SpriteBatch batch;
	private Personnage P1;
	private TiledMapRenderer renderer;
	private OrthographicCamera camera;
	private CarteTiledMap carteDeJeu;
	
	private TextureAtlas atlas;
	private Skin skin;

		
	public Screen1(){
	}
	
	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		P1.updateMouvement();	
		
		boolean lol = this.carteDeJeu.getCollision((int)P1.getX(),(int)P1.getY(),P1.getHitbotHauteurSkinPixel(),P1.getHitbotLargeurSkinPixel());
		P1.updatePersonnage(Gdx.graphics.getDeltaTime(),carteDeJeu.getWidthMapPixel(),carteDeJeu.getHeightMapPixel(),lol);

			
		//Update Map
		
		this.carteDeJeu.getTabTopToRender(P1);
		
		// Layer base render
		
		renderer.render(this.carteDeJeu.getNumberBasicLayout());
		
		// Layer top base render
		
		
		renderer.render(this.carteDeJeu.getNumberTopDownLayout());
	    camera.position.set(P1.getX(), P1.getY(), 0);
	    camera.update();
	
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(P1.getCurrentSprite(), (P1.getX()), (P1.getY()));
		batch.end();	
		
		// Layer Top Render
		
		renderer.render(this.carteDeJeu.getNumberTopTopLayout());
		renderer.setView(camera);
		
		
		boolean newMap = carteDeJeu.getCollisionObjet(P1);
		if(newMap== true){
			carteDeJeu.dispose();
			carteDeJeu = new CarteTiledMap(P1);	
			renderer = new OrthogonalTiledMapRenderer(carteDeJeu.getTiledMap());
		}
			
	}

	@Override
	public void resize(int width, int height) {
		
		camera.viewportHeight = height;
		camera.viewportWidth = width;
		camera.update();
		
	}

	@Override
	public void show() {
		
		batch = new SpriteBatch();
		
		// HERE MODIF
		
		P1 = new Personnage();
		
		
		carteDeJeu = new CarteTiledMap(P1);

		
		atlas = new TextureAtlas(Gdx.files.internal("ui/button.pack"));
		skin = new Skin(Gdx.files.internal("ui/menuSkin.json"),atlas);
	
		camera = new OrthographicCamera();	
		camera.zoom = 1 / 2f;
		renderer = new OrthogonalTiledMapRenderer(carteDeJeu.getTiledMap());
		
		Gdx.input.setInputProcessor(this);	
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
		
		carteDeJeu.dispose();
		batch.dispose();

	}

	@Override
	public boolean keyDown(int keycode) {
		
        P1.gestionClavierEntryPersonnage(keycode);
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		P1.gestionClavierExitPersonnage(keycode);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
