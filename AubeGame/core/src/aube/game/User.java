package aube.game;

import aube.entities.Personnage;

public class User {

	private String nom;
	private Personnage personnage;
	
	public User(){
		
	}
	
	public void setNom(String nNom){
		nom = nNom;
	}
	
	public String getNom(){
		return nom;
	}
	
	public void createPlayer(){
		personnage = new Personnage();
	}
	
	public Personnage getPlayer(){
		return personnage;
	}
	
	public void disposePersonnage(){
		this.personnage.dispose();
		
	}
}


