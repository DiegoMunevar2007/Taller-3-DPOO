package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.List;

import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public abstract class Cliente {
	private Tiquete[] tiquetesSinUsar;
	private Tiquete[] tiquetesUsados ;
	public Cliente() {
		this.tiquetesSinUsar= new Tiquete[1];
		this.tiquetesUsados = new Tiquete[1];
	}
	public abstract String getTipoCliente();
	public abstract String getIdentificador();
}
