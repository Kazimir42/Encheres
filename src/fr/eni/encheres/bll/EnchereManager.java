package fr.eni.encheres.bll;


import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.EnchereDAO;

public class EnchereManager {
	
	private EnchereDAO enchereDAO;
	
	
	public EnchereManager() {
		this.enchereDAO = DAOFactory.getEnchereDAO();
	}
	
	public void ajouter(Enchere currentEnchere) {
		this.enchereDAO.InsertEnchere(currentEnchere);
	}
	
	public int recupCreditEncheri(int noUtilisateur, int noArticle) {
		int creditEncherie = this.enchereDAO.SelectCreditEncherie(noUtilisateur, noArticle);
		
		return creditEncherie;
	}
	
	public List<Enchere> recupListEncherisseur(int noArticle){
		List<Enchere> listEncherriseur = new ArrayList<Enchere>();
		
		listEncherriseur = this.enchereDAO.SelectAllEncheresByNoArticle(noArticle);
		
		return listEncherriseur;
	}
	
	public void montantAZero(int noUtilisateur, int noArticle) {
		this.enchereDAO.updateToZeroByNo(noUtilisateur, noArticle);
	}

	
	public List<Enchere> recupListEncherisseurCroissant(int noArticle){
		List<Enchere> listEncherriseur = new ArrayList<Enchere>();
		
		listEncherriseur = this.enchereDAO.SelectAllEncheresByNoArticleOrderBy(noArticle);
		
		return listEncherriseur;
	}
}
