package agencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import exceptions.InvalidFechaException;
import exceptions.InvalidLugarException;
import exceptions.InvalidPrecioException;

/**
 * Clase que representa a la agencia de viajes. Contiene la lista de viajes y la
 * ruta donde se guarda en disco la información del programa en un fichero
 */
public class Agencia {
	/**
	 * Lista de viajes de la agencia
	 */
	private ArrayList<Viaje> viajes = new ArrayList<>();

	/**
	 * Ruta donde guardar los datos del programa en un fichero
	 */
	private String ruta;

	/**
	 * Constructor
	 * 
	 * @param ruta Ruta donde guardar el fichero con los datos del programa
	 */
	public Agencia(String ruta) {
		// Comprobar que la ruta no sea inválida
		if (ruta != null && !ruta.isBlank()) {
			// Referenciar un fichero con esa ruta
			File f = new File(ruta);

			this.ruta = ruta;

			// Si el fichero existe
			if (f.exists()) {
				// Leer el fichero
				leerViajes(f);
			} else {
				System.out.println("El archivo no existe");
			}
		}

	}

	/**
	 * Imprime todos los viajes disponibles
	 */
	public void verViajesDisponibles() {
		System.out.printf("      %10s      | %8s   |  %s\n", "Destino", "Fecha", "Precio");
		System.out.println("----------------------------------------------");
		for (Viaje viaje : viajes) {
			System.out.println(viaje);
		}
		System.out.println();
	}

	/**
	 * Añade un viaje a la lista de viajes disponibles de la agencia
	 * 
	 * @param viaje Viaje para añadir
	 */
	public void agregarViaje(Viaje viaje) {
		viajes.add(viaje);
	}

	/**
	 * Busca un viaje en la agencia
	 * 
	 * @param lugar Destino del viaje
	 * @return Develve la posicion del viaje, devuelve -1 si el viaje no se
	 *         encuenetra en la lista
	 */
	public int buscarViaje(String lugar) {
		Viaje v = new Viaje(lugar);

		return viajes.indexOf(v);
	}

	/**
	 * Modificamos los detalles de un viaje existente en la lista de viajes de la
	 * agencia.
	 *
	 * @param lugar  El destino del viaje.
	 * @param fecha  La fecha del viaje.
	 * @param precio El precio del viaje.
	 * @return Devuelve true si se ha podido modificar el viaje, false si no se
	 *         encontró
	 */
	public boolean modificarViaje(String lugar, String fecha, double precio) {
		// Variable que nos devolvera false o true dependiendo de si se elimina el viaje
		// o no.
		boolean resultado = false;

		// Obtenemos la posición del viaje.
		int posicion = buscarViaje(lugar);
		// Comprobamos si la posición existe en el array, si es distinta a -1, significa
		// que existe.
		if (posicion != -1) {
			// Obtenemos el objeto viaje y actualizamos lugar, fecha y precio.
			Viaje viaje = viajes.get(posicion);
			
			try {
				viaje.setLugar(lugar);
			} catch (InvalidLugarException e) {
				System.out.println(e);
			}
			
			try {
				viaje.setFecha(fecha);
			} catch (InvalidFechaException e) {
				System.out.println(e);
			}
			
			try {
				viaje.setPrecio(precio);
			} catch (InvalidPrecioException e) {
				System.out.println(e);
			}
			
			resultado = true;
		}

		return resultado;
	}

	/**
	 * Eliminamos un viaje de la lista basandonos en el lugar.
	 *
	 * @param lugar El lugar del viaje que queremos eliminar.
	 * @return true si se eliminó correctamente el viaje, false si no se encontró
	 *         ningún viaje.
	 */
	public boolean eliminarViaje(String lugar) {
		// Variable que nos devolvera false o true dependiendo de si se elimina el viaje
		// o no.
		boolean resultado = false;

		// Obtiene la posición de la lista, retornada por la función buscarViaje
		int posicion = buscarViaje(lugar);

		// Comprobamos si la posición existe en el array, si es distinta a -1, significa
		// que existe.
		if (posicion != -1) {
			// Si se encuentra el viaje, lo eliminamos de la lista.
			viajes.remove(posicion);

			// Establecemos resultado como true, indicando que el viaje fue eliminado.
			resultado = true;
		}

		// Devuelve el resultado.
		return resultado;
	}

	/**
	 * Cargar los viajes a la ram
	 * 
	 * @param file Archivo de donde leer los viajes
	 */
	private void leerViajes(File file) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String linea;
			String datos[];

			while ((linea = reader.readLine()) != null) {
				datos = linea.split("::");
				try {
					this.viajes.add(new Viaje(datos[0], datos[1], Double.parseDouble(datos[2])));
				} catch (NumberFormatException | InvalidPrecioException e) {
					System.out.println(e);
				} catch (InvalidLugarException e) {
					System.out.println(e);
				} catch (InvalidFechaException e) {
					System.out.println(e);
				}
			}

		} catch (IOException e) {
			System.out.println("Error al leer el archivo: " + e.getMessage());
		}
	}

	/**
	 * Método para guardar y sobreescribir los datos
	 */
	public void guardarViajes() {

		/* Variables */
		/* Cadena donde guardar las líneas que forman cada viaje */
		String linea = "";

		// Try catch para crear un buffered writer con la ruta guardada
		try {
			BufferedWriter in = new BufferedWriter(new FileWriter(this.ruta));

			// Sobreescritura del archivo mediante un bucle que recorre el arraylist
			for (int i = 0; i < this.viajes.size(); i++) {

				// Tomamos la linea del viaje
				linea = viajes.get(i).toFileString();

				// Actual writing
				// First we overwrite the first line
				if (i == 0) {

					in.write(linea);

				} else { // Appending of next lines

					in.append(linea);

				}
				// Avoiding last newLine and adding nweline after each line
				if (i != viajes.size() - 1) {
					in.newLine();
				} // Fin IF Avoiding last newLine

			} // Fin FOR escribir

			// Actual saving
			in.close();

		} catch (IOException e) {
			System.out.println("Error al escribir en el archivo " + e.getMessage());
		}

	}// Fin saveData()
}
