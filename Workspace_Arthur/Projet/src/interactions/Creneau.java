package interactions;


public class Creneau {

	public String creneau;
	public static boolean occupe;
	
	
	public Creneau(String creneau, boolean occupe) {
		this.creneau=creneau;
		Creneau.occupe=occupe;
	}
	
	public String getCreneau() {
		return this.creneau;
	}
	
	public void setCreneau(String creneau) {
		this.creneau=creneau;
	}
	
	public boolean getOccupe() {
		return Creneau.occupe;
	}
	
	public void setOccupe(boolean occupe) {
		Creneau.occupe=occupe;
	}
	
//	public void ajouterCreneau() {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Nouveau créneau à ajouter : ");
//		String creneau=sc.nextLine();
//		
//		Creneau nom_creneau = new Creneau(creneau);
//	}
	
	
	
	


		
}
