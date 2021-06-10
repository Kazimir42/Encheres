package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;

public class ArticleDAOJdbcImpl implements ArticleDAO{
	
	private static final String SELECTALL = "SELECT ARTICLES_VENDUS.no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, ARTICLES_VENDUS.no_utilisateur, ARTICLES_VENDUS.no_categorie, CATEGORIES.libelle, UTILISATEURS.pseudo, image " +
            " FROM ARTICLES_VENDUS" +
            " INNER JOIN CATEGORIES ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie " +
            " INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur" +
            " LEFT JOIN ENCHERES ON ARTICLES_VENDUS.no_article = ENCHERES.no_article";
	
	private static final String INSERTARTICLE = "INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie, image)" +
			" VALUES" +
			" (?, ?, ?, ?, ?, ?, ?, ?);";
	
	private static final String INSERTRETRAITARTICLE = "INSERT INTO RETRAITS(no_article, rue, code_postal, ville)" +
			" VALUES" +
			" (?, ?, ?, ?)";
	/*
	private static final String SELECTARTICLE = "SELECT ARTICLES_VENDUS.no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, ARTICLES_VENDUS.no_utilisateur, ARTICLES_VENDUS.no_categorie, CATEGORIES.libelle, UTILISATEURS.pseudo, image" +
            " FROM ARTICLES_VENDUS" +
            " INNER JOIN CATEGORIES ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie " +
            " INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur" +
            " WHERE ARTICLES_VENDUS.no_article = ?";*/

	private static final String SELECTARTICLE = "SELECT ARTICLES_VENDUS.no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, ARTICLES_VENDUS.no_utilisateur,  ARTICLES_VENDUS.no_categorie, CATEGORIES.libelle, UTILISATEURS.pseudo, image, ENCHERES.no_utilisateur AS no_utilisateurEncherisseur, u2.pseudo AS pseudoEncherisseur" +
         " FROM ARTICLES_VENDUS" +
         " LEFT JOIN CATEGORIES ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie" +
         " LEFT JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur" +
			" LEFT JOIN ENCHERES ON ARTICLES_VENDUS.no_article = ENCHERES.no_article" +
			" LEFT JOIN UTILISATEURS u2 ON ENCHERES.no_utilisateur = u2.no_utilisateur" +
         " WHERE ARTICLES_VENDUS.no_article = ?" +
         " ORDER BY montant_enchere DESC";
	
    private static final String SELECTRETRAIT = "SELECT no_article, rue, code_postal,ville FROM RETRAITS WHERE no_article = ?";
	
    private static final String UPDATEPRIXVENTE = "UPDATE ARTICLES_VENDUS SET prix_vente = ? WHERE no_article = ?";

    private static final String SELECTCATEGORIE = "SELECT ARTICLES_VENDUS.no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, ARTICLES_VENDUS.no_utilisateur, ARTICLES_VENDUS.no_categorie, CATEGORIES.libelle, UTILISATEURS.pseudo, image " +
            " FROM ARTICLES_VENDUS" +
            " INNER JOIN CATEGORIES ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie " +
            " INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur" +
            " LEFT JOIN ENCHERES ON ARTICLES_VENDUS.no_article = ENCHERES.no_article" +
            " WHERE CATEGORIES.libelle = ?";
    
    private static final String DELETE = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";
    
    //PAS OUF OUF
    private static final String DELETERETRAIT = "DELETE FROM RETRAITS WHERE no_article = ?";
    
    private static final String UPDATEARTICLE = "UPDATE ARTICLES_VENDUS SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, no_categorie = ?, image = ? WHERE no_article = ?";
    
    private static final String UPDATERETRAIT  = "UPDATE RETRAITS SET rue = ?, code_postal = ?, ville = ? WHERE no_article = ?";
    
    Timestamp currentDate = new Timestamp(System.currentTimeMillis());
    
    @Override
	public List<ArticleVendu> selectAllArticle(boolean [] filtreArticles, int numUtilisateur) {
		List<ArticleVendu> listArticle = new ArrayList<>();
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECTALL);
			ResultSet rs = pstmt.executeQuery();
			
			 EnchereManager enchereManager = new EnchereManager();
			 
			
			while (rs.next()) {
				ArticleVendu currentArticle = new ArticleVendu();
				currentArticle.setNoArticle(rs.getInt("no_article"));
				currentArticle.setNomArticle(rs.getString("nom_article"));
				currentArticle.setDescription(rs.getString("description"));
				currentArticle.setDateDebutEncheres(rs.getTimestamp("date_debut_encheres"));
				currentArticle.setDateFinEncheres(rs.getTimestamp("date_fin_encheres"));
				currentArticle.setMiseAPrix(rs.getInt("prix_initial"));
				currentArticle.setPrixVente(rs.getInt("prix_vente"));
				currentArticle.setNoUtilisateur(rs.getInt("no_utilisateur"));
				currentArticle.setNoCategorie(rs.getInt("no_categorie"));
				currentArticle.setLibelleCategorie(rs.getString("libelle"));
				currentArticle.setPseudoVendeur(rs.getString("pseudo"));
				currentArticle.setImage(rs.getString("image"));
				
				List<Enchere> listEncherisseur = enchereManager.recupListEncherisseurCroissant(currentArticle.getNoArticle());
				
				
				if (filtreArticles == null || (!filtreArticles[0] && !filtreArticles[1] && !filtreArticles[2] && !filtreArticles[3] && !filtreArticles[4] && !filtreArticles[5])) {
					listArticle.add(currentArticle);
				}
				
				else {
					
					if (filtreArticles[0] && currentArticle.getNoUtilisateur() == numUtilisateur && currentDate.after(currentArticle.getDateDebutEncheres()) && currentDate.before(currentArticle.getDateFinEncheres())) {
						listArticle.add(currentArticle);
					}
					if (filtreArticles[1] && currentArticle.getNoUtilisateur() == numUtilisateur && currentDate.before(currentArticle.getDateDebutEncheres())) {
						listArticle.add(currentArticle);
					}
					if (filtreArticles[2] && currentArticle.getNoUtilisateur() == numUtilisateur && currentDate.after(currentArticle.getDateFinEncheres())) {
						listArticle.add(currentArticle);
					}
					if (filtreArticles[3] && currentDate.after(currentArticle.getDateDebutEncheres()) && currentDate.before(currentArticle.getDateFinEncheres())) {
						listArticle.add(currentArticle);
					}
					if (filtreArticles[4] && currentDate.after(currentArticle.getDateDebutEncheres()) && currentDate.before(currentArticle.getDateFinEncheres())) {
						for (int i = 0; i <= listEncherisseur.size() - 1; i++) {
							Enchere uneEnchere = listEncherisseur.get(i);
							
							if (uneEnchere.getNoUtilisateur() == numUtilisateur) {
								listArticle.add(currentArticle);
							}
						}
					}
					if (filtreArticles[5] && currentDate.after(currentArticle.getDateFinEncheres()) && listEncherisseur.size() != 0) {
						
						Enchere EnchereGagnante = listEncherisseur.get(listEncherisseur.size() - 1);
						
						
												
						if (EnchereGagnante.getNoUtilisateur() == numUtilisateur) {
							listArticle.add(currentArticle);
						}
					}
				}
			}
			
		pstmt.close();
		rs.close();
			
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
			pstmt.setTimestamp(3, theArticle.getDateDebutEncheres());
			pstmt.setTimestamp(4, theArticle.getDateFinEncheres());
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
	
	
	@Override
    public ArticleVendu detailArticle(int no_article) throws SQLException {
        ArticleVendu currentArticle = new ArticleVendu();
        
        

        try (Connection cnx = ConnectionProvider.getConnection()){
            PreparedStatement pstmt = cnx.prepareStatement(SELECTARTICLE);
            pstmt.setInt(1, no_article);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
            currentArticle.setNoArticle(no_article);
            currentArticle.setNomArticle(rs.getString("nom_article"));
            currentArticle.setDescription(rs.getString("description"));
            currentArticle.setDateDebutEncheres(rs.getTimestamp("date_debut_encheres"));
            currentArticle.setDateFinEncheres(rs.getTimestamp("date_fin_encheres"));
            currentArticle.setMiseAPrix(rs.getInt("prix_initial"));
            currentArticle.setPrixVente(rs.getInt("prix_vente"));
            currentArticle.setNoUtilisateur(rs.getInt("no_utilisateur"));
            currentArticle.setNoCategorie(rs.getInt("no_categorie"));
            currentArticle.setLibelleCategorie(rs.getString("libelle"));
            currentArticle.setPseudoVendeur(rs.getString("pseudo"));
            if (rs.getString("image") == null) {
            	currentArticle.setImage("default.jpg");
			}else {
				currentArticle.setImage(rs.getString("image"));
			}
            currentArticle.setNoUtilisateurEncherisseur(rs.getInt("no_utilisateurEncherisseur"));
            currentArticle.setPseudoUtilisateurEncherisseur(rs.getString("pseudoEncherisseur"));
            }
            
        
                    
            pstmt.close();
            rs.close();
                
            } catch (SQLException e) {
            e.printStackTrace();
            }
        
        return currentArticle;
    }
	
	
	@Override
    public Retrait detailRetrait(int no_article) throws SQLException {
        Retrait currentRetrait = new Retrait();
    
            try (Connection cnx = ConnectionProvider.getConnection()){
            PreparedStatement pstmt = cnx.prepareStatement(SELECTRETRAIT);
            pstmt.setInt(1, no_article);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
            currentRetrait.setNoArticle(no_article);
            currentRetrait.setRue(rs.getString("rue"));
            currentRetrait.setCodePostal(rs.getInt("code_postal"));
            currentRetrait.setVille(rs.getString("ville"));
            }
            else {
                System.out.println("Pas de retrait trouvé");
            }
        
                    
            pstmt.close();
            rs.close();
                
            } catch (SQLException e) {
            e.printStackTrace();
            }
        
        return currentRetrait;
        
    }

	@Override
	public void updatePrixVente(int noUtilisateur, int prixVente) throws SQLException {
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement pstmt = cnx.prepareStatement(UPDATEPRIXVENTE);
			
			pstmt.setInt(2, noUtilisateur);
			pstmt.setInt(1, prixVente);
			
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} 
	
	
	@Override
    public List<ArticleVendu> selectCategorie(String libelle, boolean [] filtreArticles, int numUtilisateur) {
        List<ArticleVendu> listArticle = new ArrayList<>();
        
        try (Connection cnx = ConnectionProvider.getConnection()){
            PreparedStatement pstmt = cnx.prepareStatement(SELECTCATEGORIE);
            pstmt.setString(1, libelle);
            ResultSet rs = pstmt.executeQuery();
            
            EnchereManager enchereManager = new EnchereManager();
            
            while (rs.next()) {
                ArticleVendu currentArticle = new ArticleVendu();
                currentArticle.setNoArticle(rs.getInt("no_article"));
                currentArticle.setNomArticle(rs.getString("nom_article"));
                currentArticle.setDescription(rs.getString("description"));
                currentArticle.setDateDebutEncheres(rs.getTimestamp("date_debut_encheres"));
                currentArticle.setDateFinEncheres(rs.getTimestamp("date_fin_encheres"));
                currentArticle.setMiseAPrix(rs.getInt("prix_initial"));
                currentArticle.setPrixVente(rs.getInt("prix_vente"));
                currentArticle.setNoUtilisateur(rs.getInt("no_utilisateur"));
                currentArticle.setNoCategorie(rs.getInt("no_categorie"));
                currentArticle.setLibelleCategorie(rs.getString("libelle"));
                currentArticle.setPseudoVendeur(rs.getString("pseudo"));
                currentArticle.setImage(rs.getString("image"));
                
                List<Enchere> listEncherisseur = enchereManager.recupListEncherisseurCroissant(currentArticle.getNoArticle());
                
                
                if (filtreArticles == null || (!filtreArticles[0] && !filtreArticles[1] && !filtreArticles[2] && !filtreArticles[3] && !filtreArticles[4] && !filtreArticles[5])) {
					listArticle.add(currentArticle);
				}
				
				else {
					
					if (filtreArticles[0] && currentArticle.getNoUtilisateur() == numUtilisateur && currentDate.after(currentArticle.getDateDebutEncheres()) && currentDate.before(currentArticle.getDateFinEncheres())) {
						listArticle.add(currentArticle);
					}
					if (filtreArticles[1] && currentArticle.getNoUtilisateur() == numUtilisateur && currentDate.before(currentArticle.getDateDebutEncheres())) {
						listArticle.add(currentArticle);
					}
					if (filtreArticles[2] && currentArticle.getNoUtilisateur() == numUtilisateur && currentDate.after(currentArticle.getDateFinEncheres())) {
						listArticle.add(currentArticle);
					}
					if (filtreArticles[3] && currentDate.after(currentArticle.getDateDebutEncheres()) && currentDate.before(currentArticle.getDateFinEncheres())) {
						listArticle.add(currentArticle);
					}
					if (filtreArticles[4] && currentDate.after(currentArticle.getDateDebutEncheres()) && currentDate.before(currentArticle.getDateFinEncheres())) {
						for (int i = 0; i <= listEncherisseur.size() - 1; i++) {
							Enchere uneEnchere = listEncherisseur.get(i);
							
							if (uneEnchere.getNoUtilisateur() == numUtilisateur) {
								listArticle.add(currentArticle);
							}
						}
					}
					if (filtreArticles[5] && currentDate.after(currentArticle.getDateFinEncheres()) && listEncherisseur.size() != 0) {
						
						Enchere EnchereGagnante = listEncherisseur.get(listEncherisseur.size() - 1);
						
						
						
						
						if (EnchereGagnante.getNoUtilisateur() == numUtilisateur) {
							listArticle.add(currentArticle);
						}
					}
				}
            }
            
            pstmt.close();
            rs.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return listArticle;
    }
	
	
	@Override
	public List<ArticleVendu> selectRecherche(String recherche, boolean [] filtreArticles, int numUtilisateur) {
		List<ArticleVendu> listArticle = new ArrayList<>();
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECTALL);
			ResultSet rs = pstmt.executeQuery();
			
			EnchereManager enchereManager = new EnchereManager();
			
			while (rs.next()) {
				ArticleVendu currentArticle = new ArticleVendu();
				currentArticle.setNoArticle(rs.getInt("no_article"));
				currentArticle.setNomArticle(rs.getString("nom_article"));
				currentArticle.setDescription(rs.getString("description"));
				currentArticle.setDateDebutEncheres(rs.getTimestamp("date_debut_encheres"));
				currentArticle.setDateFinEncheres(rs.getTimestamp("date_fin_encheres"));
				currentArticle.setMiseAPrix(rs.getInt("prix_initial"));
				currentArticle.setPrixVente(rs.getInt("prix_vente"));
				currentArticle.setNoUtilisateur(rs.getInt("no_utilisateur"));
				currentArticle.setNoCategorie(rs.getInt("no_categorie"));
				currentArticle.setLibelleCategorie(rs.getString("libelle"));
				currentArticle.setPseudoVendeur(rs.getString("pseudo"));
				currentArticle.setImage(rs.getString("image"));
				
				List<Enchere> listEncherisseur = enchereManager.recupListEncherisseurCroissant(currentArticle.getNoArticle());
				
				boolean verifRecherche1 = currentArticle.getNomArticle().contains(recherche);
				boolean verifRecherche2 = currentArticle.getDescription().contains(recherche);
				
				if(verifRecherche1 || verifRecherche2) {
					if (filtreArticles == null || (!filtreArticles[0] && !filtreArticles[1] && !filtreArticles[2] && !filtreArticles[3] && !filtreArticles[4] && !filtreArticles[5])) {
						listArticle.add(currentArticle);
					}
					
					else {
						
						if (filtreArticles[0] && currentArticle.getNoUtilisateur() == numUtilisateur && currentDate.after(currentArticle.getDateDebutEncheres()) && currentDate.before(currentArticle.getDateFinEncheres())) {
							listArticle.add(currentArticle);
						}
						if (filtreArticles[1] && currentArticle.getNoUtilisateur() == numUtilisateur && currentDate.before(currentArticle.getDateDebutEncheres())) {
							listArticle.add(currentArticle);
						}
						if (filtreArticles[2] && currentArticle.getNoUtilisateur() == numUtilisateur && currentDate.after(currentArticle.getDateFinEncheres())) {
							listArticle.add(currentArticle);
						}
						if (filtreArticles[3] && currentDate.after(currentArticle.getDateDebutEncheres()) && currentDate.before(currentArticle.getDateFinEncheres())) {
							listArticle.add(currentArticle);
						}
						if (filtreArticles[4] && currentDate.after(currentArticle.getDateDebutEncheres()) && currentDate.before(currentArticle.getDateFinEncheres())) {
							for (int i = 0; i <= listEncherisseur.size() - 1; i++) {
								Enchere uneEnchere = listEncherisseur.get(i);
								
								if (uneEnchere.getNoUtilisateur() == numUtilisateur) {
									listArticle.add(currentArticle);
								}
							}
						}
						if (filtreArticles[5] && currentDate.after(currentArticle.getDateFinEncheres()) && listEncherisseur.size() != 0) {
							
							Enchere EnchereGagnante = listEncherisseur.get(listEncherisseur.size() - 1);
							
							
							
							
							if (EnchereGagnante.getNoUtilisateur() == numUtilisateur) {
								listArticle.add(currentArticle);
							}
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return listArticle;
	}
	
	@Override
	public List<ArticleVendu> selectRechercheEtCategorie(String recherche, String categorie, boolean [] filtreArticles, int numUtilisateur) {
		List<ArticleVendu> listArticle = new ArrayList<>();
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECTCATEGORIE);
			 pstmt.setString(1, categorie);
			ResultSet rs = pstmt.executeQuery();
			
			EnchereManager enchereManager = new EnchereManager();
			
			while (rs.next()) {
				ArticleVendu currentArticle = new ArticleVendu();
				currentArticle.setNoArticle(rs.getInt("no_article"));
				currentArticle.setNomArticle(rs.getString("nom_article"));
				currentArticle.setDescription(rs.getString("description"));
				currentArticle.setDateDebutEncheres(rs.getTimestamp("date_debut_encheres"));
				currentArticle.setDateFinEncheres(rs.getTimestamp("date_fin_encheres"));
				currentArticle.setMiseAPrix(rs.getInt("prix_initial"));
				currentArticle.setPrixVente(rs.getInt("prix_vente"));
				currentArticle.setNoUtilisateur(rs.getInt("no_utilisateur"));
				currentArticle.setNoCategorie(rs.getInt("no_categorie"));
				currentArticle.setLibelleCategorie(rs.getString("libelle"));
				currentArticle.setPseudoVendeur(rs.getString("pseudo"));
				currentArticle.setImage(rs.getString("image"));
				
				List<Enchere> listEncherisseur = enchereManager.recupListEncherisseurCroissant(currentArticle.getNoArticle());
				
				boolean verifRecherche1 = currentArticle.getNomArticle().contains(recherche);
				boolean verifRecherche2 = currentArticle.getDescription().contains(recherche);
				
				if(verifRecherche1 || verifRecherche2) {
					if (filtreArticles == null || (!filtreArticles[0] && !filtreArticles[1] && !filtreArticles[2] && !filtreArticles[3] && !filtreArticles[4] && !filtreArticles[5])) {
						listArticle.add(currentArticle);
					}
					
					else {
						
						if (filtreArticles[0] && currentArticle.getNoUtilisateur() == numUtilisateur && currentDate.after(currentArticle.getDateDebutEncheres()) && currentDate.before(currentArticle.getDateFinEncheres())) {
							listArticle.add(currentArticle);
						}
						if (filtreArticles[1] && currentArticle.getNoUtilisateur() == numUtilisateur && currentDate.before(currentArticle.getDateDebutEncheres())) {
							listArticle.add(currentArticle);
						}
						if (filtreArticles[2] && currentArticle.getNoUtilisateur() == numUtilisateur && currentDate.after(currentArticle.getDateFinEncheres())) {
							listArticle.add(currentArticle);
						}
						if (filtreArticles[3] && currentDate.after(currentArticle.getDateDebutEncheres()) && currentDate.before(currentArticle.getDateFinEncheres())) {
							listArticle.add(currentArticle);
						}
						if (filtreArticles[4] && currentDate.after(currentArticle.getDateDebutEncheres()) && currentDate.before(currentArticle.getDateFinEncheres())) {
							for (int i = 0; i <= listEncherisseur.size() - 1; i++) {
								Enchere uneEnchere = listEncherisseur.get(i);
								
								if (uneEnchere.getNoUtilisateur() == numUtilisateur) {
									listArticle.add(currentArticle);
								}
							}
						}
						if (filtreArticles[5] && currentDate.after(currentArticle.getDateFinEncheres()) && listEncherisseur.size() != 0) {
							
							Enchere EnchereGagnante = listEncherisseur.get(listEncherisseur.size() - 1);
							
							
							
							
							if (EnchereGagnante.getNoUtilisateur() == numUtilisateur) {
								listArticle.add(currentArticle);
							}
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return listArticle;
	}

	@Override
	public void deleteArticle(int noArticle) {
		
		deleteRetrait(noArticle);
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement pstmt = cnx.prepareStatement(DELETE);
			
			pstmt.setInt(1, noArticle);
			
			pstmt.executeUpdate();
			pstmt.close();
			
			System.out.println("Article supprimé");

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//BEURK MAIS FLEMME
	public void deleteRetrait(int noArticle) {
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement pstmt = cnx.prepareStatement(DELETERETRAIT);
			
			pstmt.setInt(1, noArticle);
			
			pstmt.executeUpdate();
			pstmt.close();
			
			
			System.out.println("Retrait supprimé");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void modifierArticle(ArticleVendu theArticle, Retrait currentRetrait) {
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement pstmt = cnx.prepareStatement(UPDATEARTICLE);
	
			pstmt.setString(1, theArticle.getNomArticle());
			pstmt.setString(2, theArticle.getDescription());
			pstmt.setTimestamp(3, theArticle.getDateDebutEncheres());
			pstmt.setTimestamp(4, theArticle.getDateFinEncheres());
			pstmt.setInt(5, theArticle.getMiseAPrix());
			pstmt.setInt(6, theArticle.getNoCategorie());
			pstmt.setString(7, theArticle.getImage());
			pstmt.setInt(8, theArticle.getNoArticle());
			
			modifierRetrait(currentRetrait);
			
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	public void modifierRetrait(Retrait currentRetrait) {
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement pstmt = cnx.prepareStatement(UPDATERETRAIT);
	
			pstmt.setString(1, currentRetrait.getRue());
			pstmt.setInt(2, currentRetrait.getCodePostal());
			pstmt.setString(3, currentRetrait.getVille());
			pstmt.setInt(4, currentRetrait.getNoArticle());
			
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
