package fr.eni.encheres.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class SupprimerArticle
 */
@WebServlet("/supprimer_vente")
public class SupprimerArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
    HttpSession session = request.getSession(false);
    	
    if (session == null) {
    	response.sendRedirect("/Encheres/");
    } else {
    	Utilisateur sessionUtilisateur = (Utilisateur) request.getSession(false).getAttribute("utilisateur");

        if (sessionUtilisateur == null) {
            response.sendRedirect("/Encheres/");
        } else {

        	int paramNoArticle = Integer.parseInt(request.getParameter( "noArticle" ));
        	ArticleVendu detailArticle = new ArticleVendu();
            Retrait detailRetrait = new Retrait();
            ArticleManager articleManager = new ArticleManager();
        		
            detailArticle = articleManager.afficherDetail(paramNoArticle);
            detailRetrait = articleManager.afficherRetrait(paramNoArticle);
            
            
            request.setAttribute("ArticleVendu", detailArticle);
            request.setAttribute("Retrait", detailRetrait);
            
        		
            this.getServletContext().getRequestDispatcher("/WEB-INF/supprimer_vente.jsp").forward(request, response);

	    	}
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int paramNoArticle = Integer.parseInt(request.getParameter("noArticle"));
		ArticleVendu detailArticle = new ArticleVendu();
	    ArticleManager articleManager = new ArticleManager();
			
	    detailArticle = articleManager.afficherDetail(paramNoArticle);
		
		System.out.println(detailArticle.getNoArticle());
		
		
		articleManager.supprimer(detailArticle.getNoArticle());
		
		response.sendRedirect("/Encheres/");
		
		doGet(request, response);
	}

}
