package aube.entities;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PersonnageNonJoueur {

	private static final int    FRAME_COLS = 3;     
    private static final int    FRAME_ROWS = 4;   	
    
    // PAS FORCEMENT STATIQUE !!!
    
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
	
	private enum listeAnimation {
	    DOWN,UP,RIGHT,LEFT 
	}
	private listeAnimation animationEnCours;
	
	private TextureRegion[][] textureFramePersonnage;
	private TextureRegion currentFramePersonnage;
	
	
	public PersonnageNonJoueur(){
		
	}
	
}
