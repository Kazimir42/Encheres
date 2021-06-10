<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/style.css" />
	<meta charset="UTF-8">
	<title>Modifier vente</title>
</head>
<body>
	<%@ include file="menu.jsp" %>
	
	<div class="container">
	
    <h1 class="titre">Modifier vente</h1>
    
    <c:if test="${utilisateur.noUtilisateur > 0}">
    
    <form method="post" action="/Encheres/modifier_vente?noArticle=${ArticleVendu.noArticle}" enctype="multipart/form-data">
        <p>
            <label for="nomArticle">Article : </label>
            <input type="text" required="required" name="nomArticle" id="nomArticle" value="${ArticleVendu.nomArticle}"/>
        </p>
         <p>
            <label for="descArticle">Description : </label>
            <textarea rows="3" cols="20" required="required" name="descArticle" id="descArticle">${ArticleVendu.description}</textarea>
        </p>
         <p>
            <label for="catArticle">Catégorie : </label>
            
            <c:set var="string1" value="${ArticleVendu.libelleCategorie}"/>
      		<c:set var = "string2" value = "${fn:trim(string1)}" />
			
			
			<c:if test="${string2 eq 'Sport&Loisirs'}">
           		<select name="catArticle" id="catArticle">
           			<option value="Sport&Loisirs">Sport et Loisirs</option>
    				<option value="Informatique">Informatique</option>
    				<option value="Ameublement">Ameublement</option>
    				<option value="Vetement">Vêtement</option>
				</select>
           </c:if>
           
           <c:if test="${string2 eq 'Vetement'}">
           		<select name="catArticle" id="catArticle">
           			<option value="Vetement">Vêtement</option>
           			<option value="Sport&Loisirs">Sport et Loisirs</option>
    				<option value="Informatique">Informatique</option>
    				<option value="Ameublement">Ameublement</option>
				</select>
           </c:if>
           
           <c:if test="${string2 eq 'Informatique'}">
           		<select name="catArticle" id="catArticle">
           			<option value="Informatique">Informatique</option>
           			<option value="Vetement">Vêtement</option>
           			<option value="Sport&Loisirs">Sport et Loisirs</option>
    				<option value="Ameublement">Ameublement</option>
				</select>
           </c:if>
           
           <c:if test="${string2 eq 'Ameublement'}">
           		<select name="catArticle" id="catArticle">
           			<option value="Ameublement">Ameublement</option>
           			<option value="Informatique">Informatique</option>
           			<option value="Vetement">Vêtement</option>
           			<option value="Sport&Loisirs">Sport et Loisirs</option>
				</select>
           </c:if>
        </p>
         <p>
            <label for="fichier">Photo de l'article : </label>
            <input type="file" name="fichier" id="fichier" /> ${ArticleVendu.image}
        </p>
         <p>
            <label for="prixArticle">Mise à prix : </label>
            <input type="number" required="required" name="prixArticle" id="prixArticle" value="${ArticleVendu.miseAPrix}"/>
        </p>
         <p>
            <label for="dateDebutArticle">Début de l'enchère :</label>
            <input type="datetime-local" required="required" name="dateDebutArticle" id="dateDebutArticle" value="${datedebut}"/>
        </p>
         <p>
            <label for="dateFinArticle">Fin de l'enchère : </label>
            <input type="datetime-local" required="required" name="dateFinArticle" id="dateFinArticle" value="${datefin}"/>
        </p>
        
        <br />
        
        <p>Retrait :</p>
        
         <p>
            <label for="rueRetrait">Rue : </label>
            <input type="text" required="required" name="rueRetrait" id="rueRetrait" value="${Retrait.rue}"/>
        </p>
        <p>
            <label for="codePostalRetrait">Code postal : </label>
            <input type="number" required="required" name="codePostalRetrait" id="codePostalRetrait" value="${Retrait.codePostal}"/>
        </p>
        <p>
            <label for="villeRetrait">Ville : </label>
            <input type="text" required="required" name="villeRetrait" id="villeRetrait" value="${Retrait.ville}"/>
        </p>
        
        <br /><br />

            <input type="submit" value="Enregistrer"/>
            <a href="/Encheres/detail?noArticle=${ArticleVendu.noArticle}">Annuler</a>

	</form>
	
	<c:if test="${!empty theError}">
		<p class="incorrect">${theError}</p>
    </c:if>
    
    </c:if>
    
    </div>
</body>
</html>