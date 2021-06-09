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
    
    public List<ArticleVendu> afficherTous() {
        List<ArticleVendu> listArticle = new ArrayList<ArticleVendu>();
        
        listArticle = this.articleDAO.selectAllArticle();
        
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
    
    public List<ArticleVendu> afficherParCategorie(String libelle) {
        List<ArticleVendu> listArticle = new ArrayList<ArticleVendu>();

        listArticle = this.articleDAO.selectCategorie(libelle);

        return listArticle;
    }
    
    public List<ArticleVendu> afficherParRecherche(String recherche) {
        List<ArticleVendu> listArticle = new ArrayList<ArticleVendu>();
        
        listArticle = this.articleDAO.selectRecherche(recherche);
        
        return listArticle;
    }
    
    public List<ArticleVendu> afficherParRechercheEtCategorie(String recherche, String categorie) {
    	List<ArticleVendu> listArticle = new ArrayList<ArticleVendu>();
        
        listArticle = this.articleDAO.selectRechercheEtCategorie(recherche, categorie);
        
        return listArticle;
    }
    
    
}