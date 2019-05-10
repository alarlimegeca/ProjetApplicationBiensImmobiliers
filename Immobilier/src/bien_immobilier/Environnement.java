package bien_immobilier;

public enum Environnement {
	
	//3 types de localisation du bien
	Ville("Ville","Ville"),
	Banlieue("Banlieue","Banlieue"),
	Campagne("Campagne","Campagne");
	
	private String name="";
	private String contenu="";
	
	Environnement(String name,String contenu){
		this.name=name;
		this.contenu=contenu;
	}
	
	/**
	*Méthode permettant de récupérer l'objet de type Environnement en type String
	*return une chaîne de caractères correspondant à l'environnement.
	*/
	public String getContenu1() {
		return contenu;
	}
	public String toString(){
		return name;
	}
	
	/**
	*Méthode permettant de passer de l'objet de type String à type Environnement.
	*return un objet de type Environnement.
	*/
	public static Environnement parseEnvironnement(String environnement) {
		if (environnement.equals("Ville")) {
			return Ville;
		}
		if (environnement.equals("Banlieue")) {
			return Banlieue;
		}
		else {
			return Campagne;
		}
	}
}
