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
 * Servlet implementation class Perdu
 */
@WebServlet("/perdu")
public class Perdu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/perdu.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pseudo = request.getParameter("pseudo");
		String question1 = request.getParameter("question1");
		String question2 = request.getParameter("question2");
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		
		Utilisateur currentUtilisateur = utilisateurManager.retrouver(pseudo, question1, question2);
		HttpSession session = request.getSession(true);
		
		session.setAttribute("utilisateur", currentUtilisateur);
        
		
		if (currentUtilisateur.getNoUtilisateur() > 0) {
			response.sendRedirect("/Encheres/profil");  

		}else {
			boolean error = true;
			request.setAttribute("error", error);
	        this.getServletContext().getRequestDispatcher("/WEB-INF/perdu.jsp").forward(request, response);
		}

	}

}