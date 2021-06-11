package fr.eni.encheres.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {

	public Utilisateur selectUtilisateurByPseudoAndPassword(Utilisateur user) throws SQLException;
	
	public boolean checkPseudoUtilisateurDispo(Utilisateur user) throws SQLException;

	public Utilisateur InsertUtilisateur(Utilisateur user) throws SQLException;
	
	public Utilisateur UpdateUtilisateur(Utilisateur user) throws SQLException;
	
	public Utilisateur DeleteUtilisateur(Utilisateur user) throws SQLException;

	public Utilisateur selectUtilisateurRetrouver(Utilisateur user) throws SQLException;
	
	public void updateCredit(int noUtilisateur, int credit);
	
	public int selectCreditUtilisateur(int noUtilisateur);
	
	public List<Utilisateur> SelectAllUtilisateur();
	
	public Utilisateur selectUtilisateurByPseudo(Utilisateur user) throws SQLException;


}
