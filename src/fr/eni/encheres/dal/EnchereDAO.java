package fr.eni.encheres.dal;


import java.util.List;

import fr.eni.encheres.bo.Enchere;

public interface EnchereDAO {

	public void InsertEnchere(Enchere currentEnchere);
	
	public int SelectCreditEncherie(int noUtilisateur, int noArticle);
	
	public List<Enchere> SelectAllEncheresByNoArticle(int noArticle);
	
	public void updateToZeroByNo(int utilisateur, int noArticle);
	
	public List<Enchere> SelectAllEncheresByNoArticleOrderBy(int noArticle);

}
