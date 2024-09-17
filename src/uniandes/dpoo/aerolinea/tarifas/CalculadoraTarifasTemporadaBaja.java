package uniandes.dpoo.aerolinea.tarifas;

import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas {
	protected final int COSTO_POR_KM_NATURAL = 600;
	protected final int COSTO_POR_KM_CORPORATIVO = 900;
	protected final double DESCUENTO_PEQ = 0.02;
	protected final double DESCUENTO_MEDIANAS = 0.1;
	protected final double DESCUENTO_GRANDES = 0.2;
	@Override
	protected int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
		// TODO Auto-generated method stub
		Ruta rutaVuelo=vuelo.getRuta();
		return Aeropuerto.calcularDistancia(rutaVuelo.getOrigen(), rutaVuelo.getDestino())*COSTO_POR_KM;
	}

	@Override
	protected int calcularPorcentajeDescuento(Cliente cliente) {
		// TODO Auto-generated method stub
		String tipoCliente = cliente.getTipoCliente();
		if (tipoCliente.equals("Corporativo")) {
			ClienteCorporativo clienteCorp= (ClienteCorporativo) cliente;
			if (clienteCorp.getTamanoEmpresa().equals("") {
				
			}
			
		}
		return 0;
	}

}
