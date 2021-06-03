package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;

public interface ArticleDAO {
	
	public List<ArticleVendu> selectAllArticle();

}
