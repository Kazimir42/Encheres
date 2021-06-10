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

	
	<div class="container">
		<div class="titre"><h1>Liste des enchères</h1></div>
	
		<div class="filtre">
			<div>
				<form>
				
				<p>Filtres : </p>
				   	
				   	<input type="search" id="recherche" name="recherche" placeholder="Le nom de l'article contient" size=21>
				
					<br />
				
					<label for="categorie">Catégorie : </label>
					<select name="categorie" id="categorie">
    					<option selected value="toutes">Toutes</option>
   			    		<option value="ameublement">Ameublement</option>
    					<option value="informatique">Informatique</option>
   			    		<option value="vetements">Vêtements</option>
    					<option value="sport">Sport et loisirs</option>
					</select>
					
					<br />
					
    				<c:if test="${utilisateur.noUtilisateur > 0 }">
    				<input type="radio" name="typeTo" id="achat" onclick="disableVente()"><label for="achat">Achats : </label>
    				
    				<span id="checkAchat">
    				<input type="checkbox" id="enchereOuverte" name="enchereOuverte">
  					<label for="enchereOuverte">Encheres ouvertes</label>
  					
    				<input type="checkbox" id="enchereEnCours" name="enchereEnCours">
  					<label for="enchereEnCours">Mes dernieres encheres en cours</label>
  					
  					<input type="checkbox" id="enchereRemporte" name="enchereRemporte">
  					<label for="enchereRemporte">Mes enchères remportés</label>
    				</span>
    				<br />
    				
    				
    				<input type="radio" name="typeTo" id="vente" onclick="disableAchat()"><label for="vente">Mes ventes</label>
    				
    				<span id="checkVente">
    				<input type="checkbox" id="venteEnCours" name="venteEnCours">
  					<label for="venteEnCours">Mes ventes en cours</label>
  					
    				<input type="checkbox" id="venteNonDebute" name="venteNonDebute">
  					<label for="venteNonDebute">Mes ventes non débutées</label>
  					
  					<input type="checkbox" id="venteTermine" name="venteTermine">
  					<label for="venteTermine">Mes ventes terminées</label>
    				</span>
    				<br />
    				</c:if>
    				
    				<input type="submit"  value="Rechercher">
				</form>
			</div>
		</div>
	
		<div class="articles">
				

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
      				
      				<c:if test="${utilisateur.noUtilisateur > 0 }">
      					<div class="product-price-btn">
        					<a href="/Encheres/detail?noArticle=${ArticleVendu.noArticle}" ><button type="button">DETAIL</button></a>
      					</div>
      				</c:if>
      				</div>
    			</div>
  			</div>



		</c:forEach>

			
		</div>
	</div>
	
	
	
	
	<script type="text/javascript">

	document.getElementById("venteEnCours").disabled = true;
	document.getElementById("venteNonDebute").disabled = true;
	document.getElementById("venteTermine").disabled = true;	
	document.getElementById("enchereOuverte").disabled = true;
	document.getElementById("enchereEnCours").disabled = true;
	document.getElementById("enchereRemporte").disabled = true;
	document.getElementById("checkAchat").style.color = "grey";
	document.getElementById("checkVente").style.color = "grey";
	
	function disableVente() {
		console.log("on desac les ventes")
		
		document.getElementById("venteEnCours").disabled = true;
		document.getElementById("venteNonDebute").disabled = true;
		document.getElementById("venteTermine").disabled = true;	
		
		document.getElementById("enchereOuverte").disabled = false;
		document.getElementById("enchereEnCours").disabled = false;
		document.getElementById("enchereRemporte").disabled = false;

		document.getElementById("checkAchat").style.color = "black";
		document.getElementById("checkVente").style.color = "grey";
	}
	
	function disableAchat() {
		console.log("on desac les les achats")
		
		document.getElementById("checkAchat").style.color = "grey";
		document.getElementById("checkVente").style.color = "black";
		
		document.getElementById("enchereOuverte").disabled = true;
		document.getElementById("enchereEnCours").disabled = true;
		document.getElementById("enchereRemporte").disabled = true;
		
		document.getElementById("venteEnCours").disabled = false;
		document.getElementById("venteNonDebute").disabled = false;
		document.getElementById("venteTermine").disabled = false;	
		
	}
	/*if(achat.checked == true) {
		
		console.log("achat check");
		  
	}else if(vente.checked == true) {
			
		console.log("vente checke");
		  
	}else{
		console.log("rien de check");
	}*/

	</script>
</body>
</html>