package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.List;

import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public abstract class Cliente {
	protected Tiquete[] tiquetesSinUsar;
	protected Tiquete[] tiquetesUsados ;
	public Cliente() {
		this.tiquetesSinUsar= new Tiquete[1];
		this.tiquetesUsados = new Tiquete[1];
	}	
	public abstract String getTipoCliente();
	public abstract String getIdentificador();
	public void agregarTiquete(Tiquete tiquete) {
		tiquetesSinUsar[0]=tiquete;
	}
	public int calcularValorTiquetes() {
		
	}
}
