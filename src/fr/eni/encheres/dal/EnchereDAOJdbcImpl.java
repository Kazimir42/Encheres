package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Enchere;

public class EnchereDAOJdbcImpl implements EnchereDAO{

	private static final String INSERTENCHERE = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) " +
			"VALUES(?, ?, ?, ?)";
	
	private static final String UPDATEENCHERE = "UPDATE ENCHERES SET date_enchere = ?, montant_enchere = ? WHERE no_utilisateur = ?";
	
	private static final String SELECTPRIXENCHERE = "SELECT montant_enchere FROM ENCHERES WHERE no_article = ? AND no_utilisateur = ?";
	
	private static final String SELECTENCHERRISEURPARNUMEROARTICLE = "SELECT no_utilisateur, montant_enchere FROM ENCHERES WHERE no_article = ?";
	
	private static final String UPDATETOZEROMONTANT = "UPDATE ENCHERES SET montant_enchere = ? WHERE no_utilisateur = ? AND no_article = ?";
	
	private static final String SELECTENCHERRISEURPARNUMEROARTICLEORDERBY = "SELECT no_utilisateur, montant_enchere FROM ENCHERES WHERE no_article = ? ORDER BY date_enchere ";
	
	public void InsertEnchere(Enchere currentEnchere) {
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(INSERTENCHERE);
			
			pstmt.setInt(1, currentEnchere.getNoUtilisateur());
			pstmt.setInt(2, currentEnchere.getNoArticle());
			pstmt.setTimestamp(3, currentEnchere.getDateEnchere());
			pstmt.setInt(4, currentEnchere.getMontantEnchere());
			
			pstmt.executeUpdate();
			
			System.out.println("Enchere ajouté");
			pstmt.close();
			
			
		} catch (SQLException e) {
			
			try (Connection cnx = ConnectionProvider.getConnection()){
				
				PreparedStatement pstmt = cnx.prepareStatement(UPDATEENCHERE);
				
				pstmt.setTimestamp(1, currentEnchere.getDateEnchere());
				pstmt.setInt(2, currentEnchere.getMontantEnchere());
				pstmt.setInt(3, currentEnchere.getNoUtilisateur());
				
				pstmt.executeUpdate();
				
				System.out.println("Enchere mise a jour");
				pstmt.close();
				
				
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			
		}
		
		
	}

	@Override
	public int SelectCreditEncherie(int noUtilisateur, int noArticle) {
		int creditEncherie = 0;
		
		try (Connection cnx = ConnectionProvider.getConnection()){

			PreparedStatement pstmt = cnx.prepareStatement(SELECTPRIXENCHERE);
			
			pstmt.setInt(1, noArticle);
			pstmt.setInt(2, noUtilisateur);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				creditEncherie = rs.getInt("montant_enchere");
				
				System.out.println("L'user  avait déja enchérie : " + creditEncherie + " coins sur cette article"); 	

			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return creditEncherie;
	}

	@Override
	public List<Enchere> SelectAllEncheresByNoArticle(int noArticle) {
		List<Enchere> listEncherriseur = new ArrayList<Enchere>();
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECTENCHERRISEURPARNUMEROARTICLE);
			pstmt.setInt(1, noArticle);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Enchere uneEnchere = new Enchere();
				uneEnchere.setNoUtilisateur(rs.getInt("no_utilisateur"));
				uneEnchere.setMontantEnchere(rs.getInt("montant_enchere"));
				uneEnchere.setNoArticle(noArticle);
				
				listEncherriseur.add(uneEnchere);
			}
			pstmt.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listEncherriseur;
	}

	@Override
	public void updateToZeroByNo(int utilisateur, int noArticle) {
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(UPDATETOZEROMONTANT);
			
			pstmt.setInt(1, 0);
			pstmt.setInt(2, utilisateur);
			pstmt.setInt(3, noArticle);
			
			pstmt.executeUpdate();
			
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<Enchere> SelectAllEncheresByNoArticleOrderBy(int noArticle) {
		List<Enchere> listEncherriseur = new ArrayList<Enchere>();
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECTENCHERRISEURPARNUMEROARTICLEORDERBY);
			pstmt.setInt(1, noArticle);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Enchere uneEnchere = new Enchere();
				uneEnchere.setNoUtilisateur(rs.getInt("no_utilisateur"));
				uneEnchere.setMontantEnchere(rs.getInt("montant_enchere"));
				uneEnchere.setNoArticle(noArticle);
				
				listEncherriseur.add(uneEnchere);
			}
			pstmt.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listEncherriseur;
	}
	
	
}
