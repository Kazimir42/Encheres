package fr.eni.encheres.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	public Utilisateur retrouver(String pseudo, String question1, String question2) {
        
        Utilisateur currentUtilisateur = new Utilisateur();
        currentUtilisateur.setPseudo(pseudo);
        currentUtilisateur.setQuestion1(question1);
        currentUtilisateur.setQuestion2(question2);
    
        try {
            
            currentUtilisateur = this.utilisateurDAO.selectUtilisateurRetrouver(currentUtilisateur);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentUtilisateur;
    }
	
	public void updateCredit(int noUtilisateur ,int credit) {
		this.utilisateurDAO.updateCredit(noUtilisateur, credit);
	}
	
	public int recupererCreditByNo(int noUtilisateur){
		int credit = this.utilisateurDAO.selectCreditUtilisateur(noUtilisateur);
		
		return credit;
	}
	
	public List<Utilisateur> selectionnerTous() {
		
		List<Utilisateur> listUtilisateur = new ArrayList<Utilisateur>();
	
		listUtilisateur = this.utilisateurDAO.SelectAllUtilisateur();

		return listUtilisateur;
	}
	
	public Utilisateur selectionnerParPseudo(String pseudo) {
        Utilisateur currentUtilisateur = new Utilisateur();
        currentUtilisateur.setPseudo(pseudo);

        try {

            currentUtilisateur = this.utilisateurDAO.selectUtilisateurByPseudo(currentUtilisateur);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentUtilisateur;
    }
	
}
