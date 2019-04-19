package bien_immobilier;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion {

	private final String DB_URL = "jdbc:postgresql://localhost:5432/gestion_immobilier";
	private final String USER = "postgres";

	private final String PASS = "postgres";
	private static Connection connection;

	private Connexion() {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Connection getInstance(){
		if(connection == null){
			new Connexion();
		}
		return connection;
	}
}
