package fr.eni.encheres.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

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
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class Detail
 */
@WebServlet("/detail")
public class Detail extends HttpServlet {
    private static final long serialVersionUID = 1L;
       

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    	HttpSession session = request.getSession(false);
    	
    	if (session == null) {
    		response.sendRedirect("/Encheres/");
        } else {
            Utilisateur sessionUtilisateur = (Utilisateur) request.getSession(false).getAttribute("utilisateur");

            if (sessionUtilisateur == null) {
            	response.sendRedirect("/Encheres/");
            } else {

                ArticleVendu detailArticle = new ArticleVendu();
    	        Retrait detailRetrait = new Retrait();
    	        int paramNoArticle= Integer.parseInt(request.getParameter( "noArticle" ));
    	        ArticleManager articleManager = new ArticleManager();
    	    	Timestamp currentDate = new Timestamp(System.currentTimeMillis());

    	        
    	        detailArticle = articleManager.afficherDetail(paramNoArticle);
    	        detailRetrait = articleManager.afficherRetrait(paramNoArticle);
    	        
    	        
    	                
    	    	if (currentDate.before(detailArticle.getDateDebutEncheres())) {//SI ENCHERE PAS COMMENCE ALORS = 1
    				System.out.println("l'enchere na pas debute");
    				request.setAttribute("enCours", 1);
    			}else if(currentDate.after(detailArticle.getDateFinEncheres())) { //SI ENCHERE TERMINE ALORS = 2
    				System.out.println("l'enchere est terminé");
    				request.setAttribute("enCours", 2);
    			}else {
    				System.out.println("l'enchere est en cours"); //SI ENCHERE EN COURS ALORS = 3
    				request.setAttribute("enCours", 3);
    			}
    	        
    	        request.setAttribute("ArticleVendu", detailArticle);
    	        request.setAttribute("Retrait", detailRetrait);
    	        
    	        this.getServletContext().getRequestDispatcher("/WEB-INF/detail.jsp").forward(request, response);
            }
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Utilisateur sessionUtilisateur = (Utilisateur) request.getSession(false).getAttribute("utilisateur");
    	UtilisateurManager utilisateurManager = new UtilisateurManager();
    	EnchereManager enchereManager = new EnchereManager();
    	String resultat = "";

    	
    	
    	int leNoUtilisateur = sessionUtilisateur.getNoUtilisateur();
    	int lePrixPropose = Integer.parseInt(request.getParameter("prixPropose"));
    	int paramNoArticle = Integer.parseInt(request.getParameter( "noArticle" ));
    	Timestamp date = new Timestamp(System.currentTimeMillis());

    	
    	//NUL A MODIFIER
        ArticleManager articleManager = new ArticleManager();
        ArticleVendu detailArticle = new ArticleVendu();
        detailArticle = articleManager.afficherDetail(paramNoArticle);
    	//
    	
    	

        
    	if (detailArticle.getPrixVente() < lePrixPropose && detailArticle.getMiseAPrix() <= lePrixPropose) {// si le prix entré est > au prix en cours
    		
    		    		
    		if (sessionUtilisateur.getNoUtilisateur() == detailArticle.getNoUtilisateurEncherisseur()) { //si l'user connecté a le meme ID que celui qui a fait la derniere offre

            	resultat = "<p class=\"incorrect\">Vous avez déja la meilleur offre</p>";
            	
            	
			}else { //sinon le numero de l'user connecté est diffenret du precedent encherisseur
				
    			if (sessionUtilisateur.getCredit() >=  lePrixPropose) {// on check si il a assez de coin
					
        			Enchere currentEnchere = new Enchere(leNoUtilisateur, paramNoArticle, date, lePrixPropose);
                	
        			//ON REGARDE SI IL A DEJA FAIT UNE ENCHERE ET ON RECUP COMBIEN
    				int creditDejaEncherie = enchereManager.recupCreditEncheri(leNoUtilisateur, paramNoArticle);
    				
    				//ON AJOUTE LENCHERE
        			enchereManager.ajouter(currentEnchere); 
                	
                	try { 
                		
        				//ON UPDATE LE PRIX DE LARTICLE
        				articleManager.actualisePrixVente(paramNoArticle, lePrixPropose);
        				
        				
        				//PUIS ON ENLEVE LES CREDIT DU PORTEFEUILLE DE L'USER
        				sessionUtilisateur.setCredit(sessionUtilisateur.getCredit() - (lePrixPropose - creditDejaEncherie));
        				utilisateurManager.updateCredit(sessionUtilisateur.getNoUtilisateur(), sessionUtilisateur.getCredit());
        			} catch (SQLException e) {
        				e.printStackTrace();
        			} 
                	
                	resultat = "<p class=\"info\">enchere bien ajouté !</p>";
    				
				}else {
		    		resultat = "<p class=\"incorrect\">Désolé, vous n'avez pas assez de COIN</p>";
				}

			}
    		
        	
        	
		}else {
    		resultat = "<p class=\"incorrect\">Votre prix est inférieur à la meilleur l'actuel meilleur offre ou à la mise a prix du vendeur</p>";
		}
    	
    	


    	request.setAttribute("resultat", resultat);
    	
    	
    	//enchereManager.ajouter(currentEnchere);
    	
        doGet(request, response);
    }

}