package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.bo.Utilisateur;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO{

	private static final String SELECTUTILISATEURBYPSEUDO = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur, question1, question2 FROM UTILISATEURS WHERE pseudo = ?";
	private static final String INSERTUTILISATEUR = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur, question1, question2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATEUTILISATEUR = "UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ?, question1 = ?, question2 = ? WHERE no_utilisateur = ?";
	private static final String DELETEUTILISATEUR = "DELETE UTILISATEURS WHERE no_utilisateur = ?";
	private static final String UPDATECREDIT = "UPDATE UTILISATEURS SET credit = ? WHERE no_utilisateur = ?";
	private static final String SELECTCREDITUSER = "SELECT credit FROM UTILISATEURS WHERE no_utilisateur = ?";
	
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
					System.out.println("Connexion réussi");
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
					currentUtilisateur.setQuestion1(rs.getString("question1"));
					currentUtilisateur.setQuestion2(rs.getString("question2"));

				}else {
					System.out.println("MDP INCORRECT");
				}
				
				pstmt.close();
				rs.close();
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
			System.out.println("il y a deja un utilisateur avec ce pseudo");
			String alreadyExist = "il y a deja un utilisateur avec ce pseudo";
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
				pstmt.setString(12, user.getQuestion1());
				pstmt.setString(13, user.getQuestion2());
				
				
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
			
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return pseudoDispo;
	}

	@Override
	public Utilisateur UpdateUtilisateur (Utilisateur user) throws SQLException {
		// TODO Auto-generated method stub
				
				try (Connection cnx = ConnectionProvider.getConnection()){
					
					PreparedStatement pstmt = cnx.prepareStatement(UPDATEUTILISATEUR);
					
					pstmt.setInt(12, user.getNoUtilisateur());
					
					
					pstmt.setString(1, user.getPseudo());
					pstmt.setString(2, user.getNom());
					pstmt.setString(3, user.getPrenom());
					pstmt.setString(4, user.getEmail());
					pstmt.setInt(5, user.getTelephone());
					pstmt.setString(6, user.getRue());
					pstmt.setInt(7, user.getCodePostal());
					pstmt.setString(8, user.getVille());
					pstmt.setString(9, user.getMotDePasse());
					pstmt.setString(10, user.getQuestion1());
					pstmt.setString(11, user.getQuestion2());
					
					
					if (user.isValide()) {
						pstmt.executeUpdate();
						
						pstmt.close();
					}

					
				} catch (Exception e) {
					e.printStackTrace();
				}
			
		return user;
		}
	
	@Override
	public Utilisateur DeleteUtilisateur (Utilisateur user) throws SQLException {
		// TODO Auto-generated method stub
				
				try (Connection cnx = ConnectionProvider.getConnection()){
					
					PreparedStatement pstmt = cnx.prepareStatement(DELETEUTILISATEUR);
					
					pstmt.setInt(1, user.getNoUtilisateur());
					
				
						pstmt.executeUpdate();
						
						pstmt.close();

					
				} catch (Exception e) {
					e.printStackTrace();
				}
			
		return user;
		}
	
	@Override
	public Utilisateur selectUtilisateurRetrouver(Utilisateur user) throws SQLException {
		Utilisateur currentUtilisateur = new Utilisateur();

		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECTUTILISATEURBYPSEUDO);
			pstmt.setString(1, user.getPseudo());
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String question1FromDB = rs.getString("question1");
				String question2FromDB = rs.getString("question2");
				
				if (user.getQuestion1().equals(question1FromDB) && user.getQuestion2().equals(question2FromDB)) {
					System.out.println("les deux réponses sont correctes");
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
					currentUtilisateur.setQuestion1(rs.getString("question1"));
					currentUtilisateur.setQuestion2(rs.getString("question2"));
					

				}else {
					System.out.println("Au moins une réponse est incorrecte pour ce pseudo");
				}
				
			}else {
				System.out.println("aucun user a ce pseudo");
			}
			
			pstmt.close();
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return currentUtilisateur;
	}

	@Override
	public void updateCredit(int noUtilisateur , int credit) {
		
		Connection cnx;
		try {
			cnx = ConnectionProvider.getConnection();
			
			PreparedStatement pstmt = cnx.prepareStatement(UPDATECREDIT);
			
			pstmt.setInt(1, credit);
			pstmt.setInt(2, noUtilisateur);
			
			pstmt.executeUpdate();
			pstmt.close();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
	}

	@Override
	public int selectCreditUtilisateur(int noUtilisateur) {
		int credit = 0;
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECTCREDITUSER);
			pstmt.setInt(1, noUtilisateur);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				credit = rs.getInt("credit");
			}
			pstmt.close();
			rs.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return credit;
	}
}
