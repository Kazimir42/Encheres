<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<link rel="stylesheet" href="css/navigation.css" />
</head>
<div class="box">
<div>
<h2>ENI - Enchères</h2>
</div>
<div>
<c:choose>
   <c:when test="${ utilisateur.noUtilisateur > 0 }">
    <ul id="nav">
        <li><a href="/Encheres/encheres">Encheres</a></li>
        <li><a href="/Encheres/vente">Vendre un article</a></li>
        <li><a href="/Encheres/profil">Mon profil</a></li>
        <li><a href="/Encheres/deconnexion">Deconnexion</a></li>
    </ul>
         </c:when>
    <c:otherwise>
          <li><a href="/Encheres/connexion">S'inscrire, se connecter </a></li>
    </c:otherwise>
</c:choose>
</div>
</div>
