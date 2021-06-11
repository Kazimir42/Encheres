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
 * Servlet implementation class AutreProfil
 */
@WebServlet("/autre_profil")
public class AutreProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutreProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

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
            	Utilisateur detailUtilisateur = new Utilisateur();
            	String paramPseudoUtilisateur = request.getParameter("pseudo");
            	UtilisateurManager utilisateurManager = new UtilisateurManager();
            	
            	detailUtilisateur = utilisateurManager.selectionnerParPseudo(paramPseudoUtilisateur);
            	
            	 request.setAttribute("autreUtilisateur", detailUtilisateur);
            	
            	this.getServletContext().getRequestDispatcher("/WEB-INF/autre_utilisateur.jsp").forward(request, response);
            }
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}