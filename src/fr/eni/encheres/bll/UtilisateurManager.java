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
			
			currentUtilisateur = this.utilisateurDAO.selectUtilisateurByPseudoAndPassword(currentUtilisateur);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return currentUtilisateur;
	}
	
	
	public Utilisateur ajouter(Utilisateur user) {

		try {
			user = this.utilisateurDAO.InsertUtilisateur(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public Utilisateur modifier(Utilisateur user) {

        try {
            user = this.utilisateurDAO.UpdateUtilisateur(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
	
	public Utilisateur supprimer(Utilisateur user) {

        try {
            user = this.utilisateurDAO.DeleteUtilisateur(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
	
}
