package uniandes.dpoo.aerolinea.tarifas;

import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public abstract class CalculadoraTarifas {
	public static final double IMPUESTO = 0.28;
	
	public int calcularTarifa(Vuelo vuelo, Cliente cliente) {
		
	}
	protected abstract int calcularCostoBase(Vuelo vuelo, Cliente cliente);
	protected abstract int calcularPorcentajeDescuento(Vuelo vuelo, Cliente cliente);
	protected int calcularDistanciaVuelo(Ruta ruta) {
		Aeropuerto origen= ruta.getOrigen();
		Aeropuerto destino= ruta.getDestino();
		return Aeropuerto.calcularDistancia(origen, destino);
	}
	protected int calcularValorImpuestos(int costoBase) {
		return (int)((int)costoBase*IMPUESTO);
	}
}
