package fr.eni.encheres.servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.sql.Date;
import java.util.Calendar;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class NouvelleVente
 */
@WebServlet("/nouvelle_vente")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class NouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final int TAILLE_TAMPON = 10240;
    public static final String CHEMIN_FICHIERS = "/Java/workspace/Encheres/WebContent/img/";


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        this.getServletContext().getRequestDispatcher("/WEB-INF/nouvelle_vente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleVendu currentArticle = new ArticleVendu();
		Retrait currentRetrait = new Retrait();
		Utilisateur sessionUtilisateur = (Utilisateur) request.getSession(false).getAttribute("utilisateur");
		String theError = "";
		
		String nomArticle = "";
		String descArticle = "";
		int catNoArticle = 0;
		int prixArticle = 0;
		Date dateDebutArticle = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		Date dateFinArticle = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		String rueRetrait = "";
		int codePostalRetrait = 0;
		String villeRetrait = "";
		
		// CHECK NOM
		if (!request.getParameter("nomArticle").isBlank()) {
			nomArticle = request.getParameter("nomArticle");
		}else {
			theError = "Erreur, pas de nom";
		}
		
		//CHECK DESC
		if (!request.getParameter("descArticle").isBlank()) {
			 descArticle = request.getParameter("descArticle");
		}else {
			theError = "Erreur, pas de desc";
		}
		
		//CHECK CATEGORIE
		if (!request.getParameter("catArticle").isBlank()) {
			String catArticle = request.getParameter("catArticle"); //A CONVERTIR
			
			switch (catArticle) {
			case "Informatique":
				catNoArticle = 1;
				break;
			case "Ameublement":
				catNoArticle = 2;
				break;		
			case "Vetement":
				catNoArticle = 3;
				break;		
			case "Sport&Loisirs":
				catNoArticle = 4;
				break;
			default:
				break;
			}

		}else {
			theError = "Erreur, pas de categorie";
		}
		
		//CHECK FICHIER
		Part part = request.getPart("fichier");
				
        // On vérifie qu'on a bien reçu un fichier
        String nomFichier = getNomFichier(part);
                
        if (nomFichier.isBlank()) {
        	nomFichier = null;
		}else {
	        String extension = nomFichier.substring(nomFichier.lastIndexOf(".") + 1);

	        nomFichier = UUID.randomUUID().toString();
	        
	        nomFichier = nomFichier + "." + extension;

	        // Si on a bien un fichier
	        if (nomFichier != null && !nomFichier.isEmpty()) {
	            String nomChamp = part.getName();
	            // On écrit définitivement le fichier sur le disque
	            ecrireFichier(part, nomFichier, CHEMIN_FICHIERS);

	            request.setAttribute(nomChamp, nomFichier);
	        }
		}
        
        

				
		
        //CHECK PRIX
		if (!request.getParameter("prixArticle").isBlank()) {
			prixArticle = Integer.parseInt(request.getParameter("prixArticle"));
		}else {
			theError = "Erreur, pas de prix";
		}
		
		//CHECK DATE DEBUT
		if (!request.getParameter("dateDebutArticle").isBlank()) {
			dateDebutArticle = Date.valueOf(request.getParameter("dateDebutArticle"));
		}else {
			theError = "Erreur, pas de date de debut";
		}
		
		//CHECK DATE FIN
		if (!request.getParameter("dateFinArticle").isBlank()) {
			dateFinArticle = Date.valueOf(request.getParameter("dateFinArticle"));
		}else {
			theError = "Erreur, pas de date de fin";
		}
		
		
		//CHECK RUE RETRAIT
		if (!request.getParameter("rueRetrait").isBlank()) {
			rueRetrait = request.getParameter("descArticle");
		}else {
			theError = "Erreur, pas de rue de retrait";
		}
		
        //CHECK POSTAL RETRAIT
		if (!request.getParameter("codePostalRetrait").isBlank()) {
			codePostalRetrait = Integer.parseInt(request.getParameter("codePostalRetrait"));
		}else {
			theError = "Erreur, pas de code postal de retrait";
		}
		
        //CHECK VILLE RETRAIT
		if (!request.getParameter("villeRetrait").isBlank()) {
			villeRetrait = request.getParameter("villeRetrait");
		}else {
			theError = "Erreur, pas de code postal de retrait";
		}
		
		//SI Y A AUCUNE ERREUR
		if (theError.isBlank()) {
			
			currentArticle.setNomArticle(nomArticle);
			currentArticle.setDescription(descArticle);
			currentArticle.setNoCategorie(catNoArticle);
			currentArticle.setImage(nomFichier);
			currentArticle.setMiseAPrix(prixArticle);
			currentArticle.setDateDebutEncheres(dateDebutArticle);
			currentArticle.setDateFinEncheres(dateFinArticle);
			
			currentRetrait.setRue(rueRetrait);
			currentRetrait.setCodePostal(codePostalRetrait);
			currentRetrait.setVille(villeRetrait);
			
			ArticleManager articleManager = new ArticleManager();
					
			articleManager.ajoutArticle(currentArticle, sessionUtilisateur.getNoUtilisateur(), currentRetrait);
			
			response.sendRedirect("/Encheres/");
			
			
		}else {
			request.setAttribute("theError", theError);
	        this.getServletContext().getRequestDispatcher("/WEB-INF/nouvelle_vente.jsp").forward(request, response);
		}
				

		
		}
	
	private void ecrireFichier( Part part, String nomFichier, String chemin ) throws IOException {
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
            sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMPON);

            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur;
            while ((longueur = entree.read(tampon)) > 0) {
                sortie.write(tampon, 0, longueur);
            }
        } finally {
            try {
                sortie.close();
            } catch (IOException ignore) {
            }
            try {
                entree.close();
            } catch (IOException ignore) {
            }
        }
    }
	
	

	

    private String getNomFichier( Part part ) {
        for ( String content : part.getHeader( "content-disposition" ).split( ";" ) ) {
            if ( content.trim().startsWith( "filename" ) )
                return content.substring( content.indexOf( "=" ) + 2, content.length() - 1 );
        }
        return "Default.file";
    }

}
