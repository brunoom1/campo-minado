import com.gabrielmendonca.campoMinado.CampoMinado;

public class App {

	public static void main(String args[]) {
		
		CampoMinado game = new CampoMinado(20, 20);
		game.init();

		System.out.print(game);
		
	}
}

