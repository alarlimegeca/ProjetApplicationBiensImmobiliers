package individus;

import interactions.Annonce;
import interactions.TypeTransaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

public class Dialogue {

public static String pays() {
	    JOptionPane jop = new JOptionPane();
	    String pays = jop.showInputDialog(null, "Veuillez entrer le pays où se situe le bien.", "Pays", JOptionPane.QUESTION_MESSAGE);
		return pays;
}
 
public static String numero() {
	    JOptionPane jop = new JOptionPane();
	    String numero = jop.showInputDialog(null, "Veuillez entrer le numéro du bien.", "Numï¿½ro", JOptionPane.QUESTION_MESSAGE);
	    return numero;
}

public static String voie() {
    JOptionPane jop = new JOptionPane();
    String voie = jop.showInputDialog(null, "Veuillez entrer le nom de la voie du bien.", "Voie", JOptionPane.QUESTION_MESSAGE);
    return voie;
}

public static String code_postal() {
    JOptionPane jop = new JOptionPane();
    String post = jop.showInputDialog(null, "Veuillez entrer le code postal du bien.", "Code postal", JOptionPane.QUESTION_MESSAGE);
    return post;
}

public static String code_INSEE() {
    JOptionPane jop = new JOptionPane();
    String insee = jop.showInputDialog(null, "Veuillez entrer le code insee du bien.", "Code INSEE", JOptionPane.QUESTION_MESSAGE);
    return insee;
}

public static String commune() {
    JOptionPane jop = new JOptionPane();
    String commune = jop.showInputDialog(null, "Veuillez entrer la commune du bien.", "Commune", JOptionPane.QUESTION_MESSAGE);
    return commune;
}

public static String date() {
    JOptionPane jop = new JOptionPane();
    String commune = jop.showInputDialog(null, "Veuillez entrer la date de saisie de la note.", "Note", JOptionPane.QUESTION_MESSAGE);
    return commune;
}
	
public static String typeBien() {
	String[] listeBiens = {"Constructible", "Non habitable", "Habitable"};
	JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
    String type = (String)jop.showInputDialog(null, 
      "Veuillez entrer le type de bien.",
      "Type de bien",
      JOptionPane.QUESTION_MESSAGE,
      null,
      listeBiens,
      listeBiens[2]);
    return type;
	}

public static String typeTransaction() {
	String[] listeBiens = {"Viager", "Vente", "Location"};
	JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
    String type = (String)jop.showInputDialog(null, 
      "Sélectionnez le type de transaction.",
      "Type de transaction",
      JOptionPane.QUESTION_MESSAGE,
      null,
      listeBiens,
      listeBiens[2]);
    return type;
	}

public static String typeHabitation(String typeBien) {
	if (typeBien.equals("Constructible")) {
    String[] listeHabitation = {"Terrain_vague", "Prairie", "Foret"};
    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
    String type = (String)jop.showInputDialog(null, 
      "Veuillez sélectionner le type de bien.",
      "Type de bien",
      JOptionPane.QUESTION_MESSAGE,
      null,
      listeHabitation,
      listeHabitation[2]);
    return type;
	}
	if (typeBien.equals("Non habitable")) {
	String[] listeHabitation = {"Entrepot", "Parking", "Bureaux", "Garage"};
	JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
    String type = (String)jop.showInputDialog(null, 
      "Veuillez sélectionner le type de bien.",
      "Type de bien",
      JOptionPane.QUESTION_MESSAGE,
      null,
      listeHabitation,
      listeHabitation[2]);
    return type;
	}
	else {
	String[] listeHabitation = {"Maison", "Appartement", "Chateau", "Chambre"};
	JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
    String type = (String)jop.showInputDialog(null, 
      "Veuillez sélectionner le type de bien.",
      "Type de bien",
      JOptionPane.QUESTION_MESSAGE,
      null,
      listeHabitation,
      listeHabitation[2]);
    return type;
	}
	
   
}

public static String typeEnvironnement() {
	String[] listeEnvironnements = {"Ville", "Banlieue", "Campagne"};
	JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
    String type = (String)jop.showInputDialog(null, 
      "Veuillez entrer l'environnement.",
      "Environnement",
      JOptionPane.QUESTION_MESSAGE,
      null,
      listeEnvironnements,
      listeEnvironnements[2]);
    return type;
	}
public static String titreAnnonce() {
    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
    String surface = jop.showInputDialog(null, "Veuillez entrer le titre de l'annonce.", "Titre", JOptionPane.QUESTION_MESSAGE);
	return surface;
}	

 public static String surfaceBien() {
	    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String surface = jop.showInputDialog(null, "Veuillez entrer la surface de votre bien (en m²).", "Surface", JOptionPane.QUESTION_MESSAGE);
		return surface;
  }	

 public static String distanceTransports() {
	    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String distance = jop.showInputDialog(null, "Veuillez entrer la distance du bien aux transports (en km).", "Transports", JOptionPane.QUESTION_MESSAGE);
		return distance;
  }
	
  public static String nomBien() {
	    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String nom = jop.showInputDialog(null, "Veuillez entrer le nom de votre bien.", "Nom du bien", JOptionPane.QUESTION_MESSAGE);
		return nom;
  }
  
  public static String qualiteTerrain() {
	    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String qualite = jop.showInputDialog(null, "Veuillez entrer la qualité du terrain (entre 1 et 5).", "Qualité", JOptionPane.QUESTION_MESSAGE);
		return qualite;
}
  
  public static String distanceCommerce() {
	    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String distancecom = jop.showInputDialog(null, "Veuillez entrer la distance du bien par rapport aux commerces (en km).", "Transports", JOptionPane.QUESTION_MESSAGE);
		return distancecom;
}
  
  public static String distanceEcole() {
	    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String distanceeco = jop.showInputDialog(null, "Veuillez entrer la distance du bien à l'école la plus proche (en km).", "Transports", JOptionPane.QUESTION_MESSAGE);
		return distanceeco;
}

  public static String surfaceBatie() {
	    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String surfacebatie = jop.showInputDialog(null, "Veuillez entrer la surface batie (étages inclus) (en m²).", "Surface", JOptionPane.QUESTION_MESSAGE);
		return surfacebatie;
  }
  
  public static String surfaceJardin() {
	    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String surfacejar = jop.showInputDialog(null, "Veuillez entrer la surface du jardin.", "Surface", JOptionPane.QUESTION_MESSAGE);
		return surfacejar;
}
  public static String nb_pieces() {
	    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String nbpieces = jop.showInputDialog(null, "Veuillez entrer le nombre de pièces.", "Composition", JOptionPane.QUESTION_MESSAGE);
		return nbpieces;
}
  public static String nb_salles_eaux() {
	    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String nbbains = jop.showInputDialog(null, "Veuillez entrer le nombre de salles de bains.", "Composition", JOptionPane.QUESTION_MESSAGE);
		return nbbains;
}
  public static String date_construction() {
	    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String datecons = jop.showInputDialog(null, "Veuillez entrer l'année de construction.", "Age", JOptionPane.QUESTION_MESSAGE);
		return datecons;
  }
  
  public static int confirmation(String contexte){
	  JOptionPane jop = new JOptionPane();			
	  int choix = jop.showConfirmDialog(null, contexte, "Validation", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	return choix;

  }
  
  public static String nom_ind(){
	  JOptionPane jop = new JOptionPane();			
	  String nom= jop.showInputDialog(null, "Veuillez entrer votre nom de famille.", "Identité", JOptionPane.QUESTION_MESSAGE);
	return nom;

  }
  
  public static String prenom_ind(){
	  JOptionPane jop = new JOptionPane();			
	  String prenom= jop.showInputDialog(null, "Veuillez entrer votre prenom (seulement le premier).", "Identité", JOptionPane.QUESTION_MESSAGE);
	return prenom;

  }
  
  public static String num_tel_ind(){
	  JOptionPane jop = new JOptionPane();			
	  String num_tel= jop.showInputDialog(null, "Veuillez entrer votre numï¿½ro de tï¿½lï¿½phone.", "Identité", JOptionPane.QUESTION_MESSAGE);
	return num_tel;

  }
  
  public static String e_mail(){
	  JOptionPane jop = new JOptionPane();			
	  String e_mail= jop.showInputDialog(null, "Veuillez entrer votre adresse e-mail.", "Identité", JOptionPane.QUESTION_MESSAGE);
	return e_mail;

  }
  
  public static String pseudo(String statut){
	  JOptionPane jop = new JOptionPane();			
	  String pseudo_client = jop.showInputDialog(null, "Veuillez entrer votre pseudo pour votre compte " +statut+".", "Identité½", JOptionPane.QUESTION_MESSAGE);
	return pseudo_client;

  }
  
  public static String mot_de_passe(String statut){
	  JOptionPane jop = new JOptionPane();			
	  String mdp= jop.showInputDialog(null, "Veuillez entrer votre mot de passe pour votre compte " +statut+ ".", "Identité", JOptionPane.QUESTION_MESSAGE);
	return mdp;

  }
  
  public static String choisir_annonce(String[] array_titres) {
		JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String annonce = (String)jop.showInputDialog(null, 
	      "Pour quelle annonce souhaitez-vous voir les propositions ?",
	      "Proposition",
	      JOptionPane.QUESTION_MESSAGE,
	      null,
	      array_titres,
	      array_titres[0]);
	    return annonce;
		}
  
  public static Annonce choisir_annonces(String[] l_annonces) {
	  int decide = 1;
	  Annonce choix = null;
	  while (decide==1) {
		JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String annonce = (String)jop.showInputDialog(null, 
	      "Quel bien vous intéresse ?",
	      "Proposition",
	      JOptionPane.QUESTION_MESSAGE,
	      null,
	      l_annonces,
	      l_annonces[0]);
	    
	    String separation[]  = annonce.split(" ");
	    List<String> list = Arrays.asList(separation);
	    String id_ann = list.get(0);
	    int id_annonce = Integer.parseInt(id_ann);
	    Annonce ann = BDD.construire_annonce(id_annonce, "annonce");
	    ArrayList<String> affichage = ann.affichage();
	    jop.showMessageDialog(null, affichage.get(0), "Adresse", JOptionPane.INFORMATION_MESSAGE);  
		jop.showMessageDialog(null, affichage.get(1), "Caractéristiques du bien", JOptionPane.INFORMATION_MESSAGE);  
		jop.showMessageDialog(null, affichage.get(2), "Client", JOptionPane.INFORMATION_MESSAGE); 
		decide = confirmation("Voulez-vous acquérir ce bien"); 
		choix = ann;
		
	    }
	return choix;
		}
  
  public static String choisir_particulier(String[] l_particuliers) {
		JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String annonce = (String)jop.showInputDialog(null, 
	      "Avec quelle personne souhaitez vous effectuer la transaction ?",
	      "Proposition",
	      JOptionPane.QUESTION_MESSAGE,
	      null,
	      l_particuliers,
	      l_particuliers[0]);
	    return annonce;
		}
  
  public static String choisir_agent(String[] l_agents) {
		JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String annonce = (String)jop.showInputDialog(null, 
	      "A quel agent souhaitez vous affecter cette annonce ?",
	      "Choix agent",
	      JOptionPane.QUESTION_MESSAGE,
	      null,
	      l_agents,
	      l_agents[0]);
	    return annonce;
		}
  
  public static String notation(String statut){
	  JOptionPane jop = new JOptionPane();			
	  String mdp= jop.showInputDialog(null, "Veuillez entrer la note de l'agent " +statut+ ".", "Identité", JOptionPane.QUESTION_MESSAGE);
	return mdp;

  }
  public static String nom() {
	    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String nom = jop.showInputDialog(null, "Veuillez entrer votre nom : ", "Rendez-vous", JOptionPane.QUESTION_MESSAGE);
		return nom;
}
public static String prenom() {
	    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String prenom = jop.showInputDialog(null, "Veuillez entrer votre prenom : ", "Rendez-vous", JOptionPane.QUESTION_MESSAGE);
		return prenom;
}

public static String telephone() {
	    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String telephone = jop.showInputDialog(null, "Veuillez entrer votre numéro de téléphone : ", "Rendez-vous", JOptionPane.QUESTION_MESSAGE);
		return telephone;
}

public static String email() {
	    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String email = jop.showInputDialog(null, "Veuillez entrer votre adresse email : ", "Rendez-vous", JOptionPane.QUESTION_MESSAGE);
		return email;
}

public static  void refus_rdv(String email, String telephone){
	  JOptionPane jop = new JOptionPane();
	jop.showMessageDialog(null, "Rendez-vous supprimé, pensez à envoyer un mail à : "+email+" ou à appeler le : "+telephone,"Rendez-Vous", JOptionPane.INFORMATION_MESSAGE);
		
}
public static  void afficher_recherche(String liste_biens){
	  JOptionPane jop = new JOptionPane();
	jop.showMessageDialog(null,liste_biens , "Résultat de votre recherche", JOptionPane.INFORMATION_MESSAGE);
		
}
public static  void aucun_resultat(){
	  JOptionPane jop = new JOptionPane();
	jop.showMessageDialog(null,"Votre recherche ne donne aucun résultat" , "Résultat de votre recherche", JOptionPane.INFORMATION_MESSAGE);
		
}

public static String voirRdv() {
  	
  	String[] listeChoix = {"Oui", "Non"};
	JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
    String choix = (String)jop.showInputDialog(null, 
     "Vous avez une demande de rendez-vous, souhaitez-vous y répondre",
      "Rendez-vous",
      JOptionPane.QUESTION_MESSAGE,
      null,
      listeChoix,
      listeChoix[1]);
    return choix;
	}
public static String creneauDispo(String[] liste_creneau_simple){
  
  JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
  String lecreneau = (String)JOptionPane.showInputDialog(null, 
      "Choisissez le créneau vous convenant le mieux : ",
      "Horaire",
      JOptionPane.QUESTION_MESSAGE,
      null,
      liste_creneau_simple,
      liste_creneau_simple[0]);
  return lecreneau;
}

public static String choisirBien(String[] liste_biens){
  
  JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
  String lebien = (String)JOptionPane.showInputDialog(null, 
      "Choisissez le bien que vous voulez visiter : ",
      "Rendez-Vous",
      JOptionPane.QUESTION_MESSAGE,
      null,
      liste_biens,
      liste_biens[0]);
  return lebien;
}
public static  void voir_rdv(String[] liste_rdv){
  JOptionPane jop = new JOptionPane();
jop.showMessageDialog(null, liste_rdv,"Adresse", JOptionPane.INFORMATION_MESSAGE);
	
}

public static String creneau() {
    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
    String creneau = jop.showInputDialog(null, "Nouveau créneau à ajouter : (format jj/mm/AAAA hh:mm)", "Ajout créneau", JOptionPane.QUESTION_MESSAGE);
	return creneau;
}
public static String accepter_rdv(String heure, String nom_bien, String numero, String voie, String code_postal, String commune, 
	  String pays, String nom_particulier, String prenom_particulier) {
  	
  	String[] listeChoix = {"Oui", "Non"};
	JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
    String choix = (String)jop.showInputDialog(null, 
      nom_particulier+" "+prenom_particulier+" vous propose un rendez-vous pour le bien : "+nom_bien+"."+"\nAdresse : "+numero+" "+voie+" "+commune+" "+
    		  code_postal+" "+pays+"\nVoulez-vous accepter ce rendez-vous",
      "Rendez-vous",
      JOptionPane.QUESTION_MESSAGE,
      null,
      listeChoix,
      listeChoix[1]);
    return choix;
	}

public static String choix_rdv_achat() {
  	
  	String[] listeChoix = {"Prendre rdv pour ce bien", "Acheter ce bien"};
	JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
    String choix = (String)jop.showInputDialog(null, 
     "Souhaitez-vous prendre rendez-vous pour ce bien ou l'acheter ? ",
      "Immobilier",
      JOptionPane.QUESTION_MESSAGE,
      null,
      listeChoix,
      listeChoix[0]);
    return choix;
	}

public static String affecter_agent(String[] liste_agent){
	  JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String agent = (String)jop.showInputDialog(null, 
	      "Quel agent voulez-vous affecter à ce rendez-vous ? ",
	      "Rendez-vous",
	      JOptionPane.QUESTION_MESSAGE,
	      null,
	      liste_agent,
	      liste_agent[1]);
	    return agent;
		}


public static String dateAjd(){
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	Date date=new Date();
	String date_ajd=format.format(date);
	return date_ajd;
}

public static  void aucun_creneau(){
	  JOptionPane jop = new JOptionPane();
	jop.showMessageDialog(null,"Nous sommes désolé, il n'y a plus de créneau disponible" , "Résultat de votre recherche", JOptionPane.INFORMATION_MESSAGE);
		
}

  public static int chercher(ArrayList<String> titres_annonces,String object) {
	  int rep = 0;
	  for (int i = 0 ; i<titres_annonces.size();i++) {
		  if (titres_annonces.get(i)==object) {
			  rep = i;
		  }
	  }
	  
	return rep;
	  
  }
  
  public static  void validation_rdv_particulier(){
	  JOptionPane jop = new JOptionPane();
	jop.showMessageDialog(null,"Votre rendez-vous a bien été pris.\nBonne journée." , "Résultat de votre recherche", JOptionPane.INFORMATION_MESSAGE);
		
}
  
  public static  void validation_rdv_respo(){
	  JOptionPane jop = new JOptionPane();
	jop.showMessageDialog(null,"Rendez-vous validé." , "Demande de rendez-vous", JOptionPane.INFORMATION_MESSAGE);
		
}
  
  public static  void pas_de_demande(){
	  JOptionPane jop = new JOptionPane();
	jop.showMessageDialog(null,"Vous n'avez aucune demande de rendez-vous." , "Demande de rendez-vous", JOptionPane.INFORMATION_MESSAGE);
		
}
  public static  void demande_transaction(){
	  JOptionPane jop = new JOptionPane();
	jop.showMessageDialog(null,"Votre demande vient d'être envoyée.\nElle sera analysée par le propriétaire du bien." , "Demande de rendez-vous", JOptionPane.INFORMATION_MESSAGE);
		
}
  
  public static void main(String[] args) {

	  typeTransaction();
  }




}
