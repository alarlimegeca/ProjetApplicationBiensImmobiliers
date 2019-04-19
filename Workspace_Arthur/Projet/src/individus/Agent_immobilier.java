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
	
	// AUTRES METHODES
	
	static Scanner scan = new Scanner(System.in);
	
	public static void se_connecter_agent() {
		System.out.println("Pseudo_agent ?");
		String pseudo = scan.nextLine();
		System.out.println("Mot_de_passe_agent ?");
		String passe = scan.nextLine();
		Connection conn = null;
        try {
        	// db parameters
        	String url = "jdbc:sqlite:/media/formation/CLEF MENGIN/Projet info/BDD/Individus.db";
        	//create a connection to the database
        	Class.forName("org.sqlite.JDBC");
        	conn = DriverManager.getConnection(url);
        	// Requête SQL
        	String query = "SELECT * FROM agent_immobilier WHERE pseudo_agent LIKE '" + pseudo+"'";
        	Statement state = Connexion.getinstance().createStatement();
        	ResultSet result = state.executeQuery(query);
            
            String mdp = result.getString("mot_de_passe_agent");
            
            
            if (mdp.contentEquals(passe)) {
            	System.out.println("Vous êtes connecté");
            }
            else { System.out.println("Mot de passe incorrect");}

        } catch (SQLException e1) {
        	System.out.println(e1.getMessage());
        	
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
        	try {
        		if (conn != null) {
        			conn.close();
        		}
        	} catch (SQLException ex) {
        		System.out.println(ex.getMessage());
        	}
        }

	}

}

