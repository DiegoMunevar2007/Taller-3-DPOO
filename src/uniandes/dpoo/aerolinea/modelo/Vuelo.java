package uniandes.dpoo.aerolinea.modelo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class Vuelo {

	private String fecha;
	private Ruta ruta;
	private Avion avion;
	private Map<String,Tiquete> tiquetes;
	public Vuelo(String fecha, Ruta ruta, Avion avion) {
		this.fecha = fecha;
		this.ruta = ruta;
		this.avion = avion;
		this.tiquetes = new HashMap<String,Tiquete>();
	}
	public String getFecha() {
		return fecha;
	}
	public Ruta getRuta() {
		return ruta;
	}
	public Avion getAvion() {
		return avion;
	}
	public ArrayList<Tiquete> getTiquetes() {
		ArrayList<Tiquete>listaTiquetes= new ArrayList<Tiquete>();
		for (String elemento: this.tiquetes.keySet()) {
			listaTiquetes.addLast(this.tiquetes.get(elemento));
		}
		return listaTiquetes;
	}
	
	public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad) {
		
	}
	// TODO: Hacer VenderTiquetes y equals
}
