package fr.eni.encheres.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class Index
 */
@WebServlet("")
public class Index extends HttpServlet {
    private static final long serialVersionUID = 1L;
       

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	Timestamp currentDate = new Timestamp(System.currentTimeMillis());
    	
        ArticleManager articleManager = new ArticleManager();
        
        List<ArticleVendu> listArticle = new ArrayList<>();
        String categorie = request.getParameter("categorie");
        String recherche = request.getParameter("recherche");
        boolean enchereOuverte = request.getParameter("enchereOuverte") != null;
        boolean enchereEnCours = request.getParameter("enchereEnCours") != null;
        boolean enchereRemporte = request.getParameter("enchereRemporte") != null;
        boolean venteEnCours = request.getParameter("venteEnCours") != null;
        boolean venteNonDebute = request.getParameter("venteNonDebute") != null;
        boolean venteTermine = request.getParameter("venteTermine") != null;
        boolean[] filtreArticles = {venteEnCours, venteNonDebute, venteTermine, enchereOuverte, enchereEnCours, enchereRemporte};
        
     
        int numUtilisateur;
        
        HttpSession session = request.getSession(false);
        
        if (session == null) {
        	numUtilisateur = 0;
        } else {
        
        	Utilisateur sessionUtilisateur = (Utilisateur) request.getSession(false).getAttribute("utilisateur");
        
        	if (sessionUtilisateur == null) {
        		numUtilisateur = 0;
        	} else {
        		numUtilisateur = sessionUtilisateur.getNoUtilisateur();
        	}
        }
     
    
        if(categorie == null || categorie.equals("toutes")) {
            if(recherche == null || recherche.isBlank()) {
                listArticle = articleManager.afficherTous(filtreArticles, numUtilisateur);
            }
            else {
                listArticle = articleManager.afficherParRecherche(recherche, filtreArticles, numUtilisateur);
            }
        }
        else {
            if(recherche == null || recherche.isBlank()) {
                listArticle = articleManager.afficherParCategorie(categorie, filtreArticles, numUtilisateur);
            }
            else {
                listArticle = articleManager.afficherParRechercheEtCategorie(recherche, categorie, filtreArticles, numUtilisateur);
            }
        }
        Collections.reverse(listArticle);
        request.setAttribute("listProduit", listArticle);
        
        //REMI
        EnchereManager enchereManager = new EnchereManager();
        UtilisateurManager utilisateurManager = new UtilisateurManager();
        for (ArticleVendu articleVendu : listArticle) {// on parcour les articles
        	
        	if (currentDate.after(articleVendu.getDateFinEncheres())) { // seuleemnt ceux dont les encheres sont terminé
        		
        		//recup de tout les enchérisseurs pour le numéro d'article
        		List<Enchere> listEncherriseur = enchereManager.recupListEncherisseurCroissant(articleVendu.getNoArticle());


        		for (int i = 0; i <= listEncherriseur.size() - 1; i++) {
        			
        			Enchere uneEnchere2 = listEncherriseur.get(i);

        			if(i == listEncherriseur.size() - 1) {
                		int creditVendeur = utilisateurManager.recupererCreditByNo(articleVendu.getNoUtilisateur());
        				utilisateurManager.updateCredit(articleVendu.getNoUtilisateur(), (creditVendeur + uneEnchere2.getMontantEnchere()));
        				
    					enchereManager.montantAZero(uneEnchere2.getNoUtilisateur(), articleVendu.getNoArticle());

        			}else {

    					int creditAcheteurLoser = utilisateurManager.recupererCreditByNo(uneEnchere2.getNoUtilisateur());
    					
    					utilisateurManager.updateCredit(uneEnchere2.getNoUtilisateur(), (creditAcheteurLoser + uneEnchere2.getMontantEnchere()));
    					    					
    					enchereManager.montantAZero(uneEnchere2.getNoUtilisateur(), articleVendu.getNoArticle());
    					
        			}

        		}
        		

        		
			}
		}
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
    }

}