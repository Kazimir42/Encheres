package fr.eni.encheres.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
	}

	/**+
	 * @param error 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pseudo = request.getParameter("pseudo");
		String mdp = request.getParameter("mdp");
		UtilisateurManager utilisateurManager = new UtilisateurManager();
        
		Utilisateur currentUtilisateur = utilisateurManager.selectionner(pseudo, mdp);
		
		System.out.println(currentUtilisateur.toString());
		
		HttpSession session = request.getSession();
		
		/*
        request.setAttribute("pseudo", currentUtilisateur.getPseudo());
        request.setAttribute("mdp", currentUtilisateur.getMotDePasse());
        */
		
		session.setAttribute("utilisateur", currentUtilisateur);
        
		
		if (currentUtilisateur.getNoUtilisateur() > 0) {
			response.sendRedirect("/Encheres/");  

	        //this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
		}else {
			boolean error = true;
			request.setAttribute("error", error);
	        this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
		}
			
	}

}
