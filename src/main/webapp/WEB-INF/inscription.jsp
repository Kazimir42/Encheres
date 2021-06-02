<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inscription</title>
</head>
<body>
	 <form method="post" action="/Encheres/connexion">
        <p>
            <label for="pseudo">Pseudo : </label>
            <input type="text" name="pseudo" id="pseudo" />
        </p>
         <p>
            <label for="nom">Nom : </label>
            <input type="text" name="nom" id="nom" />
        </p>
         <p>
            <label for="prenom">Prénom : </label>
            <input type="text" name="prenom" id="prenom" />
        </p>
         <p>
            <label for="email">Email : </label>
            <input type="text" name="email" id="email" />
        </p>
         <p>
            <label for="telephone">Téléphone : </label>
            <input type="text" name="telephone" id="telephone" />
        </p>
         <p>
            <label for="rue">Rue : </label>
            <input type="text" name="rue" id="rue" />
        </p>
         <p>
            <label for="codePostal">Code postal : </label>
            <input type="text" name="codePostal" id="codePostal" />
        </p>
         <p>
            <label for="ville">Ville : </label>
            <input type="text" name="ville" id="ville" />
        </p>
        <p>
        	<label for="mdp">Mot de passe : </label>
            <input type="text" name="mdp" id="mdp" />
        </p>
         <p>
            <label for="confirmation">Confirmation : </label>
            <input type="text" name="confirmation" id="confirmation" />
        </p>
            
            <input type="submit" />
            <input type="checkbox" name="souvenir" id="souvenir" checked>
            <label for="souvenir">Se souvenir de moi</label>
            
        </form>
</body>
</html>