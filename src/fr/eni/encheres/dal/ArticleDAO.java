package fr.eni.encheres.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;

public interface ArticleDAO {
	
	public List<ArticleVendu> selectAllArticle(boolean [] filtreArticles, int numUtilisateur);

	
	public void ajoutArticle(ArticleVendu theArticle, int noUtilisateur, Retrait currentRetrait);

	public ArticleVendu detailArticle(int no_article) throws SQLException;

    public Retrait detailRetrait(int no_article) throws SQLException;
	
    public void updatePrixVente(int noUtilisateur, int prixVente) throws SQLException;
    
    public List<ArticleVendu> selectCategorie(String libelle, boolean [] filtreArticles, int numUtilisateur);

    public List<ArticleVendu> selectRecherche(String recherche, boolean [] filtreArticles, int numUtilisateur);

    public List<ArticleVendu> selectRechercheEtCategorie(String recherche, String categorie, boolean [] filtreArticles, int numUtilisateur);

    public void deleteArticle(int noArticle);
    
	public void modifierArticle(ArticleVendu theArticle, Retrait currentRetrait);
	
	

}
