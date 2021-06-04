<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
	<link rel="stylesheet" href="css/style.css" />
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
		
		<!-- ${listProduit} -->
		

		<c:forEach items="${listProduit}" var="ArticleVendu">

		
			<!--  UN PRODUIT -->
			<div class="wrapper">
    			<div class="product-img">
    			<c:choose>
    				<c:when test="${empty ArticleVendu.image}">
        				<img class="imgProduct" src="img/default.jpg">
    				</c:when>
   					<c:otherwise>
        				<img class="imgProduct" src="img/${ArticleVendu.image}">
    				</c:otherwise>
				</c:choose>
    			</div>
    			<div class="product-info">
      				<div class="product-text">
        				<h2>${ArticleVendu.nomArticle}</h2>
        				<h3>${ArticleVendu.libelleCategorie}</h3>
        				<c:choose>
    						<c:when test="${ArticleVendu.prixVente > ArticleVendu.miseAPrix}">
        						<p><span class="littleBig">Prix :</span> ${ArticleVendu.prixVente} COINS</p>
    						</c:when>
   							<c:otherwise>
        						<p><span class="littleBig">Prix :</span> ${ArticleVendu.miseAPrix} COINS</p>
    						</c:otherwise>
						</c:choose>
						
        				<p><span class="littleBig">Fin de l'enchère :</span> ${ArticleVendu.dateFinEncheres}</p>
        				<p><span class="littleBig">Vendeur :</span>${ArticleVendu.pseudoVendeur}</p>
      				</div>
      				
      				<c:if test="${utilisateur.noUtilisateur > 0 }">
      					<div class="product-price-btn">
        					<a href="/Encheres/detail?noArticle=${ArticleVendu.noArticle}" ><button type="button">DETAIL</button></a>
      					</div>
      				</c:if>
      				
    			</div>
  			</div>



		</c:forEach>

			
		</div>
	</div>
	
</body>
</html>