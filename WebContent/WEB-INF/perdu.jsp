<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/style.css" />
        <meta charset="utf-8" />
        <title>Connexion</title>
    </head>
    <body>
        <%@ include file="menu.jsp" %>
        
        <div class="container">
        <h1 class="titre">Mot de passe perdu</h1>

        
        <form method="post" action="/Encheres/perdu">
        <p>
            <label for="pseudo">Pseudo : </label>
            <input type="text" name="pseudo" id="pseudo" />
        </p>
        <p>
            <label for="question1">Quel est votre animal préféré ?</label>
            <input type="text" name="question1" id="question1" />
        </p>
        <p>
            <label for="question2">Quel était le prénom de votre premier amour ?</label>
            <input type="text" name="question2" id="question2" />
        </p>
            
            <input type="submit" />  
        </form>
        
        <c:if test="${ error }">
        <p class="incorrect">
            Au moins l'une des réponses est incorrecte pour ce pseudo.
        </p>
        </c:if>
        
        </div>
    </body>
 </html>