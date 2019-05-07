package individus;

public class Particulier extends Individu{
	
	// CONSTRUCTEURS

	public Particulier(int id_individu, String nom, String prenom, String e_mail, int num_tel) {
		super(id_individu, nom, prenom, e_mail, num_tel);
		
	}
	
	public static ArrayList choisirBien() throws ClassNotFoundException, SQLException{
		Connection conn = null;
	    Statement stmt = null;
	    Statement stmt2 = null;
	    Class.forName("org.sqlite.JDBC");
	    conn = DriverManager.getConnection("jdbc:sqlite:bdd.db");
	    stmt = conn.createStatement();
	    stmt2 = conn.createStatement();
		ArrayList<String> liste_biens=new ArrayList<>();
		ArrayList liste_resultat=new ArrayList<>();
		
	    ResultSet res = stmt.executeQuery("SELECT * FROM biens_immobiliers WHERE en_ligne =1");
	    while (res.next()){
	           String bien = res.getString("nom");
	           liste_biens.add(bien);
	    		}
   	  			String[] liste_biens_simple = new String[ liste_biens.size() ];
   	  			liste_biens.toArray( liste_biens_simple );
   	  			String choix_bien = Dialogue.choisirBien(liste_biens_simple);
   	  			liste_resultat.add(choix_bien);
   	  			ResultSet res2 = stmt2.executeQuery("SELECT * FROM biens_immobiliers WHERE nom LIKE '"+choix_bien+"'");
   	  			while (res2.next()){
   	  				int id_bien = res2.getInt("id_bien");
   	  				liste_resultat.add(id_bien);
   	  			}
	    stmt.close();
            stmt2.close();
	    conn.close();
	    return liste_resultat; 

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
