package aube.fichier;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import aube.entities.Personnage;


public class PersonnageFichier {
	
	private static final int    TAILLE_FICHIER_PERSONNAGE = 4;     
	
	String nomHero,positionCarte;
	int posX,posY;
	

	
	public void garnirVariableFichier(int positionFichier,Personnage P1){
		
		positionFichier = 1;
		String lignePersonnage = "Personnage" + positionFichier;
		
		FileHandle file = Gdx.files.internal("fichierGame/PersonnageInfo.txt");
		String contenuFichier; 
		contenuFichier = file.readString();
		
		String[] ligneFichier = contenuFichier.split(":");
		int index = 0;
		
		for(int x = 0;x < 3;x++){
		
			if(ligneFichier[index].equals(lignePersonnage)){
				
				for(int y = 0;y <= TAILLE_FICHIER_PERSONNAGE;y++){
	    			
	    			switch(y){
	    				case 1:;
	    				break;
	    				case 2:
	    					P1.setAxeX(Float.parseFloat(ligneFichier[index]));
	    				break;
	    				case 3:
	    					P1.setAxeY(Float.parseFloat(ligneFichier[index]));
	    				break;
	    				case 4:
	    					P1.setMap(ligneFichier[index]);	
	    				break;
	    			}
	    			index++;
				}		
			}
			else{
				
	    		for(int y = 0;y <= TAILLE_FICHIER_PERSONNAGE;y++){
	    	    	index++;
	    		}
	    	}    	
		}		
		
	}
	
	
	public void saveDonneeHero(Personnage P1){
		
		// TODO SAVE
		
	}
	
	
}

