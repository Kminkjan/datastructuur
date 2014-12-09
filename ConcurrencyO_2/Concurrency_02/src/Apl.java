
public class Apl {

	public static void main(String[] args) {
		Sint sint = new Sint();
		Piet willem = new VerzamelPiet("willem", "black", sint);
		Piet harry = new WerkPiet("harry", "black");
		harry.start();
		willem.start();
	}

}
