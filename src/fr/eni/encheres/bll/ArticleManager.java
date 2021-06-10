package fr.eni.encheres.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.ArticleDAO;
import fr.eni.encheres.dal.DAOFactory;

public class ArticleManager {

    private ArticleDAO articleDAO;
    
    public ArticleManager() {
        this.articleDAO = DAOFactory.getArticleDAO();
    }
    
    public List<ArticleVendu> afficherTous(boolean [] filtreArticles, int numUtilisateur) {
        List<ArticleVendu> listArticle = new ArrayList<ArticleVendu>();

        listArticle = this.articleDAO.selectAllArticle(filtreArticles, numUtilisateur);

        return listArticle;
    }
    
    public void ajoutArticle(ArticleVendu theArticle, int noUtilisateur, Retrait currentRetrait) {
        this.articleDAO.ajoutArticle(theArticle, noUtilisateur, currentRetrait);
    }
    
    public ArticleVendu afficherDetail(int noArticle) {
        
        ArticleVendu currentArticle = new ArticleVendu();
    
        try {
            
            currentArticle = this.articleDAO.detailArticle(noArticle);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentArticle;
    }
    
    public Retrait afficherRetrait(int noArticle) {
        
        Retrait currentRetrait = new Retrait();
    
        try {
            
            currentRetrait = this.articleDAO.detailRetrait(noArticle);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentRetrait;
    }
    
    public void actualisePrixVente(int leNoArticle, int lePrix) throws SQLException {
    	this.articleDAO.updatePrixVente(leNoArticle, lePrix);
    }
    
    public List<ArticleVendu> afficherParCategorie(String libelle, boolean [] filtreArticles, int numUtilisateur) {
        List<ArticleVendu> listArticle = new ArrayList<ArticleVendu>();

        listArticle = this.articleDAO.selectCategorie(libelle, filtreArticles, numUtilisateur);

        return listArticle;
    }
    
    public List<ArticleVendu> afficherParRecherche(String recherche, boolean [] filtreArticles, int numUtilisateur) {
        List<ArticleVendu> listArticle = new ArrayList<ArticleVendu>();
        
        listArticle = this.articleDAO.selectRecherche(recherche, filtreArticles, numUtilisateur);
        
        return listArticle;
    }
    
    public List<ArticleVendu> afficherParRechercheEtCategorie(String recherche, String categorie, boolean [] filtreArticles, int numUtilisateur) {
        List<ArticleVendu> listArticle = new ArrayList<ArticleVendu>();
        
        listArticle = this.articleDAO.selectRechercheEtCategorie(recherche, categorie, filtreArticles, numUtilisateur);
        
        return listArticle;
    }
    
    public void supprimer(int noArticle) {
    	this.articleDAO.deleteArticle(noArticle);
    }
    
    public void modifierArticle(ArticleVendu theArticle, Retrait currentRetrait) {
        this.articleDAO.modifierArticle(theArticle, currentRetrait);
    }
    
}