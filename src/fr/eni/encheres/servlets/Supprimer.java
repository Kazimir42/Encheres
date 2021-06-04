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
 * Servlet implementation class Supprimer
 */
@WebServlet("/supprimer")
public class Supprimer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/supprimer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Utilisateur currentUtilisateur = new Utilisateur();
		Utilisateur sessionUtilisateur = (Utilisateur) request.getSession(false).getAttribute("utilisateur");
		
		currentUtilisateur.setNoUtilisateur(sessionUtilisateur.getNoUtilisateur());
		
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		Utilisateur theUser = utilisateurManager.supprimer(currentUtilisateur);
		
		if (theUser.getError().isBlank()) {
			HttpSession session = request.getSession();
			session.setAttribute("utilisateur", theUser);
			session.invalidate();
			response.sendRedirect("/Encheres/");
			
		}else {
			request.setAttribute("error", theUser.getError());
		    this.getServletContext().getRequestDispatcher("/WEB-INF/supprimer.jsp").forward(request, response);
		}

	}

}