package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;

public interface ArticleDAO {
	
	public List<ArticleVendu> selectAllArticle();
	
	public void ajoutArticle(ArticleVendu theArticle, int noUtilisateur, Retrait currentRetrait);

}
