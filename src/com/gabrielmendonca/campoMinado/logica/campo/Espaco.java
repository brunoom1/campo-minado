package com.gabrielmendonca.campoMinado.logica.campo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Espaco extends Point {

	private static final long serialVersionUID = 4261951146815198970L;
	
	private Object objeto = null;
	private List<Espaco> proximos = null;
	
	
	public static final byte ESTATUS_INEXPLORADO = 0;
	public static final byte ESTATUS_EXPLORADO = 1;
	
	/**
	 * Define estatus do espaço, se ele foi explorado ou não
	 * por padrão, o espaço não foi explorado
	 */
	private byte estatus = ESTATUS_EXPLORADO;
	
	public Espaco () {
		super(0, 0);
		proximos = new ArrayList<>();
	}
	
	public Espaco (int x, int y) {
		super(x, y);
	}
	
	public byte getEstado () {
		return estatus;
	}
	
	public Espaco setEstado(byte estatus) {
		this.estatus = estatus;
		return this;
	}
	
	public boolean foiExplorado () {
		return this.estatus == ESTATUS_EXPLORADO;
	}
	
	public List<Espaco> getProximos () {
		return this.proximos;
	}
	
	public boolean temProximos () {
		return this.proximos != null && this.proximos.size() > 0;
	}
	
	public Espaco setProximos(List<Espaco> proximos) {
		this.proximos = proximos;
		return this;
	}
		
	public boolean estaVasio () {
		return objeto == null;
	}
	
	public Object getObjeto () {
		return objeto;
	}
	
	public Espaco setObjeto (Object objeto) {
		this.objeto = objeto;
		return this;
	}
	
	public String toString () {
		
		if (!foiExplorado()) {
			return "⸪|";
		}
		
		if (this.estaVasio()) {
			return "_|";
		}
		return this.getObjeto().toString();
	}
}
