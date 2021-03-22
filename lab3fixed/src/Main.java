public class Main {

	public static void main(String[] args) {
		//Carte carte = Helper.gson.fromJson(Helper.FileRead("carte.json"), Carte.class);
		
		Carte carte = new Carte();
		
		carte.Edit();
		
		try {
			carte.setPret(-1f);
		} catch (CarteException e) {
			e.Handle();
		}
		
		carte.setNrCopii(2);
		
		carte.setState(3, 5);
	
		carte.Print();
		
		Helper.FileWrite("carte.json", Helper.gson.toJson(carte));
	}
}
