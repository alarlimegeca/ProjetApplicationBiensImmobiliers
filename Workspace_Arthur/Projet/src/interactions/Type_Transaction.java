
package interactions;

public enum TypeTransaction {
		Vente("Vente","Vente"),
		Location("Location","Location"),
		Vente_viager("Vente_viager","Vente_viager");
		
		
		private String name="";
		private String contenu="";
		
		TypeTransaction(String name,String contenu){
			this.name=name;
			this.contenu=contenu;
		}
		public String getContenu3() {
			return contenu;
		}
		public String toString(){
			return name;
		}
}
