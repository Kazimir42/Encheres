package fr.eni.encheres.dal;

import java.sql.SQLException;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {

	public Utilisateur selectUtilisateurByPseudoAndPassword(Utilisateur user) throws SQLException;
	
	public boolean checkPseudoUtilisateurDispo(Utilisateur user) throws SQLException;

	public Utilisateur InsertUtilisateur(Utilisateur user) throws SQLException;
	
	public Utilisateur UpdateUtilisateur(Utilisateur user) throws SQLException;
	
}
