<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
<link rel="stylesheet" href="css/index.css" />
</head>
<body>
	<%@ include file="menu.jsp" %>

	<div class="titre"><h1>Liste des enchères</h1></div>
	<div class="container">
	<div class="filtre">
		<div>
			<label for="categorie">Catégorie : </label>
		</div>
		<div class="categorie">
			<select name="categorie" id="categorie">
    			<option selected value="toutes">Toutes</option>
   			    <option value="ameublement">Ameublement</option>
    			<option value="informatique">Informatique</option>
   			    <option value="vetements">Vêtements</option>
    			<option value="sport">Sport et loisirs</option>
			</select>
		</div>
		<div>
			<form>
  				<div>
   					 <input type="search" id="recherche" name="recherche"
    					 placeholder="Le nom de l'article contient" size=21>
    				<button>Rechercher</button>
  				</div>
			</form>
		</div>
	</div>
		<div class="articles">
		</div>
	</div>
	
</body>
</html>