package fr.eni.encheres.dal;

import java.sql.SQLException;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {

	public Utilisateur selectUtilisateur(Utilisateur user) throws SQLException;
}
