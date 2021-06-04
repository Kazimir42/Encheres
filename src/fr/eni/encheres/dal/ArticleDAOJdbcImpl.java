package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;

public class ArticleDAOJdbcImpl implements ArticleDAO{
	
	private static final String SELECTALL = "SELECT ARTICLES_VENDUS.no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, ARTICLES_VENDUS.no_utilisateur, ARTICLES_VENDUS.no_categorie, CATEGORIES.libelle, UTILISATEURS.pseudo, image " +
			" FROM ARTICLES_VENDUS" +
			" INNER JOIN CATEGORIES ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie " +
			" INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur";
	
	private static final String INSERTARTICLE = "INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie, image)" +
			" VALUES" +
			" (?, ?, ?, ?, ?, ?, ?, ?);";
	
	private static final String INSERTRETRAITARTICLE = "INSERT INTO RETRAITS(no_article, rue, code_postal, ville)" +
			" VALUES" +
			" (?, ?, ?, ?)";
	

	@Override
	public List<ArticleVendu> selectAllArticle() {
		List<ArticleVendu> listArticle = new ArrayList<>();
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECTALL);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ArticleVendu currentArticle = new ArticleVendu();
				currentArticle.setNoArticle(rs.getInt("no_article"));
				currentArticle.setNomArticle(rs.getString("nom_article"));
				currentArticle.setDescription(rs.getString("description"));
				currentArticle.setDateDebutEncheres(rs.getDate("date_debut_encheres"));
				currentArticle.setDateFinEncheres(rs.getDate("date_fin_encheres"));
				currentArticle.setMiseAPrix(rs.getInt("prix_initial"));
				currentArticle.setPrixVente(rs.getInt("prix_vente"));
				currentArticle.setNoUtilisateur(rs.getInt("no_utilisateur"));
				currentArticle.setNoCategorie(rs.getInt("no_categorie"));
				currentArticle.setLibelleCategorie(rs.getString("libelle"));
				currentArticle.setPseudoVendeur(rs.getString("pseudo"));
				currentArticle.setImage(rs.getString("image"));
				
				
				listArticle.add(currentArticle);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return listArticle;
	}

	@Override
	public void ajoutArticle(ArticleVendu theArticle, int noUtilisateur, Retrait theRetrait) {
		System.out.println("Ajout d'un nouvel article");
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement pstmt = cnx.prepareStatement(INSERTARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, theArticle.getNomArticle());
			pstmt.setString(2, theArticle.getDescription());
			pstmt.setDate(3, theArticle.getDateDebutEncheres());
			pstmt.setDate(4, theArticle.getDateFinEncheres());
			pstmt.setInt(5, theArticle.getMiseAPrix());
			pstmt.setInt(6, noUtilisateur);
			pstmt.setInt(7, theArticle.getNoCategorie());
			pstmt.setString(8, theArticle.getImage());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				
				int leNumArticle = rs.getInt(1);
				
				PreparedStatement pstmt2 = cnx.prepareStatement(INSERTRETRAITARTICLE);
				pstmt2.setInt(1, leNumArticle);
				pstmt2.setString(2, theRetrait.getRue());
				pstmt2.setInt(3, theRetrait.getCodePostal());
				pstmt2.setString(4, theRetrait.getVille());
				
				pstmt2.executeUpdate();
				pstmt2.close();
			}

			pstmt.close();
			
			

			


			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
