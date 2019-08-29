package com.gabrielmendonca.campoMinado;

import com.gabrielmendonca.campoMinado.logica.Mina;
import com.gabrielmendonca.campoMinado.logica.campo.Campo;

public class CampoMinado {

	Campo campo;
	int maximoMinas = 20; // valor em porcentagem

	int linhas = 40;
	int colunas = 40;
	int totalMinas = 0;

	public CampoMinado (int linhas, int colunas) {
		this.linhas = linhas;
		this.colunas = colunas;
		int size = linhas * colunas;
		totalMinas = Math.round(size * maximoMinas / 100);
	}

	
	public CampoMinado init () {		
		this.campo = new Campo (linhas, colunas);
		
		int i = 0;
		
		try {
			while (i < totalMinas) {
				this.campo.addObject(new Mina(), true);
				i++;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		return this;		
	}
	
	public int getTotalMinas () {
		return totalMinas;
	}
	
	public String toString () {
		
		String str = "-------- CAMPO MINADO --------\n";
		
		str += "M(" + this.getTotalMinas() + ")";
		
		str += "\n";
		str += this.campo;
		
		return str;
	}
	
}