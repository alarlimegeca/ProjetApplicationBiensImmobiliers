package bien_immobilier;

public enum TypeHabitation {
	Terrain_vague("Terrain_vague","Terrain_vague"),
	Prairie("Prairie","Prairie"),
	Foret("Foret","Foret"),
	Parking("Parking","Parking"),
	Entrepot("Entrepot","Entrepot"),
	Garage("Garage","Garage"),
	Bureaux("Bureaux","Bureaux"),
	Chateau("Chateau","Chateau"),
	Maison("Maison","Maison"),
	Appartement("Appartement","Appartement"),
	Chambre("Chambre","Chambre");
	
	private String name="";
	private String contenu="";
	
	TypeHabitation(String name,String contenu){
		this.name=name;
		this.contenu=contenu;
	}
	public String getContenu2() {
		return contenu;
	}
	public String toString(){
		return name;
	}

	
	public TypeHabitation parseHabitation(String habitation){
		if (habitation.equals("Prairie")){
			return Prairie;
		}
		if (habitation.equals("Foret")){
			return Foret;
		}
		if (habitation.equals("Maison")){
			return Maison;
		}
		if (habitation.equals("Appartement")){
			return Appartement;
		}
		if (habitation.equals("Chambre")){
			return Chambre;
		}
		if (habitation.equals("Entrepot")){
			return Entrepot;
		}
		if (habitation.equals("Parking")){
			return Parking;
		}
		if (habitation.equals("Bureaux")){
			return Bureaux;
		}
		else {
			return Terrain_vague;
		}
	}
}
