package interactions;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion {
	
	static final String DB_URL = "jdbc:sqlite:/home/formation/Bureau/bdd/bddprojet";
	static final String DB_USER = "dbase";
	static final String DB_PASS = "dbase2019";
	
	static Connection connection;
	
	/**
	 * Constructeur
	 * 
	 */
	
	private Connexion (){
		try{
			Class.forName("org.sqlite.JDBC");
			Connexion.connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Récupére ou construit si elle n'existe pas l'instance connexion
	 * @return
	 */
	public static Connection getinstance(){
		if (Connexion.connection == null){
			new Connexion();	
		}
		return Connexion.connection;
	}
}
