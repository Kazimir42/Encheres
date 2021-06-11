<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css" />
<meta charset="UTF-8">
<title>Panel admin</title>
</head>
<body>

	<%@ include file="menu.jsp" %>
	
	<div class="container">
		<h1 class="titre">Panel Admin</h1>
		<br />
		
		<form action="/Encheres/panel_admin" method="post">
		
			<label for="selectUser">Selectionnez un utilisateur :</label>
			<select name="selectUser" id="selectUser">
				<c:forEach items="${listUtilisateur}" var="utilisateur">
					<option value="${utilisateur.noUtilisateur}">${utilisateur.pseudo} | ${utilisateur.email}</option>
				</c:forEach>
			</select>
			
			<br /><br />
			
			<input type="radio" name="supprimer" id="supprimer" >
			<label for="supprimer">Supprimer</label>
			
			<br />
			
			<input type="radio" name="desactiver" id="desactiver" >
			<label for="desactiver">Désactiver</label>
			
			<br /><br />
			
			<input type="submit"  value="  Do it  ">
			
		</form>	
		
		
		
		
		
	</div>

</body>
</html>