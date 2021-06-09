package fr.eni.encheres.bo;

import java.sql.Timestamp;

public class Enchere {	//MOUAI
	private int noUtilisateur;
	private int noArticle;
	private Timestamp dateEnchere;
	private int montantEnchere;
	
	
	public Enchere() {
		
	}
	

	public Enchere(int noUtilisateur, int noArticle, Timestamp dateEnchere, int montantEnchere) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.noArticle = noArticle;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}



	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public Timestamp getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(Timestamp dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public int getMontantEnchere() {
		return montantEnchere;
	}
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}


	@Override
	public String toString() {
		return "Enchere [noUtilisateur=" + noUtilisateur + ", noArticle=" + noArticle + ", dateEnchere=" + dateEnchere
				+ ", montantEnchere=" + montantEnchere + "]";
	}

	
	
	
	
}
