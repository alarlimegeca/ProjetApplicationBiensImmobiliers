package individus;

public class Agent_immobilier extends Individu{
	
	// ATTRIBUTS
	
	private String pseudo_agent;
	private String mot_de_passe_agent;
	private double note;

	// CONSTRUCTEURS
	
	public Agent_immobilier(int id_individu, String nom, String prenom, String e_mail, int num_tel, String pseudo_agent, String mot_de_passe_agent, double note) {
		super(id_individu, nom, prenom, e_mail, num_tel);
		this.pseudo_agent = pseudo_agent;
		this.mot_de_passe_agent = mot_de_passe_agent;
		this.note = note;
	}
	
	// ACCESSEURS ET MUTATEURS
	
	public String getPseudoAgent() {
		return pseudo_agent;
	}
	
	public void setPseudoAgent(String pseudo_agent) {
		this.pseudo_agent = pseudo_agent;
	}
	
	public String getPasseAgent() {
		return mot_de_passe_agent;
	}
	
	public void setPasseAgent(String mot_de_passe_agent) {
		this.mot_de_passe_agent = mot_de_passe_agent;
	}
	
	public double getNote() {
		return note;
	}
	
	public void setNote(double note) {
		this.note = note;
	}

}

