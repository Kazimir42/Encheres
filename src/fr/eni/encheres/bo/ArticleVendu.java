package fr.eni.encheres.bo;

import java.sql.Timestamp;

public class ArticleVendu extends Categorie{
	private int noArticle;
	private String nomArticle;
	private String description;
	private Timestamp dateDebutEncheres;
	private Timestamp dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private int noUtilisateur;
	private int noCategorie;
	private String libelleCategorie;
	private String pseudoVendeur;
	private boolean etatVente; //article est il enchérissable ou non
	private String image;
	private int noUtilisateurEncherisseur;
	private String pseudoUtilisateurEncherisseur;





	/*
	 * 
	 * MODIF PAS SUR
	 * 
	 */
	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getLibelleCategorie() {
		return libelleCategorie;
	}

	public void setLibelleCategorie(String libelleCategorie) {
		this.libelleCategorie = libelleCategorie;
	}
	
	
	
	public int getNoUtilisateurEncherisseur() {
		return noUtilisateurEncherisseur;
	}

	public void setNoUtilisateurEncherisseur(int noUtilisateurEncherisseur) {
		this.noUtilisateurEncherisseur = noUtilisateurEncherisseur;
	}

	public String getPseudoUtilisateurEncherisseur() {
		return pseudoUtilisateurEncherisseur;
	}

	public void setPseudoUtilisateurEncherisseur(String pseudoUtilisateurEncherisseur) {
		this.pseudoUtilisateurEncherisseur = pseudoUtilisateurEncherisseur;
	}

	
	/*
	 * 
	 * 
	 */
	
	

	public ArticleVendu() {
		
	}

	public ArticleVendu(int noArticle, String nomArticle, String description, Timestamp dateDebutEncheres,
			Timestamp dateFinEncheres, int miseAPrix, int prixVente, boolean etatVente) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
	}
	
	
	public int getNoArticle() {
		return noArticle;
	}
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	public String getNomArticle() {
		return nomArticle;
	}
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getDateDebutEncheres() {
		return dateDebutEncheres;
	}
	public void setDateDebutEncheres(Timestamp dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}
	public Timestamp getDateFinEncheres() {
		return dateFinEncheres;
	}
	public void setDateFinEncheres(Timestamp dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}
	public int getMiseAPrix() {
		return miseAPrix;
	}
	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}
	public int getPrixVente() {
		return prixVente;
	}
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}
	public boolean isEtatVente() {
		return etatVente;
	}
	public void setEtatVente(boolean etatVente) {
		this.etatVente = etatVente;
	}
	
	public String getPseudoVendeur() {
		return pseudoVendeur;
	}

	public void setPseudoVendeur(String pseudoVendeur) {
		this.pseudoVendeur = pseudoVendeur;
	}
	
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	

	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", prixVente=" + prixVente + ", noUtilisateur=" + noUtilisateur + ", noCategorie="
				+ noCategorie + ", libelleCategorie=" + libelleCategorie + ", pseudoVendeur=" + pseudoVendeur
				+ ", etatVente=" + etatVente + "]";
	}
	
}
