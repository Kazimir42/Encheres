<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/style.css" />
	<meta charset="UTF-8">
	<title>Inscription</title>
</head>
<body>
	<%@ include file="menu.jsp" %>
	
	<div class="container">
	<h1 class="titre">S'inscrire</h1>

     <form method="post" action="/Encheres/inscription">
        <p>
            <label for="pseudo">Pseudo : </label>
            <input type="text" required="required" name="pseudo" id="pseudo" />
        </p>
         <p>
            <label for="nom">Nom : </label>
            <input type="text" required="required" name="nom" id="nom" />
        </p>
         <p>
            <label for="prenom">Prénom : </label>
            <input type="text" required="required" name="prenom" id="prenom" />
        </p>
         <p>
            <label for="email">Email : </label>
            <input type="text" required="required" name="email" id="email" />
        </p>
         <p>
            <label for="telephone">Téléphone : </label>
            <input type="number" required="required" name="telephone" id="telephone" />
        </p>
         <p>
            <label for="rue">Rue : </label>
            <input type="text" required="required" name="rue" id="rue" />
        </p>
         <p>
            <label for="codePostal">Code postal : </label>
            <input type="number" required="required" name="codePostal" id="codePostal" />
        </p>
         <p>
            <label for="ville">Ville : </label>
            <input type="text" required="required" name="ville" id="ville" />
        </p>
        <p>
            <label for="mdp">Mot de passe : </label>
            <input type="password" required="required" name="mdp" id="mdp" />
        </p>
        <p>
            <label for="confirmation">Confirmation : </label>
            <input type="password" required="required" name="confirmation" id="confirmation" />
        </p>
        <p>
            <label for="question1">Quel est votre animal préféré ?</label>
            <input type="text" required="required" name="question1" id="question1" />
        </p>
        
        <p>
            <label for="question2">Quel était le prénom de votre premier amour ?</label>
            <input type="text" required="required" name="question2" id="question2" />
        </p>

            <input type="submit" />
            <input type="checkbox" name="souvenir" id="souvenir" checked>
            <label for="souvenir">Se souvenir de moi</label>

        </form>
                        
        <c:if test="${!empty error}">
			<p class="incorrect">${error}</p>
        </c:if>
    </div>
</body>
</html>