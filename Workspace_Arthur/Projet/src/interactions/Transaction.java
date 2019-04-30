package interactions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import individus.Agent_immobilier;
import individus.Particulier;

public class Transaction {
 
	private String date_transaction;
	private Particulier leparticulier;
	private Agent_immobilier lagent;
	private Type_Transaction type_transaction;
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	

	public Transaction(Type_Transaction type_transaction, Particulier leparticulier, Agent_immobilier lagent) {
		super();
		this.type_transaction=type_transaction;
		this.leparticulier=leparticulier;
		this.lagent=lagent;
		this.date_transaction = Date_Ajd();
	}
	
	public String Date_Ajd(){
		Date date=new Date();	
		String date_ajd = dateFormat.format(date);
		return date_ajd;

	}
	
	public Type_Transaction getType_Transaction() {
		return this.type_transaction;
	}
	
	public void setType_Transaction(Type_Transaction type_transaction) {
		this.type_transaction=type_transaction;
	}
	
	public String getDate_Transaction() {
		return this.date_transaction;
	}
	
	public void setDate_Transaction(String date_transaction) {
		this.date_transaction=date_transaction;
	}
	

}

