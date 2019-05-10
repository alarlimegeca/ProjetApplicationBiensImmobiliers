package individus;

public abstract class Individu {
	
	// ATTRIBUTS
	
	private int id_individu;
	private String nom;
	private String prenom;
	private String e_mail;
	private String num_tel;
	
	// CONSTRUCTEURS
	
	public Individu(int id_individu, String nom, String prenom, String e_mail,String num_tel) {
		this.id_individu = id_individu;
		this.nom = nom;
		this.prenom = prenom;
		this.e_mail = e_mail;
		this.num_tel = num_tel;
	}
	
	// ACCESSEURS ET MUTATEURS
	
	public int getId() {
		return id_individu;
	}
	
	public void setId(int id_individu) {
		this.id_individu = id_individu;
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
		return e_mail;
	}
	
	public void setEmail(String e_mail) {
		this.e_mail = e_mail;
	}
	
	public String getNum() {
		return num_tel;
	}
	
	public void setNum(String num_tel) {
		this.num_tel = num_tel;
	}

	// AUTRES METHODES
	
	/**
	 * permet d'afficher les attributs de l'individu en fonction de son statut
	 * @param statut
	 * @return affichage individu
	 */
	
	public String toString(String statut){
		  String str = "Description " +statut+"\n";
		  if (this.id_individu == 0) {
			  str += "Identifiant : à déterminer \n";
		  }
		  else {
			  str += "Identifiant : " + this.id_individu + "\n";
		  }
	      str += "Nom : " + this.nom+ "\n";
	      str += "Prénom : " + this.prenom +" \n";
	      str += "Courriel : " + this.e_mail  +  " \n";
	      str += "Numéro téléphone : " + this.num_tel + " \n";
	      return str;
	}
	
}


