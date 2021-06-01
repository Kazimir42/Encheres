package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.bo.Utilisateur;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO{

	private static final String SELECTUTILISATEURBYPSEUDO = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE pseudo = ?";
	
	@Override
	public Utilisateur selectUtilisateur(Utilisateur user) throws SQLException {
		
		Utilisateur currentUtilisateur = new Utilisateur();

		
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECTUTILISATEURBYPSEUDO);
			pstmt.setString(1, user.getPseudo());
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				String mdpFromeDB = rs.getString("mot_de_passe");
				
				if (user.getMotDePasse().equals(mdpFromeDB)) {
					
					System.out.println("mdp correct pour le pseudo entré");
					
					currentUtilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
					currentUtilisateur.setPseudo(rs.getString("pseudo"));
					currentUtilisateur.setNom(rs.getString("nom"));
					currentUtilisateur.setPrenom(rs.getString("prenom"));
					currentUtilisateur.setEmail(rs.getString("email"));
					currentUtilisateur.setTelephone(rs.getInt("telephone"));
					currentUtilisateur.setRue(rs.getString("rue"));
					currentUtilisateur.setCodePostal(rs.getInt("code_postal"));
					currentUtilisateur.setVille(rs.getString("ville"));
					currentUtilisateur.setMotDePasse(rs.getString("mot_de_passe"));
					currentUtilisateur.setCredit(rs.getInt("credit"));
					currentUtilisateur.setAdministrateur(rs.getByte("administrateur"));

				}else {
					System.out.println("MDP INCORECT");
				}
				
				//int theId = rs.getInt("no_utilisateur");
				
			}else {
				System.out.println("aucun user a ce pseudo");
			}
			
		} catch (SQLException e) {

		}
		
		return currentUtilisateur;
	}

}
