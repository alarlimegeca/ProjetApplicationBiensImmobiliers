package interactions;

import java.text.SimpleDateFormat;

import individus.Agent_immobilier;
import individus.Particulier;

public class Transaction {
 
	private SimpleDateFormat date_transaction;
	private static Particulier leparticulier;
	private static Agent_immobilier lagent;
	private Type_Transaction type_transaction;
	
	
	public Transaction(Type_Transaction type_transaction, SimpleDateFormat date_transaction, Particulier leparticulier, Agent_immobilier lagent) {
		super();
		this.type_transaction=type_transaction;
		this.date_transaction=date_transaction;
		Transaction.leparticulier=leparticulier;
		Transaction.lagent=lagent;
	}
	
	
	public Type_Transaction getType_Transaction() {
		return this.type_transaction;
	}
	
	public void setType_Transaction(Type_Transaction type_transaction) {
		this.type_transaction=type_transaction;
	}
	
	public SimpleDateFormat getDate_Transaction() {
		return this.date_transaction;
	}
	
	public void setDate_Transaction(SimpleDateFormat date_transaction) {
		this.date_transaction=date_transaction;
	}
	
	public String afficheDate() {
		String date_affichage = date_transaction.format(new java.util.Date());
		return date_affichage;
	}
	
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat( "10/11/1441 16:30" );
		Particulier Alfred=new Particulier(015615, "Mengin", "Alfred", "alfrendmen@ensg.eu", 0627272727);
		Agent_immobilier Lilian=new Agent_immobilier(007, "Calas", "Lilian", "lilian.calas@agence.fr", 06475215652, "loulou77", "lemotdepasse", 0);
		Transaction number1 = new Transaction(Type_Transaction.Location,sdf,Alfred,Lilian);
		System.out.println("La "+number1.getType_Transaction()+" a bien eu lieu à cette date : "+number1.afficheDate() + " entre notre agent "+lagent.getPrenom()+" "+lagent.getNom()+" et "+leparticulier.getPrenom());
	}

}

