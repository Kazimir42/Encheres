<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
    <c:when test="${ noUtilisateur > 0 }">
    	<ul>
    		<li><a href="/Encheres/encheres">Encheres</a></li>
    		<li><a href="/Encheres/vente">Vendre un article</a></li>
    		<li><a href="/Encheres/profil">Mon profil</a></li>
    		<li><a href="/Encheres/deconnexion">Deconnexion</a></li>
		</ul>
    </c:when>    
    <c:otherwise>
		<ul>
    		<li><a href="/Encheres/connexion">S'inscrire, se connecter</a></li>
		</ul>
    </c:otherwise>
</c:choose>
