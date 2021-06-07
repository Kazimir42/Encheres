<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/style.css" />
	<meta charset="UTF-8">
	<title>Nouvelle vente</title>
</head>
<body>
	<%@ include file="menu.jsp" %>
	
	<div class="container">
	
    <h1 class="titre">Nouvelle vente</h1>
    
    <c:if test="${utilisateur.noUtilisateur > 0}">
    
    <form method="post" action="/Encheres/nouvelle_vente" enctype="multipart/form-data">
        <p>
            <label for="nomArticle">Article : </label>
            <input type="text" required="required" name="nomArticle" id="nomArticle" />
        </p>
         <p>
            <label for="descArticle">Description : </label>
            <textarea rows="3" cols="20" required="required" name="descArticle" id="descArticle"></textarea>
        </p>
         <p>
            <label for="catArticle">Catégorie : </label>
            
			<select name="catArticle" id="catArticle">
    			<option value="Informatique">Informatique</option>
    			<option value="Ameublement">Ameublement</option>
    			<option value="Vetement">Vêtement</option>
    			<option value="Sport&Loisirs">Sport et Loisirs</option>
			</select>
        </p>
         <p>
            <label for="fichier">Photo de l'article : </label>
            <input type="file" name="fichier" id="fichier" />
        </p>
         <p>
            <label for="prixArticle">Mise à prix : </label>
            <input type="number" required="required" name="prixArticle" id="prixArticle" />
        </p>
         <p>
            <label for="dateDebutArticle">Début de l'enchère :</label>
            <input type="date" required="required" name="dateDebutArticle" id="dateDebutArticle" />
        </p>
         <p>
            <label for="dateFinArticle">Fin de l'enchère : </label>
            <input type="date" required="required" name="dateFinArticle" id="dateFinArticle" />
        </p>
        
        <br />
        
        <p>Retrait :</p>
        
         <p>
            <label for="rueRetrait">Rue : </label>
            <input type="text" required="required" name="rueRetrait" id="rueRetrait" />
        </p>
        <p>
            <label for="codePostalRetrait">Code postal : </label>
            <input type="number" required="required" name="codePostalRetrait" id="codePostalRetrait" />
        </p>
        <p>
            <label for="villeRetrait">Ville : </label>
            <input type="text" required="required" name="villeRetrait" id="villeRetrait" />
        </p>
        
        <br /><br />

            <input type="submit" value="Enregistrer"/>
            <a href="/Encheres/">Annuler</a>

	</form>
	
	<c:if test="${!empty theError}">
		<p class="incorrect">${theError}</p>
    </c:if>
    
    </c:if>
    
    </div>
</body>
</html>