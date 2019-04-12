package individus;

public abstract class Individu {
	
	// ATTRIBUTS
	
	private int id_individu;
	private String nom;
	private String prenom;
	private String e_mail;
	private int num_tel;
	
	// CONSTRUCTEURS
	
	public Individu(int id_individu, String nom, String prenom, String e_mail,int num_tel) {
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
	
	public int getNum() {
		return num_tel;
	}
	
	public void setNum(int num_tel) {
		this.num_tel = num_tel;
	}

}

