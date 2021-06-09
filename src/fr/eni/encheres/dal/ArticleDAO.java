package fr.eni.encheres.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;

public interface ArticleDAO {
	
	public List<ArticleVendu> selectAllArticle();
	
	public void ajoutArticle(ArticleVendu theArticle, int noUtilisateur, Retrait currentRetrait);

	public ArticleVendu detailArticle(int no_article) throws SQLException;

    public Retrait detailRetrait(int no_article) throws SQLException;
	
    public void updatePrixVente(int noUtilisateur, int prixVente) throws SQLException;
    
    public List<ArticleVendu> selectCategorie(String libelle);
    
    public List<ArticleVendu> selectRecherche(String recherche);

    public List<ArticleVendu> selectRechercheEtCategorie(String recherche, String categorie);

}
