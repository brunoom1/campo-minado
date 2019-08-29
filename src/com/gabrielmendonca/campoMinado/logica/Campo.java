package com.gabrielmendonca.campoMinado.logica;
import java.util.ArrayList;
import com.gabrielmendonca.campoMinado.logica.Mina;

/**
 * Class campo, define o local onde estão as minas
 * 
 * @author gabriel
 */

public class Campo {

	/**
	 * Campo l
	 */
	ArrayList<ArrayList<Mina>> minas;

	public Campo () {
		this.minas = new ArrayList<ArrayList<Mina>>();
	}

	public void adicionarMina(Mina mina, int col, int lin) {

		if (col == 0) {
			this.minas.add(new ArrayList<Mina>());
		}
		
		this.minas.get(lin).add(mina);
	}
	
	public int totalMinas () {
		return this.minas.size() * this.minas.size();
	}
	
	public ArrayList<ArrayList<Mina>> getMinas () {
		return this.minas;
	}
		
	public String toString () {
		String result = "";
		int raiz = (int) Math.sqrt(this.totalMinas());
		
		for (int row = 0; row < raiz; row ++) {
			for (int col = 0; col < raiz; col ++) {
				result += this.minas.get(row).get(col);
			}
			result += "\n";
		}
		
		return result;
	}

}