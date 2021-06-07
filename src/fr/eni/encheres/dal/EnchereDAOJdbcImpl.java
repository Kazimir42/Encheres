package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;

import fr.eni.encheres.bo.Enchere;

public class EnchereDAOJdbcImpl implements EnchereDAO{

	private static final String INSERTENCHERE = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) " +
			"VALUES(?, ?, ?, ?)";
	
	public void InsertEnchere(Enchere currentEnchere) {
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(INSERTENCHERE);
			
			pstmt.setInt(1, currentEnchere.getNoUtilisateur());
			pstmt.setInt(2, currentEnchere.getNoArticle());
			pstmt.setDate(3, currentEnchere.getDateEnchere());
			pstmt.setInt(4, currentEnchere.getMontantEnchere());
			
			pstmt.executeUpdate();
			
			System.out.println("Enchere ajouté");
			pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
