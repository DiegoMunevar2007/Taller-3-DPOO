package uniandes.dpoo.aerolinea.consola;

import java.io.IOException;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Avion;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteNatural;
import uniandes.dpoo.aerolinea.persistencia.CentralPersistencia;
import uniandes.dpoo.aerolinea.persistencia.TipoInvalidoException;

public class ConsolaAerolinea extends ConsolaBasica {
    private Aerolinea unaAerolinea;
    private static String DIRECTORIO = System.getProperty("user.dir") + "\\datos\\";

    /**
     * Es un método que corre la aplicación y realmente no hace nada interesante:
     * sólo muestra cómo se podría utilizar la clase Aerolínea para hacer pruebas.
     */
    public void correrAplicacion() {
        unaAerolinea = new Aerolinea();
        menu();
    }

    private void menu() {
        try {
            // String archivo = this.pedirCadenaAlUsuario( "Digite el nombre del archivo
            // json con la información de una aerolinea" );
            String[] opcionesInicio = { "Cargar un/unos JSON de aerolineas", "Programar un vuelo","Crear un nuevo Cliente", "Guardar",
                    "Vender tiquetes",
                    "Registrar vuelo como realizado", "Consultar saldo pendiente de cliente", "Salir" };

            boolean finalizado = false;
            while (finalizado == false) {
                int respuesta = mostrarMenu("Inicio", opcionesInicio);

                if (respuesta == 1) {
                    int respuestaOpcion1 = mostrarMenu("Opciones",
                            new String[] { "Cargar JSON de informacion de aerolineas",
                                    "Cargar JSON de informacion de clientes y tiquetes", "Cargar ambos" });
                    if (respuestaOpcion1 == 1) {
                        String archivo = this.pedirCadenaAlUsuario(
                                "Digite el nombre del archivo json con la información de una aerolinea");
                        unaAerolinea.cargarAerolinea("./datos/" + archivo + ".json", CentralPersistencia.JSON);
                        System.out.println("Aerolinea cargada exitosamente");
                    } else if (respuestaOpcion1 == 2) {
                        String archivo = this.pedirCadenaAlUsuario(
                                "Digite el nombre del archivo json con la información de los tiquetes");
                        unaAerolinea.cargarTiquetes("./datos/" + archivo + ".json", CentralPersistencia.JSON);
                        System.out.println("Tiquetes cargados exitosamente");
                    } else if (respuestaOpcion1 == 3) {
                        String archivo = this.pedirCadenaAlUsuario(
                                "Digite el nombre del archivo json con la información de una aerolinea");
                        unaAerolinea.cargarAerolinea("./datos/" + archivo + ".json", CentralPersistencia.JSON);
                        System.out.println("Aerolinea cargada exitosamente");
                        archivo = this.pedirCadenaAlUsuario(
                                "Digite el nombre del archivo json con la información de los tiquetes");
                        unaAerolinea.cargarTiquetes("./datos/" + archivo + ".json", CentralPersistencia.JSON);
                        System.out.println("Tiquetes cargados exitosamente");
                    }

                } else if (respuesta == 2) {
                    boolean termino = false;
                    int contador = 1;
                    while (termino == false) {
                        System.out.println("Vuelo #" + Integer.toString(contador));
                        String fecha = pedirCadenaAlUsuario("Ingrese una fecha en formato YYYY-MM-DD");
                        Ruta ruta = agregarRuta();
                        Avion avion = agregarAvion();
                        try {
                            unaAerolinea.programarVuelo(fecha, ruta.getCodigoRuta(), avion.getNombre());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        boolean confirmacion = pedirConfirmacionAlUsuario("¿Desea agregar otro vuelo?");
                        if (confirmacion == false) {
                            termino = true;
                        }
                        contador++;
                    }
                    respuesta = 0;

                } else if (respuesta == 3) {
                    String nombre = pedirCadenaAlUsuario("Ingrese el nombre del cliente");
                    boolean valido = false;
                    while (valido == false) {
                        String tipoCliente = pedirCadenaAlUsuario("Ingrese el tipo de cliente (Natural o Corporativo)");
                        if (tipoCliente.equals("Natural") || tipoCliente.equals("Corporativo")) {
                            valido = true;
                            try {
                                Cliente cliente = null;
                                if (tipoCliente.equals("Natural")) {
                                    cliente = new ClienteNatural(nombre);
                                } else {
                                    int tamano = pedirEnteroAlUsuario("Ingrese el tamaño de la empresa");
                                    cliente = new ClienteCorporativo(nombre, tamano);
                                }
                                unaAerolinea.agregarCliente(cliente);
                                System.out.println("Cliente agregado exitosamente");
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        } else {
                            System.out.println("Tipo de cliente no valido");
                        }
                    }
                }

                else if (respuesta == 4) {
                    int respuestaOpcion3 = mostrarMenu("Opciones",
                            new String[] { "Guardar JSON de informacion de aerolineas",
                                    "Cargar JSON de informacion de clientes y tiquetes", "Cargar ambos" });
                    if (respuestaOpcion3 == 1) {
                        try {
                            String archivo = pedirCadenaAlUsuario("Ingrese el nombre del archivo de vuelos a guardar");
                            unaAerolinea.salvarAerolinea(DIRECTORIO + archivo + ".json", CentralPersistencia.JSON);
                            System.out.println("Aerolinea guardada exitosamente");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (respuestaOpcion3 == 2) {
                        try {
                            String archivo = pedirCadenaAlUsuario(
                                    "Ingrese el nombre del archivo de Tiquetes a guardar");
                            unaAerolinea.salvarTiquetes(DIRECTORIO + archivo + ".json", CentralPersistencia.JSON);
                            System.out.println("Tiquetes guardados exitosamente");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (respuestaOpcion3 == 3) {
                        try {
                            String archivo = pedirCadenaAlUsuario("Ingrese el nombre del archivo de vuelos a guardar");
                            unaAerolinea.salvarAerolinea(DIRECTORIO + archivo + ".json", CentralPersistencia.JSON);
                            System.out.println("Aerolinea guardada exitosamente");
                            archivo = pedirCadenaAlUsuario("Ingrese el nombre del archivo de Tiquetes a guardar");
                            unaAerolinea.salvarTiquetes(DIRECTORIO + archivo + ".json", CentralPersistencia.JSON);
                            System.out.println("Tiquetes guardados exitosamente");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }

                } else if (respuesta == 5) {
                    String idCliente = pedirCadenaAlUsuario("Ingrese el ID del Cliente");
                    String fecha = pedirCadenaAlUsuario("Ingrese una fecha en formato YYYY-MM-DD");
                    String ruta = pedirCadenaAlUsuario("Ingrese el codigo de la ruta a tomar");
                    int cantidad = pedirEnteroAlUsuario("Ingrese una cantidad de tiquetes a comprar");
                    try {
                        int precioTotal = unaAerolinea.venderTiquetes(idCliente, fecha, ruta, cantidad);
                        System.out.println("El precio total a pagar es de: " + Integer.toString(precioTotal));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else if (respuesta == 6) {
                    String fecha = pedirCadenaAlUsuario("Ingrese una fecha en formato YYYY-MM-DD");
                    String codigoRuta = pedirCadenaAlUsuario("Ingrese el codigo de la ruta");
                    try {
                        unaAerolinea.registrarVueloRealizado(fecha, codigoRuta);
                        System.out.println("Vuelo registrado como realizado");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else if (respuesta == 7) {
                    String idCliente = pedirCadenaAlUsuario("Ingrese el ID del Cliente");
                    String saldo = unaAerolinea.consultarSaldoPendienteCliente(idCliente);
                    System.out.println("El saldo pendiente del cliente es de: " + saldo);
                }

                else if (respuesta == 8) {
                    finalizado = true;
                }
            }
        } catch (TipoInvalidoException e) {
            e.printStackTrace();
        } catch (InformacionInconsistenteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Avion agregarAvion() {
        System.out.println("-----------Avion----------");
        String nombre = pedirCadenaAlUsuario("Ingrese el nombre del avion");
        int capacidad = pedirEnteroAlUsuario("Ingrese la capacidad del avion");
        Avion avion = new Avion(nombre, capacidad);
        unaAerolinea.agregarAvion(avion);
        return avion;
    }

    private Ruta agregarRuta() {
        System.out.println("-------Ruta-----");
        Ruta ruta = null;
        String codigo = pedirCadenaAlUsuario("Ingrese el codigo de la ruta");
        if (unaAerolinea.getRuta(codigo) == null) {
            System.out.println("------Aeropuerto de origen-----");
            Aeropuerto origen = agregarAeropuerto();
            System.out.println("------Aeropuerto de destino-----");
            Aeropuerto destino = agregarAeropuerto();
            String horaSalida = pedirCadenaAlUsuario("Ingrese la hora de salida de la ruta sin ':' ");
            String horaLlegada = pedirCadenaAlUsuario("Ingrese la hora de llegada de la ruta sin ':' ");

            ruta = new Ruta(origen, destino, horaSalida, horaLlegada, codigo);
            unaAerolinea.agregarRuta(ruta);

        } else {
            System.out.println("Codigo de ruta ya existe");
            agregarRuta();
        }
        return ruta;
    }

    private Aeropuerto agregarAeropuerto() {
        System.out.println("-------Aeropuerto-----");
        String codigo = pedirCadenaAlUsuario("Ingrese el codigo del aeropuerto");
        if (unaAerolinea.existeAeropuerto(codigo) == true) {
            System.out.println("Aeropuerto " + unaAerolinea.getAeropuerto(codigo).getNombre() + " encontrado");
            return unaAerolinea.getAeropuerto(codigo);
        } else {
            String nombre = pedirCadenaAlUsuario("Ingrese el nombre del aeropuerto");

            String nombreCiudad = pedirCadenaAlUsuario("Ingrese el nombre de la ciudad");
            double latitud = pedirNumeroAlUsuario("Ingrese la latitud del aeropuerto");
            double longitud = pedirNumeroAlUsuario("Ingrese la longitud del aeropuerto");
            Aeropuerto aeropuerto = new Aeropuerto(nombre, codigo, nombreCiudad, latitud, longitud);
            unaAerolinea.agregarAeropuerto(aeropuerto);
            return aeropuerto;
        }

    }

    public static void main(String[] args) {
        ConsolaAerolinea ca = new ConsolaAerolinea();
        ca.correrAplicacion();
        System.exit(0);
    }
}
