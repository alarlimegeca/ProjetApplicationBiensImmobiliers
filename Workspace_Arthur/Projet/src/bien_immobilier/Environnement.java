package bien_immobilier;

public enum Environnement {
	Ville("Ville","Ville"),
	Banlieue("Banlieue","Banlieue"),
	Campagne("Campagne","Campagne");
	
	private String name="";
	private String contenu="";
	
	Environnement(String name,String contenu){
		this.name=name;
		this.contenu=contenu;
	}
	public String getContenu1() {
		return contenu;
	}
	public String toString(){
		return name;
	}
	
}