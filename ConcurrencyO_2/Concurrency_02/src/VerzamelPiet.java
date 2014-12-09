import java.util.ArrayList;


public class VerzamelPiet extends Piet{
	private Sint sint;
	public VerzamelPiet(String name, String color, Sint sint) {
		super(name, color);
		this.sint = sint;
	}
	@Override
	public void run() {
	}
	
	public void doTask() {
		// COLLECT WISHLIST
		try {	// SINT WORDT WAKKER BIJ NIEUW ACTIE
			this.sleep(1000);	// KIJKT OF EEN MEETING PLAATS KAN VINDEN
		} catch (InterruptedException e) { // TODO SINT WAKKER ALS DE PIET ZICH MELD
			e.printStackTrace();
		}
	}	
}
