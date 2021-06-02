<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Connexion</title>
    </head>
    <body>
		<%@ include file="menu.jsp" %>
        <c:if test="${ !empty pseudo }"><p><c:out value="Bonjour, vous vous appelez ${ pseudo }" /></p></c:if>
        
        <form method="post" action="/Encheres/connexion">
        <p>
            <label for="prenom">Pseudo : </label>
            <input type="text" name="pseudo" id="pseudo" />
        </p>
        <p>
        	<label for="mdp">Mot de passe : </label>
            <input type="password" name="mdp" id="mdp" />
            <a href="/Encheres/perdu" style="font-size:0.8em">Mot de passe oublié ?</a>
        </p>
            
            <input type="submit" />
            <input type="checkbox" name="souvenir" id="souvenir" checked>
            <label for="souvenir">Se souvenir de moi</label>
            
        </form>
        
        <c:if test="${ error }">
        <p>
        	Pseudo ou mot de passe incorrect.
        </p>
        </c:if>
        
        <a href="/Encheres/inscription">S'inscrire</a>
    </body>
 </html>