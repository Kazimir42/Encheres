package fr.eni.encheres.bo;

public class Utilisateur {
	private int noUtilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private int telephone; //A MODIF SI ETRANGE
	private String rue;
	private int codePostal;	//A MODIFE SI CORSE
	private String ville;
	private String motDePasse;
	private int credit;
	private byte administrateur;
	
	private String error = "";
	private String question1 = "";
	private String question2= "";
	
	


	public Utilisateur() {
		
	}
	
	public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, int telephone,
			String rue, int codePostal, String ville, String motDePasse, int credit, byte administrateur, String question1, String question2) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
		this.question1 = question1;
		this.question2 = question2;
	}
	
	public int getNoUtilisateur() {
		return noUtilisateur;
	}
	
	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	
	public String getPseudo() {
		return pseudo;
	}
	
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getTelephone() {
		return telephone;
	}
	
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	
	public String getRue() {
		return rue;
	}
	
	public void setRue(String rue) {
		this.rue = rue;
	}
	
	public int getCodePostal() {
		return codePostal;
	}
	
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}
	
	public String getVille() {
		return ville;
	}
	
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	public String getMotDePasse() {
		return motDePasse;
	}
	
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	public int getCredit() {
		return credit;
	}
	
	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	public byte getAdministrateur() {
		return administrateur;
	}
	
	public void setAdministrateur(byte administrateur) {
		this.administrateur = administrateur;
	}
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	public String getQuestion1() {
		return question1;
	}
	
	public void setQuestion1(String question1) {
		this.question1 = question1;
	}
	
	public String getQuestion2() {
		return question2;
	}
	
	public void setQuestion2(String question2) {
		this.question2 = question2;
	}
	
	public boolean isValide() {
		boolean validite;
		
		if (pseudo.isBlank() || nom.isBlank() || prenom.isBlank() || email.isBlank() || telephone == 0 || rue.isBlank() || codePostal == 0 || ville.isBlank() || motDePasse.isBlank()) {
			validite = false;
			System.out.println("isValide est PAS OK");
		}else {
			validite = true;
			System.out.println("isValide est OK");

		}
		
		return validite;
	}

	@Override
	public String toString() {
		return "Utilisateur [noUtilisateur=" + noUtilisateur + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom="
				+ prenom + ", email=" + email + ", telephone=" + telephone + ", rue=" + rue + ", codePostal="
				+ codePostal + ", ville=" + ville + ", motDePasse=" + motDePasse + ", credit=" + credit
				+ ", administrateur=" + administrateur + ", error=" + error + ", question1 =" + ",question2 =" + "]";
	}
	
	

}