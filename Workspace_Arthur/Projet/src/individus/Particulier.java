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
	
		public static String typeBien() {
		String[] listeBiens = {"Constructible", "Non habitable", "Habitable"};
		JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String type = (String)jop.showInputDialog(null, 
	      "Veuillez entrer le type de bien que vous souhaitez (Constructible, Non habitable ou Habitable).",
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
	      "Veuillez sélectionner le type de bien (Terrain vague, Prairie ou Forêt).",
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
	      "Veuillez sélectionner le type de bien (Entrepot, Parking, Bureaux ou Garage).",
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
	      "Veuillez sélectionner le type de bien (Maison, Appartement, Chateau ou Chambre).",
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
	      "Veuillez entrer la localisation recherchée (Ville, Banlieue ou Campagne).",
	      "Environnement",
	      JOptionPane.QUESTION_MESSAGE,
	      null,
	      listeEnvironnements,
	      listeEnvironnements[2]);
	    return type;
	}
	
	
	
	
	
	public boolean recherche_bien() {
		
		Connection conn = null;
	    try {
	    	// db parameters
	    	String url = "jdbc:sqlite:bdd";
	    	//create a connection to the database
	    	Class.forName("org.sqlite.JDBC");
	    	conn = DriverManager.getConnection(url);
			
			// Requête SQL
			String query = "SELECT * FROM Constructible WHERE type_hab LIKE '"+Particulier.typeHabitation(typeBien())+"' AND type_env LIKE '"+Particulier.typeEnvironnement()+"'";
			
			Statement state = Connexion.getinstance().createStatement();

	        ResultSet result = state.executeQuery(query);
	        while (result.next()) {
	        	String nom = result.getString("nom");
	        	Double surface = result.getDouble("surface");
	        	Double jardin  = result.getDouble("jardin");
	        	Integer quali_terrain= result.getInt("quali_terrain");
	        	Integer nbr_pieces =result.getInt("nbr_pieces");
	        	Integer nbr_sallesdeau =result.getInt("nbr_sallesdeau");
	        	Double ecole =result.getDouble("ecole");
	        	Double commerce =result.getDouble("commerce");
	        	Double transports =result.getDouble("transports");
	        	Integer date_construction = result.getInt("date_construction");
	        	System.out.println("nom:"+nom+"  date de la construction:"+date_construction+"  surface:"+surface+"  jardin:"+jardin+"  qualité du terrain:"+quali_terrain+"  nombre de pièces:"+nbr_pieces+"  nombre de salles d'eau:"+nbr_sallesdeau+"  distance des transports en commun:"+transports+"  distance d'un commerce:"+commerce+"  distance d'une école:"+ecole+"");
	        	}
	        }
	        
	         catch (SQLException e1) {
	        	System.out.println(e1.getMessage());
	        	
	        } catch (ClassNotFoundException e) {
	    		e.printStackTrace();
	    	} finally {
	        	try {
	        		if (conn != null) {
	        			conn.close();
	        		}
	        	} catch (SQLException ex) {
	        		System.out.println(ex.getMessage());
	        	}
	    	return false;
	    	
	        }
	    }
	

}
