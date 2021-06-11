package fr.eni.encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class PanelAdmin
 */
@WebServlet("/panel_admin")
public class PanelAdmin extends HttpServlet {
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
            	if (sessionUtilisateur.getAdministrateur() == 1) {
            		
            		UtilisateurManager utilisateurManager = new UtilisateurManager();
            		List<Utilisateur> listUtilisateur = new ArrayList<Utilisateur>();
            		
            		
            		listUtilisateur = utilisateurManager.selectionnerTous();
            		
            		
            		request.setAttribute("listUtilisateur", listUtilisateur);
            		
            		
            		
            		this.getServletContext().getRequestDispatcher("/WEB-INF/panel_admin.jsp").forward(request, response);
				}else {
	            	response.sendRedirect("/Encheres/");
				}
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
