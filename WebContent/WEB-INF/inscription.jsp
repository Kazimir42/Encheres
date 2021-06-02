<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inscription</title>
</head>
<body>
	<%@ include file="menu.jsp" %>
	<h1>S'inscrire</h1>

     <form method="post" action="/Encheres/inscription">
        <p>
            <label for="pseudo">Pseudo : </label>
            <input type="text" name="pseudo" id="pseudo" />
        </p>
         <p>
            <label for="nom">Nom : </label>
            <input type="text" name="nom" id="nom" />
        </p>
         <p>
            <label for="prenom">Prénom : </label>
            <input type="text" name="prenom" id="prenom" />
        </p>
         <p>
            <label for="email">Email : </label>
            <input type="text" name="email" id="email" />
        </p>
         <p>
            <label for="telephone">Téléphone : </label>
            <input type="number" name="telephone" id="telephone" />
        </p>
         <p>
            <label for="rue">Rue : </label>
            <input type="text" name="rue" id="rue" />
        </p>
         <p>
            <label for="codePostal">Code postal : </label>
            <input type="number" name="codePostal" id="codePostal" />
        </p>
         <p>
            <label for="ville">Ville : </label>
            <input type="text" name="ville" id="ville" />
        </p>
        <p>
            <label for="mdp">Mot de passe : </label>
            <input type="password" name="mdp" id="mdp" />
        </p>
         <p>
            <label for="confirmation">Confirmation : </label>
            <input type="password" name="confirmation" id="confirmation" />
        </p>

            <input type="submit" />
            <input type="checkbox" name="souvenir" id="souvenir" checked>
            <label for="souvenir">Se souvenir de moi</label>

        </form>
                        
        <c:if test="${!empty error}">
			<p>${error}</p>
        </c:if>
</body>
</html>