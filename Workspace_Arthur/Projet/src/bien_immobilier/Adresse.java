package bien_immobilier;

public class Adresse {
	
	public int id_adresse;
	public int numero;
	public String voie;
	public String code_postal;
	public String code_INSEE;
	public String commune;
	public String pays;
	
	public Adresse(int id_adresse, int numero, String voie, String code_postal, String code_INSEE, String commune, String pays) {
		super();
		this.id_adresse=id_adresse;
		this.numero=numero;
		this.voie=voie;
		this.code_postal=code_postal;
		this.code_INSEE=code_INSEE;
		this.commune=commune;
		this.pays=pays;
	}
	public int getId_adresse() {
		return id_adresse;
	}
	public void setId_adresse(int id_adresse) {
		this.id_adresse=id_adresse;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero=numero;
	}
	public String getVoie() {
		return voie;
	}
	public void setVoie(String voie) {
		this.voie=voie;
	}
	public String getCode_postal() {
		return code_postal;
	}
	public void setCode_postal(String code_postal) {
		this.code_postal=code_postal;
	}
	public String code_INSEE() {
		return code_INSEE;
	}
	public void setCode_INSEE(String code_INSEE) {
		this.code_INSEE=code_INSEE;
	}
	public String getCommune() {
		return commune;
	}
	public void setCommune(String commune) {
		this.commune=commune;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays=pays;
	}
}
