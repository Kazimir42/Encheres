<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="css/navigation.css" />

<div class="box">
	<div class="logo">
		<a href="/Encheres/" style="font-size: 30px; text-decoration: none">ENI - Enchères</a>
	</div>
<div>
<c:choose>
   <c:when test="${ utilisateur.noUtilisateur > 0 }">
    <ul id="nav">
    	<c:if test="${utilisateur.administrateur == 1}">
    	<li><a href="/Encheres/panel_admin">Panel Admin</a></li>
    	</c:if>
        <li><a href="/Encheres/">Encheres</a></li>
        <li><a href="/Encheres/nouvelle_vente">Vendre un article</a></li>
        <li><a href="/Encheres/profil">Mon profil</a></li>
        <li><a href="/Encheres/deconnexion">Deconnexion</a></li>
    </ul>
    </c:when>
    <c:otherwise>
     <ul id="nav">
          <li><a href="/Encheres/connexion" style="">S'inscrire, se connecter </a></li>
     </ul>
    </c:otherwise>
</c:choose>
</div>
</div>