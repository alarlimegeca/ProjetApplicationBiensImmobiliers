package individus;

public class Particulier extends Individu{
	
	// CONSTRUCTEURS

	public Particulier(int id_individu, String nom, String prenom, String e_mail, int num_tel) {
		super(id_individu, nom, prenom, e_mail, num_tel);
		
	}
	
	public static int donnerInfoRdv(){
		String nom = Dialogue.nom();
		String prenom = Dialogue.prenom();
		String telephone = Dialogue.telephone();
		String email = Dialogue.email();
		int id_particulier = BDD.trouver_id(nom, prenom);
		BDD.ajouterParticulier(id_particulier, nom, prenom, email, telephone);
		return id_particulier;
	}
	

}
