package fr.eni.encheres.bll;


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

}
