package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.List;

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
		tiquetesSinUsar[0]=tiquete;
	}
	public int calcularValorTiquetes() {
		
	}
}
