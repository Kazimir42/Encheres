package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;

public class ArticleDAOJdbcImpl implements ArticleDAO{
	
	private static final String SELECTALL = "SELECT ARTICLES_VENDUS.no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, ARTICLES_VENDUS.no_utilisateur, ARTICLES_VENDUS.no_categorie, CATEGORIES.libelle, UTILISATEURS.pseudo" +
			" FROM ARTICLES_VENDUS" +
			" INNER JOIN CATEGORIES ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie " +
			" INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur";

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
				
				listArticle.add(currentArticle);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return listArticle;
	}

}
