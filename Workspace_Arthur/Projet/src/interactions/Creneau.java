package interactions;

import java.util.Date;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Creneau {
	
	public String creneau;	
	
	public Creneau(String creneau) {
		this.creneau=creneau;
	}
	
	public String getCreneau() {
		return this.creneau;
	}
	
	public void setCreneau(String creneau) {
		this.creneau=creneau;
	}
	
	
	public static void ajouterCreneau() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nouveau créneau à ajouter : (format dd/mm/YYYY hh:mm");
		String creneau=sc.nextLine();
		BDD.ajouterCreneau(creneau);
	}
	
	
		
		public static void supprimerCreneau() throws SQLException, ClassNotFoundException, IOException {
		      
		    int count = 0;
			Connection conn = null;
		    Statement stmt = null;
		    Statement stmt2 = null;
		    Statement stmt3 = null;
		    Statement stmt4 = null;
		    Statement stmt5 = null;


		    Class.forName("org.sqlite.JDBC");
		    conn = DriverManager.getConnection("jdbc:sqlite:bdd.db");
		    stmt = conn.createStatement();
		    stmt2 = conn.createStatement();
		    stmt3 = conn.createStatement();
		    stmt4 = conn.createStatement();
		    stmt5 = conn.createStatement();


            ResultSet res = stmt.executeQuery("SELECT max(rowid) FROM creneau");
            while (res.next()){
                count = res.getInt(1);
            }
            
            System.out.println("count = "+count);

            for (int i = count ; i>0 ; i--  ) {
            	try{
            	System.out.println(i+" itération");
            	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            	ResultSet res2 = stmt2.executeQuery("SELECT * FROM creneau WHERE rowid= " + i );
        		Date date_ajd=new Date();
        		String date ="";
                while (res2.next()) {
                	date = res2.getString("heure");
                	System.out.println(date);
                }
            	Date date1 = format.parse(date);
            	System.out.println("date1 ="+date1);
            	System.out.println("dateajd ="+date_ajd);
        		if (date_ajd.compareTo(date1) >= 0) {          	
        			System.out.println("on supprime");
        			conn.setAutoCommit(false);
        			String sql = "DELETE from creneau where heure='"+date+"'";
        			stmt3.executeUpdate(sql);
        			String sql2 ="DELETE from creneau where rowid="+i;
        			stmt4.executeUpdate(sql2);
        			conn.commit();
        		}
            	
            }
            	catch (ParseException e){
            		continue;
            	}
            }
            stmt.close();
            stmt2.close();
            stmt3.close();
            stmt4.close();
            stmt5.close();

		    conn.close();
		}
	

		
}


