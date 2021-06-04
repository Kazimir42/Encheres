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
 * Servlet implementation class Modifier
 */
@WebServlet("/modifier")
public class Modifier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modifier() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/modifier.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Utilisateur currentUtilisateur = new Utilisateur();
		Utilisateur sessionUtilisateur = (Utilisateur) request.getSession(false).getAttribute("utilisateur");
		
		
		String lePassword = "";
		String lePassword1 = request.getParameter("mdp");
		String lePassword2 = request.getParameter("confirmation");
		String lePseudo = "";
		String leNom = "";
		String lePrenom = "";
		String leEmail = "";
		String laRue = "";
		String laVille = "";
		
		
		int leTel = 0;
		int leCodePostal = 0;
		
		//CHECK TELEPHONE PAS VIDE
		if (!request.getParameter("telephone").isBlank()) {
			leTel = Integer.parseInt(request.getParameter("telephone"));
		}else {
			currentUtilisateur.setError("erreur, telephone incorrect");
		}
		
		//CHECK CODE POSTAL PAS VIDE
		if (!request.getParameter("codePostal").isBlank()) {
			leCodePostal = Integer.parseInt(request.getParameter("codePostal"));
		}else {
			currentUtilisateur.setError("erreur, code postal vide");
		}
		
		//CHECK PASSWORD EQUALS
		if (lePassword1.equals(lePassword2)) {
			lePassword = lePassword1;
		}else {
			currentUtilisateur.setError("erreur, mot de passe non identique");
		}
		
		//CHECK PSEUDO REMPLI
		if (!request.getParameter("pseudo").isBlank()) {
			lePseudo = request.getParameter("pseudo");

		}else {
			currentUtilisateur.setError("erreur, pas de pseudo entré");
		}
		
		//CHECK NOM REMPLI
		if (!request.getParameter("nom").isBlank()) {
			leNom = request.getParameter("nom");
		}else {
			currentUtilisateur.setError("erreur, pas de nom entré");
		}
		
		//CHECK PRENOM REMPLI
		if (!request.getParameter("prenom").isBlank()) {
			lePrenom = request.getParameter("prenom");
		}else {
			currentUtilisateur.setError("erreur, pas de nom entré");
		}
		
		//CHECK EMAIL REMPLI
		if (!request.getParameter("email").isBlank()) {
			leEmail = request.getParameter("email");
		}else {
			currentUtilisateur.setError("erreur, pas d'email entré");
		}
		
		//CHECK RUE REMPLI
		if (!request.getParameter("rue").isBlank()) {
			laRue = request.getParameter("rue");
		}else {
			currentUtilisateur.setError("erreur, pas de rue entré");
		}
		
		//CHECK VILLE REMPLI
		if (!request.getParameter("ville").isBlank()) {
			laVille = request.getParameter("ville");
		}else {
			currentUtilisateur.setError("erreur, pas de ville entré");
		}
		
		if(!lePseudo.equals(currentUtilisateur.getPseudo())) {
		currentUtilisateur.setPseudo(lePseudo);
		}
		currentUtilisateur.setNom(leNom);
		currentUtilisateur.setPrenom(lePrenom);
		currentUtilisateur.setEmail(leEmail);
		currentUtilisateur.setTelephone(leTel);
		currentUtilisateur.setRue(laRue);
		currentUtilisateur.setCodePostal(leCodePostal);
		currentUtilisateur.setVille(laVille);
		currentUtilisateur.setMotDePasse(lePassword);
		currentUtilisateur.setCredit(sessionUtilisateur.getCredit());

		currentUtilisateur.setNoUtilisateur(sessionUtilisateur.getNoUtilisateur());
		

					
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		Utilisateur theUser = utilisateurManager.modifier(currentUtilisateur);
			
		if (theUser.getError().isBlank()) {
			HttpSession session = request.getSession();
			session.setAttribute("utilisateur", theUser);

			response.sendRedirect("/Encheres/profil");
			
			System.out.println(theUser.toString());
		}else {
			request.setAttribute("error", theUser.getError());
		    this.getServletContext().getRequestDispatcher("/WEB-INF/modifier.jsp").forward(request, response);
		}

	}
}
