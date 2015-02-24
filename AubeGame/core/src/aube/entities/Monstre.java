package aube.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Monstre {

	private static final int    FRAME_COLS = 3;     // Nombres casses sprite
    private static final int    FRAME_ROWS = 4;     // ^^
    
    private static final int    HAUTEUR_SKIN = 50;
    private static final int    LARGEUR_SKIN = 32;
    
    private static final int HITBOX_HAUTEUR_SKIN = 16;
    private static final int HITBOX_LARGEUR_SKIN = 32;
    
    private static final float  TIME_PER_FRAME = 0.25f;
    private float stateTime;
    
    private int currentSkinNumber,mouvementSkin;
	private Float PosX,PosY;
	private int hauteurSkin,largeurSkin;
	private int hitboxHauteurSkin,hitboxLargeurSkin;
	private Texture AppaPers;
	
	// Touche enfoncée
	
	private boolean left,right,up,down;	
	
	// Liste des animations
	
	private enum listeAnimation {
	    DOWN,UP,RIGHT,LEFT 
	}
	private listeAnimation animationEnCours;
	
	private TextureRegion[][] textureFramePersonnage;
	private TextureRegion currentFramePersonnage;

	
	public Monstre(){
		
		AppaPers = new Texture("img/Blop.png");
		textureFramePersonnage= TextureRegion.split(AppaPers, AppaPers.getWidth()/FRAME_COLS, AppaPers.getHeight()/FRAME_ROWS);
		currentFramePersonnage = new TextureRegion();
		currentFramePersonnage = textureFramePersonnage[2][1];

		stateTime = 0.0f;
		currentSkinNumber = 8;
		mouvementSkin = 1;
		animationEnCours = listeAnimation.DOWN;
		hauteurSkin = HAUTEUR_SKIN;
		largeurSkin = LARGEUR_SKIN;
		hitboxHauteurSkin = HITBOX_HAUTEUR_SKIN;
		hitboxLargeurSkin = HITBOX_LARGEUR_SKIN;
		
		PosX = PosY = 0f;
	}
	
	
}
