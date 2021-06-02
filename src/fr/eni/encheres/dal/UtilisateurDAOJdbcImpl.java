package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.bo.Utilisateur;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO{

	private static final String SELECTUTILISATEURBYPSEUDO = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE pseudo = ?";
	private static final String INSERTUTILISATEUR = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	@Override
	public Utilisateur selectUtilisateurByPseudoAndPassword(Utilisateur user) throws SQLException {
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
					
					pstmt.close();
					rs.close();
				}else {
					System.out.println("MDP INCORECT");
				}	
			}else {
				System.out.println("aucun user a ce pseudo");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return currentUtilisateur;
	}

	@Override
	public Utilisateur InsertUtilisateur(Utilisateur user) throws SQLException {
		//Utilisateur currentUtilisateur = new Utilisateur();
		
		if (!checkPseudoUtilisateurDispo(user)) {
			System.out.println("il y a deja un utilisateur avec de pseudo");
			String alreadyExist = "il y a deja un utilisateur avec de pseudo";
			user.setPseudo("");
			user.setError(alreadyExist);
		}else {
			System.out.println("on peut créer le compte");
			
			try (Connection cnx = ConnectionProvider.getConnection()){
				
				PreparedStatement pstmt = cnx.prepareStatement(INSERTUTILISATEUR, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, user.getPseudo());
				pstmt.setString(2, user.getNom());
				pstmt.setString(3, user.getPrenom());
				pstmt.setString(4, user.getEmail());
				pstmt.setInt(5, user.getTelephone());
				pstmt.setString(6, user.getRue());
				pstmt.setInt(7, user.getCodePostal());
				pstmt.setString(8, user.getVille());
				pstmt.setString(9, user.getMotDePasse());
				pstmt.setInt(10, 100);
				pstmt.setByte(11, (byte) 0);
				
				
				if (user.isValide()) {
					pstmt.executeUpdate();
					ResultSet rs = pstmt.getGeneratedKeys();
					
					if (rs.next()) {
						user.setNoUtilisateur(rs.getInt(1));
					}
					
					System.out.println("Utilisateur crée");
					
					pstmt.close();
					rs.close();
				}

				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}


		
		return user;
	}

	@Override
	public boolean checkPseudoUtilisateurDispo(Utilisateur user) throws SQLException {

		boolean pseudoDispo = true;
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECTUTILISATEURBYPSEUDO);
			pstmt.setString(1, user.getPseudo());
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				System.out.println("qqun a deja ce pseudo");
				pseudoDispo = false;
			}else {
				System.out.println("aucun user a ce pseudo");
				pseudoDispo = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return pseudoDispo;
	}

}
