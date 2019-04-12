package interactions;

import java.sql.Date;
import java.text.SimpleDateFormat;

import individus.Agent_immobilier;
import individus.Particulier;

public class RendezVous extends Creneau{
	
	public static Creneau creneau;
	public static Particulier leparticulier;
	public static Agent_immobilier lagent;
	
	public RendezVous(Creneau creneau,Particulier leparticulier,Agent_immobilier lagent) {
		super(null, occupe);
		RendezVous.creneau =creneau;
		RendezVous.leparticulier=leparticulier;
		RendezVous.lagent=lagent;
	}
	
	
//	public String afficheHeure() {
//		String date_rdv = creneau.format(new java.util.Date());
//		return date_rdv;
//	}
//	public void ajouter_rdv(SimpleDateFormat heure_rdv ){
//		RendezVous rdv1 = new RendezVous(heure_rdv);
//	}
//	
	public void annuler_rdv(Date heure_rdv) {
	}
	
	public void affecter_rdv(Date heure_rdv, String nom_agent, String nom_particulier) {
		
	}
	
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat( "10/11/1441 16:30" );
		Agent_immobilier Lilian=new Agent_immobilier(007, "Calas", "Lilian", "lilian.calas@agence.fr", 06475215652, "loulou77", "lemotdepasse", 0);
		Particulier Alfred=new Particulier(015615, "Mengin", "Alfred", "alfrendmen@ensg.eu", 0627272727);
		Creneau creneau1 = new Creneau("10/11/1441 16:30",true);
		RendezVous rdv = new RendezVous(creneau1,Alfred,Lilian);
		System.out.println("heure du rdv : "+rdv.creneau.getCreneau()+" avec notre agent "+lagent.getNom()+ ", bonne journée " +leparticulier.getPrenom()); 
	}
}
