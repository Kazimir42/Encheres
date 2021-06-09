<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detail</title>
<link rel="stylesheet" href="css/style.css" />
</head>
<body>
	<%@ include file="menu.jsp" %>
	<div class="container">
		<div class="titre"><h1>Detail</h1></div>
		<div class="wrapper2">
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
        				
						<p><span class="littleBig">Description de l'article :</span> ${ArticleVendu.description}</p>
						
						<p><span class="littleBig">Meilleur offre :
						
						<c:choose>
    						<c:when test="${ArticleVendu.miseAPrix > ArticleVendu.prixVente}">
        						</span> Aucune
    						</c:when>
   							<c:otherwise>
        						</span> ${ArticleVendu.prixVente} COINS <span class="littleBig">par</span> ${ArticleVendu.pseudoUtilisateurEncherisseur}</p>
    						</c:otherwise>
						</c:choose>
						
						<p><span class="littleBig">Mise a prix :</span> ${ArticleVendu.miseAPrix} COINS</p>
						
        				<p><span class="littleBig">Début de l'enchère :</span> ${ArticleVendu.dateDebutEncheres}</p>
        				<p><span class="littleBig">Fin de l'enchère :</span> ${ArticleVendu.dateFinEncheres}</p>
        				<c:if test="${enCours == 1}"><p><span class="littleBig">Statut de l'enchère : </span><span style="color: orange;">Non débuté</span></p></c:if>
        				<c:if test="${enCours == 2}"><p><span class="littleBig">Statut de l'enchère : </span><span style="color: red;">Terminé</span></p></c:if>
        				<c:if test="${enCours == 3}"><p><span class="littleBig">Statut de l'enchère : </span><span style="color: green;">En cours</span></p></c:if>
        				
        				
        				<p><span class="littleBig">Adresse de retrait :</span> ${Retrait.rue} ${Retrait.codePostal} ${Retrait.ville}</p>
      				    <br />
      				    <p><span class="littleBig">Vendeur :</span>${ArticleVendu.pseudoVendeur}</p>
      				
						
						<c:if test="${utilisateur.noUtilisateur > 0}">
      				
      				  	<c:choose>
    						
    						<c:when test="${utilisateur.pseudo eq ArticleVendu.pseudoVendeur}">

    						</c:when>
   							
   							<c:otherwise>
   							<c:if test="${enCours == 2 && utilisateur.pseudo eq ArticleVendu.pseudoUtilisateurEncherisseur}">
   								<p style="font-size: 26px">Vous avez remporté l'enchere</p>
      						</c:if>
   							
   							<c:if test="${enCours == 3}">
   								<form method="post" action="/Encheres/detail?noArticle=${ArticleVendu.noArticle}">
   								
   								<div class="product-price-btn">
   									<label for="prixPropose">Ma proposition : </label>
            						<input type="number" name="prixPropose" id="prixPropose" />
   									<input type="submit" value="ENCHERIR">
      							</div>
      							
      							</form>
      						</c:if>
    						</c:otherwise>
    						
						</c:choose>
						
						</c:if>
						
						<c:if test="${!empty resultat}">
							${resultat}
       					</c:if>

      				</div>
    			</div>
  			</div>
	</div>
	
</body>
</html>