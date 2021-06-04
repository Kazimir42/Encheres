<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Suppression</title>
<link rel="stylesheet" href="css/style.css" />
</head>
<body>
	<%@ include file="menu.jsp" %>
    <h1>Supprimer le compte ?</h1>
    <p class="info">Attention, cette action est irréversible</p>

    <form method="post" action="/Encheres/supprimer">
        <input type="submit" value="Confirmer"/>
    </form>

    <form method="post" action="/Encheres/profil">
        <input type="submit" value="Annuler" />
    </form>

</body>
</html>