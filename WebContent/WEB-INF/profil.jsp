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
		<h1 class="titre">Informations du profil</h1>

    <c:if test="${utilisateur.noUtilisateur > 0}">
        <p>
            Pseudo : ${utilisateur.pseudo}
        </p>
         <p>
            Nom : ${utilisateur.nom}
        </p>
         <p>
            Prénom : ${utilisateur.prenom}
        </p>
         <p>
            Email : ${utilisateur.email}
        </p>
         <p>
            Téléphone : ${utilisateur.telephone}
        </p>
         <p>
            Rue : ${utilisateur.rue}
        </p>
         <p>
            Code postal : ${utilisateur.codePostal}
        </p>
         <p>
            Ville : ${utilisateur.ville}
        </p>
        <p>
            Mot de passe : ${utilisateur.motDePasse}
        </p>
         <p>
            Credit : ${utilisateur.credit}
        </p>

         <c:if test="${utilisateur.administrateur > 0}">
            <p> Administrateur </p>
        </c:if>
      </c:if>

        <a href="/Encheres/modifier">Modifier les informations de mon profil</a><br />
		<a href="/Encheres/supprimer">Supprimer mon compte</a>
		
	</div>

</body>
</html>