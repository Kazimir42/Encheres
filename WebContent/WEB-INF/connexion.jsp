<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connexion</title>
</head>
    <body>
    <%@ include file="menu.jsp" %>
	
        <form method="post" action="/Encheres/connexion">
        <p>
            <label for="prenom">Pseudo : </label>
            <input type="text" name="pseudo" id="pseudo" />
        </p>
        <p>
        	<label for="mdp">Mot de passe : </label>
            <input type="text" name="mdp" id="mdp" />
        </p>
            
            <input type="submit" />
        </form>
        
        <c:if test="${error}">
        	<p>Echec de la connexion</p>
        </c:if>
        
        
    </body>
</html>