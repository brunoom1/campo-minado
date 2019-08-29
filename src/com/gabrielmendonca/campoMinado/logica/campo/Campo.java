package com.gabrielmendonca.campoMinado.logica.campo;

import java.util.ArrayList;
import java.util.List;


public class Campo {
	
	int lines;
	int columns;
	

	/**
	 * Lista de espaços
	 */
	List<ArrayList<Espaco>> espacos;
	
	/**
	 * Lista de espaços, lista sequencial
	 */
	List<Espaco> linha_espaco = null;
	
	/**
	 * Lista de espaços vazios
	 */
	List<Espaco> lista_espacos_vazios = null;

	
	/**
	 * Constroi novo campo de linhas vs colunas
	 * @param lines
	 * @param columns
	 */
	
	public Campo (int lines, int columns) {
		this.lines = lines;
		this.columns = columns;
		
		espacos = new ArrayList<ArrayList<Espaco>>();
		linha_espaco = new ArrayList<Espaco>();
		lista_espacos_vazios = new ArrayList<Espaco>();

		for (int lin = 0; lin < lines; lin ++) {
			for (int col = 0; col < columns; col ++) {
				if (col == 0) {
					espacos.add(new ArrayList<Espaco>());
				}
				Espaco espaco = new Espaco(col, lin);
				espacos.get(lin).add(espaco);
				linha_espaco.add(espaco);
				lista_espacos_vazios.add(espaco);			
			}
		}
	}
	
	
	
	/**
	 * Busca espaços do campo no formato linha e coluna
	 * 
	 * @return ArrayList<ArrayList<Espaco>>
	 */
	public List<ArrayList<Espaco>> getEspacos () {
		return espacos;
	}
	
	/**
	 * Retorna lista de espacos vazios 
	 * 
	 * @return
	 */
	public List<Espaco> getEspacosVazios () {
		return lista_espacos_vazios;
	}
	
	
	/**
	 * Adiciona objeto a um espaço vazio de maneira sequencial
	 * 
	 * @param mina
	 * @return
	 */
	public Campo addObject(Object obj) throws Exception {
		
		List<Espaco> espacos = this.getEspacosVazios();
		
		if (espacos.size() > 0) {
			Espaco espaco = espacos.get(0);
			espacos.remove(0);
			espaco.setObjeto(obj);
		} else {
			throw new Exception();
		}
		
		return this;
	}
	
	
	
	public Campo addObject(Object obj, boolean aleatorio) throws Exception {
		
		if (!aleatorio) {
			this.addObject(obj);
			return this;
		} 
		
		List<Espaco> espacos = this.getEspacosVazios();
		
		if (espacos.size() > 0) {
			int pos = (int) Math.round(Math.random() * espacos.size());

			Espaco espaco = espacos.get(pos);
			espacos.remove(espaco);
			espaco.setObjeto(obj);
		} else {
			throw new Exception ();
		}
		
		return this;
	}
	
	/**
	 * Busca espaços de forma sequencial
	 * 
	 * @return List<Espaco> espacos
	 */
	
	public List<Espaco> getEspacosEmLinha () {
		return linha_espaco;
	}
	
	
	/** 
	 * Retorna quantidade total de itens do campo
	 * 
	 * @return int
	 */
	public int size () {
		return espacos.size() * espacos.size();
	}
	
	/**
	 * Retorna espaços próximos a um espaço passado por parâmetro
	 * @param espaco
	 * @return List<Espaco>
	 */
	public List<Espaco> espacosProximos (Espaco espaco) {
		List<Espaco> proximos = new ArrayList<Espaco>();
		
		for (int lin = -1; lin < 2; lin += 1) {
			for (int col = -1; col < 2; col += 1) {

				
				if (lin == 0 && col == 0) continue;

				int x = (int) espaco.getX();
				int y = (int) espaco.getY();

				try {
					Espaco espacoProximo = this.getEspacos().get(y + lin).get(x + col);
					proximos.add(espacoProximo);
				} catch (IndexOutOfBoundsException e) {}
			}
		}
		
		return proximos;
	}
	
	/** 
	 * Mostra campo no modo texto	
	 */
	
	public String toString () {
		String str = "";
		
		char colChar = 'A';
		
		for (List<Espaco> lin: this.getEspacos()) {
			for (Espaco e: lin) {
				if (e.x == 0) {
					str += colChar++ + " |";
				}
				
				if (!e.foiExplorado()) {
					str += e;
				} else if (e.estaVasio()) {
					
					List<Espaco> espacosProximos = null;					
					
					if (!e.temProximos()) {
						espacosProximos = this.espacosProximos(e);						
						e.setProximos(espacosProximos);
					} else {
						espacosProximos = e.getProximos();
					}
					
					
					/* 
					 * conta espacos próximos que não estão 
					 * vasios
					 */
					
					int count = 0;
					for (Espaco proximo: espacosProximos) {
						if (!proximo.estaVasio()) {
							count += 1;
						}
					}
					
					if (count > 0) {
						str += count + "|";
					} else {
						str += "_|";						
					}
				} else {
					str += e;					
				}
			}
			str += "\n";
		}
		
		str += "  ";
		for (int i = 0; i < columns; i ++) {
			str += " " + i;
		}
		
		return str;
	}

}

