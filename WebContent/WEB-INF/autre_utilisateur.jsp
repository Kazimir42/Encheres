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
            Pseudo : ${autreUtilisateur.pseudo}
        </p>
         <p>
            Nom : ${autreUtilisateur.nom}
        </p>
         <p>
            Prénom : ${autreUtilisateur.prenom}
        </p>
         <p>
            Email : ${autreUtilisateur.email}
        </p>
         <p>
            Téléphone : ${autreUtilisateur.telephone}
        </p>
         <p>
            Rue : ${autreUtilisateur.rue}
        </p>
         <p>
            Code postal : ${autreUtilisateur.codePostal}
        </p>
         <p>
            Ville : ${autreUtilisateur.ville}
        </p>

      </c:if>

    </div>

</body>
</html>