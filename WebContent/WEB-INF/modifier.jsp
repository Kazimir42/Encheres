<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inscription</title>
<link rel="stylesheet" href="css/style.css" />
</head>
<body>
	<%@ include file="menu.jsp" %>
	
	<div class="container">
		<h1 class="titre">Modifier mon profil</h1>
	
	<p class = "info">Les informations non modifiées resteront inchangées</p>

     <form method="post" action="/Encheres/modifier">
        <p>
            <label for="pseudo">Pseudo : </label>
            <input type="text" name="pseudo" id="pseudo" value="${utilisateur.pseudo}" />
        </p>
         <p>
            <label for="nom">Nom : </label>
            <input type="text" name="nom" id="nom" value="${utilisateur.nom}" />
        </p>
         <p>
            <label for="prenom">Prénom : </label>
            <input type="text" name="prenom" id="prenom" value="${utilisateur.prenom}" />
        </p>
         <p>
            <label for="email">Email : </label>
            <input type="text" name="email" id="email" value="${utilisateur.email}" />
        </p>
         <p>
            <label for="telephone">Téléphone : </label>
            <input type="number" name="telephone" id="telephone" value="${utilisateur.telephone}" />
        </p>
         <p>
            <label for="rue">Rue : </label>
            <input type="text" name="rue" id="rue" value="${utilisateur.rue}" />
        </p>
         <p>
            <label for="codePostal">Code postal : </label>
            <input type="number" name="codePostal" id="codePostal" value="${utilisateur.codePostal}" />
        </p>
         <p>
            <label for="ville">Ville : </label>
            <input type="text" name="ville" id="ville" value="${utilisateur.ville}" />
        </p>
        <p>
            <label for="mdp">Mot de passe : </label>
            <input type="password" name="mdp" id="mdp" value="${utilisateur.motDePasse}" />
        </p>
         <p>
            <label for="confirmation">Confirmation : </label>
            <input type="password" name="confirmation" id="confirmation" value="${utilisateur.motDePasse}" />
        </p>
        
        <input type="submit" />
        
        </form>
                        
        <c:if test="${!empty error}">
			<p class="incorrect">${error}</p>
        </c:if>
    </div>
</body>
</html>