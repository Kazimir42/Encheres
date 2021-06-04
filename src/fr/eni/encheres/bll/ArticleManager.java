package fr.eni.encheres.bll;

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
	
}
