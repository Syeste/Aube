package aube.entities;


import aube.fichier.PersonnageFichier;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Personnage {
	
	private static final int    FRAME_COLS = 3;     
    private static final int    FRAME_ROWS = 4;     
    
 /*   private static final int    HAUTEUR_SKIN = 26;
    private static final int    LARGEUR_SKIN = 20;*/
    
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
	private String mapNow;

	// Touche enfoncée
	
	private boolean left,right,up,down;	
	
	// Liste des animations
	
	private enum listeAnimation {
	    DOWN,UP,RIGHT,LEFT 
	}
	private listeAnimation animationEnCours;
	
	private TextureRegion[][] textureFramePersonnage;
	private TextureRegion currentFramePersonnage;
	
	private PersonnageFichier PF;
	
	// Bullet du personnage
	
	private Bullet[] tableauDeBalle;
	int compteurDeBalle;
	
	public Personnage(){
	
		PF = new PersonnageFichier();
		PosX = 0f;
		PosY = 0f;
		
		
		PF.garnirVariableFichier(1,this);
		
		// TO CHANGE


		AppaPers = new Texture("img/BaseSkinNew.png");
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
		
		tableauDeBalle = new Bullet[100];
		compteurDeBalle = 0;
	}
	
	public String getMapNow(){
		return mapNow;
	}
	
	public TextureRegion getCurrentSprite(){

		return currentFramePersonnage;
	}
	
	public void updateSprite(float time){
		
		stateTime += time;
		
		if(stateTime > TIME_PER_FRAME){
			stateTime = 0;
			if(left == true || right == true || up == true || down == true){
				this.animationMouvementBasique();
			}
		}		
	}
	
	public void setAxeX(Float x)
	{
		PosX += x;
	}
	
	public void setAxeY(Float y)
	{
		PosY += y;
	}
	
	public void setMap(String map){
		mapNow = map;
	}
	
	public float getX()
	{
		return PosX;
	}
	
	public float getY()
	{
		return PosY;
	}
	
	public void setX(float newX)
	{
		PosX = newX;
	}

	public void setY(float newY)
	{
		PosY = newY;
	}
	
	public Personnage getPersonnage(){
		return this;
	}

	
	public int getHauteurSkinPixel()
	{
		return hauteurSkin;
	}
	
	public int getLargeurSkinPixel()
	{
		return largeurSkin;
	}
	
	public int getHitbotLargeurSkinPixel(){
		return hitboxLargeurSkin;
	}
	
	public int getHitbotHauteurSkinPixel(){
		return hitboxHauteurSkin;
	}	
	
	public void updatePersonnage(float time,int widthMap,int heightMap,boolean collisionD){

		checkMouseMouvement();
		
		if(!(this.checkCollisionCarte(widthMap, heightMap))){	
			collisionD = true;
		}
		
		if(collisionD == false)
			this.updateSprite(time);
		else
		{
			if(left == true)
				this.setAxeX(1.0f);
			if(right == true)
				this.setAxeX(-1.0f);
			if(up == true)
				this.setAxeY(-1.0f);
			if(down == true)
				this.setAxeY(1.0f);
		}
	}
	
	public void checkMouseMouvement(){
			
		int positionSourisX = Gdx.input.getX();
		int positionSourisY = Gdx.input.getY();
		
		int hauteurTempY = ((Gdx.graphics.getHeight()/2));
		int largeurTempX = ((Gdx.graphics.getWidth()/2)+((this.largeurSkin)/2)+20);

//		System.out.println(Gdx.graphics.getHeight() + " " + Gdx.graphics.getWidth());
//		System.out.println("Hauteur T X : " + hauteurTempY + "Largeur T Y : " + largeurTempX + " PosS X : " + positionSourisX + " PosS Y : " + positionSourisY);

		// PosX PosY
		
		if(positionSourisX >= largeurTempX && positionSourisY <= hauteurTempY)
		{
			//System.out.println("Croissant 1 !");
			if((positionSourisX - largeurTempX) >= (hauteurTempY - positionSourisY)){
				if(animationEnCours != listeAnimation.RIGHT){
					currentSkinNumber = 5;
					animationEnCours = listeAnimation.RIGHT;
				}
			}else{
				if(animationEnCours != listeAnimation.UP){
					currentSkinNumber = 2;
					animationEnCours = listeAnimation.UP;
				}
			}
		}else{
			if(positionSourisX <= largeurTempX && positionSourisY <= hauteurTempY){
				//System.out.println("Croissant 2 !");
				if((largeurTempX - positionSourisX) >= (hauteurTempY - positionSourisY)){
					if(animationEnCours != listeAnimation.LEFT){
						currentSkinNumber = 11;
						animationEnCours = listeAnimation.LEFT;
					}
				}else{
					if(animationEnCours != listeAnimation.UP){
						currentSkinNumber = 2;
						animationEnCours = listeAnimation.UP;
					}
				}
			}else{
				
				if(positionSourisX >= largeurTempX && positionSourisY >= hauteurTempY){
				//	System.out.println("Croissant 3 !");
					if((positionSourisX - largeurTempX) >= (positionSourisY - hauteurTempY)){
						if(animationEnCours != listeAnimation.RIGHT){
							currentSkinNumber = 5;
							animationEnCours = listeAnimation.RIGHT;
						}
					}else{
						if(animationEnCours != listeAnimation.DOWN){
							currentSkinNumber = 8;
							animationEnCours = listeAnimation.DOWN;
						}
					}
					
				}else{
					//System.out.println("Croissant 4 !");
					if(( largeurTempX - positionSourisX) >= (positionSourisY - hauteurTempY)){
						if(animationEnCours != listeAnimation.LEFT){
							currentSkinNumber = 11;
							animationEnCours = listeAnimation.LEFT;
						}
					}else{
						if(animationEnCours != listeAnimation.DOWN){
							currentSkinNumber = 8;
							animationEnCours = listeAnimation.DOWN;
						}
					}
				}
			}
		}
	}
		
	
	
	
	public void updateMouvement(){
		
		if(left == true)
			this.setAxeX(-1.0f);
		if(right == true)
			this.setAxeX(1.0f);
		if(up == true)
			this.setAxeY(1.0f);
		if(down == true)
			this.setAxeY(-1.0f);
	}
	
	public void gestionClavierEntryPersonnage(int keycode){
		
		switch(keycode){
		case Keys.LEFT:
			left = true;
			/*if(up == false && down == false){
				stateTime = 0;
				currentSkinNumber = 11;
				animationEnCours = listeAnimation.LEFT;
			}*/
			break;
		case Keys.RIGHT:
			right = true;
			/*if(up == false && down == false){
				stateTime = 0;
				currentSkinNumber = 5;
				animationEnCours = listeAnimation.RIGHT;
			}*/
			break;	
		case Keys.UP:
			up = true;
			/*if(right == false && left == false){
				stateTime = 0;
				currentSkinNumber = 2;
				animationEnCours = listeAnimation.UP;
			}*/
			break;	
		case Keys.DOWN:
			down = true;
			/*if(right == false && left == false){
				stateTime = 0;
				currentSkinNumber = 8;
				animationEnCours = listeAnimation.DOWN;
			}*/
			break;
		case Keys.SPACE:
			
			break;
		}       
		this.miseAJourTextureSkin();
	}
		
	public void animationMouvementBasique()
	{
		currentSkinNumber += mouvementSkin;
		
		switch(animationEnCours){
		case DOWN :
				if(currentSkinNumber == 9)
					mouvementSkin = -1;
				if(currentSkinNumber == 7)
					mouvementSkin = 1;
				break;
		case UP :
			if(currentSkinNumber == 3)
				mouvementSkin = -1;
			if(currentSkinNumber == 1)
				mouvementSkin = 1;
			break;
		case RIGHT:
			if(currentSkinNumber == 6)
				mouvementSkin = -1;
			if(currentSkinNumber == 4)
				mouvementSkin = 1;
			break;
		case LEFT:
			if(currentSkinNumber == 12)
				mouvementSkin = -1;
			if(currentSkinNumber == 10)
				mouvementSkin = 1;
			break;
		}

		this.miseAJourTextureSkin();				
	}
	
	public void miseAJourTextureSkin()
	{
		switch(currentSkinNumber){
		
		case 1:			currentFramePersonnage = textureFramePersonnage[0][0];
			break;
		case 2:			currentFramePersonnage = textureFramePersonnage[0][1];
			break;
		case 3:			currentFramePersonnage = textureFramePersonnage[0][2];
			break;
		case 4:			currentFramePersonnage = textureFramePersonnage[1][0];
			break;
		case 5:			currentFramePersonnage = textureFramePersonnage[1][1];
			break;
		case 6:			currentFramePersonnage = textureFramePersonnage[1][2];
			break;	
		case 7:			currentFramePersonnage = textureFramePersonnage[2][0];
			break;
		case 8:			currentFramePersonnage = textureFramePersonnage[2][1];
			break;			
		case 9:			currentFramePersonnage = textureFramePersonnage[2][2];
			break;
		case 10:		currentFramePersonnage = textureFramePersonnage[3][0];
			break;
		case 11:		currentFramePersonnage = textureFramePersonnage[3][1];
			break;			
		case 12:		currentFramePersonnage = textureFramePersonnage[3][2];
			break;				
	}
	}
	
	public boolean checkCollisionCarte(int widthMap,int heightMap){
		
		if( (this.PosY+ HITBOX_HAUTEUR_SKIN<heightMap &&  this.PosY >= 0) && (this.PosX + HITBOX_LARGEUR_SKIN<widthMap && this.PosX >= 0))
			return true;
		else
			return false;	
	}
	
	
	public void gestionClavierExitPersonnage(int keycode){
		
		switch(keycode){
			case Keys.LEFT:
					left = false;
					/*if(up == true){
						animationEnCours = listeAnimation.UP;
						currentSkinNumber = 2;
					}
					if(down == true){
						animationEnCours = listeAnimation.DOWN;
						currentSkinNumber = 8;
					}*/
			break;
			case Keys.RIGHT:
				right = false;
				/*if(up == true){
					animationEnCours = listeAnimation.UP;
					currentSkinNumber = 2;
				}
				if(down == true){
					animationEnCours = listeAnimation.DOWN;
					currentSkinNumber = 8;
				}*/
			break;
			case Keys.DOWN:
				down = false;
				/*if(right == true){
					animationEnCours = listeAnimation.RIGHT;
					currentSkinNumber = 5;
				}
				if(left == true){
					animationEnCours = listeAnimation.LEFT;
					currentSkinNumber = 11;
				}*/
			break;
			case Keys.UP:
				up = false;
				/*if(right == true){
					animationEnCours = listeAnimation.RIGHT;
					currentSkinNumber = 5;
				}
				if(left == true){
					animationEnCours = listeAnimation.LEFT;
					currentSkinNumber = 11;
				}*/
			break;
		}
        
        if(down == false && up == false && right == false && left == false){ 
	        switch(currentSkinNumber){
	        	case 1 : case 3 :	
	        		currentSkinNumber = 2;
	        		break;
	        	case 4 : case 6 :	
	        		currentSkinNumber = 5;
	        		break;        		
	        	case 7 : case 9 :	
	        		currentSkinNumber = 8;
	        		break;        		
	        	case 10 : case 12 :	
	        		currentSkinNumber = 11;
	        		break;        		        		
	        }
        }   
        this.miseAJourTextureSkin();
	}
	
	public void dispose(){
		
		this.AppaPers.dispose();
		this.currentFramePersonnage.getTexture().dispose();
	}
	
}
