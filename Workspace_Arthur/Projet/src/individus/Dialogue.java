package individu;

import interactions.TypeTransaction;

import javax.swing.JOptionPane;

public class Dialogue {
	
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

public static String typeHabitation(String typeBien) {
	if (typeBien.equals("Constructible")) {
    String[] listeHabitation = {"Terrain_vague", "Prairie", "Forêt"};
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
	    String surface = jop.showInputDialog(null, "Veuillez entrer la surface de votre bien.", "Surface", JOptionPane.QUESTION_MESSAGE);
		return surface;
  }	

 public static String distanceTransports() {
	    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String distance = jop.showInputDialog(null, "Veuillez entrer la distance du bien aux transports.", "Transports", JOptionPane.QUESTION_MESSAGE);
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
	    String distancecom = jop.showInputDialog(null, "Veuillez entrer la distance du bien par rapport aux commerces.", "Transports", JOptionPane.QUESTION_MESSAGE);
		return distancecom;
}
  
  public static String distanceEcole() {
	    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String distanceeco = jop.showInputDialog(null, "Veuillez entrer la distance du bien à l'école la plus proche (km).", "Transports", JOptionPane.QUESTION_MESSAGE);
		return distanceeco;
}
	
  public static String surfaceJardin() {
	    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String surfacejar = jop.showInputDialog(null, "Veuillez entrer la surface du jardin.", "Transports", JOptionPane.QUESTION_MESSAGE);
		return surfacejar;
}
  public static String nb_pieces() {
	    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String nbpieces = jop.showInputDialog(null, "Veuillez entrer le nombre de pièces.", "Transports", JOptionPane.QUESTION_MESSAGE);
		return nbpieces;
}
  public static String nb_salles_eaux() {
	    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String nbbains = jop.showInputDialog(null, "Veuillez entrer le nombre de salles de bains.", "Transports", JOptionPane.QUESTION_MESSAGE);
		return nbbains;
}
	
 public static int confirmation(String contexte){
  JOptionPane jop = new JOptionPane();			
  int choix = jop.showConfirmDialog(null, contexte, "Validation", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
return choix;

}
  public static void main(String[] args) {
 typeBien();
   
  }
}
