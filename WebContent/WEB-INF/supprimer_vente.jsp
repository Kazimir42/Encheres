<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Suppression Vente</title>
<link rel="stylesheet" href="css/style.css" />
</head>
<body>
	<%@ include file="menu.jsp" %>
	
	<div class="container">
		<h1 class="titre">Supprimer la vente</h1>
		
    <p class="info">Attention, cette action est irréversible</p>

    <form method="post" action="/Encheres/supprimer_vente?noArticle=${ArticleVendu.noArticle}">
        <input type="submit" value="Confirmer"/>
    </form>

    <form method="post" action="/Encheres/detail?noArticle=${ArticleVendu.noArticle}">
        <input type="submit" value="Annuler" />
    </form>
    
    </div>

</body>
</html>