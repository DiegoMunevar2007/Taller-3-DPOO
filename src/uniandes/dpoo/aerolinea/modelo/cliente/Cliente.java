package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.ArrayList;

import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public abstract class Cliente {
	protected ArrayList<Tiquete> tiquetesSinUsar;
	protected ArrayList<Tiquete> tiquetesUsados;
	public Cliente() {
		this.tiquetesSinUsar= new ArrayList<Tiquete>();
		this.tiquetesUsados = new ArrayList<Tiquete>();
	}	
	public abstract String getTipoCliente();
	public abstract String getIdentificador();
	public void agregarTiquete(Tiquete tiquete) {
		tiquetesSinUsar.add(tiquete);
	}
	public int calcularValorTiquetes() {
		
	}
}
