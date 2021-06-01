package fr.eni.encheres.bll;

import java.sql.SQLException;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;

public class UtilisateurManager {

	private UtilisateurDAO utilisateurDAO;
	
	public UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	
	public Utilisateur selectionner(String pseudo, String mdp) {
		
		Utilisateur currentUtilisateur = new Utilisateur();
		currentUtilisateur.setPseudo(pseudo);
		currentUtilisateur.setMotDePasse(mdp);
	
		try {
			
			currentUtilisateur = this.utilisateurDAO.selectUtilisateur(currentUtilisateur);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currentUtilisateur;
	}
	
}
