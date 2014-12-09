
public class Apl {

	public static void main(String[] args) {
		Sint sint = new Sint();
		Piet p = new VerzamelPiet("collector", "black", sint);
		Piet harry = new WerkPiet("worker", "black");
		harry.start();
		p.start();
	}

}
