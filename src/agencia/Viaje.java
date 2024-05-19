package agencia;

import exceptions.InvalidFechaException;
import exceptions.InvalidLugarException;
import exceptions.InvalidPrecioException;

/**
 * Clase que representa un viaje de vuelo
 */
public class Viaje {

	/* Fields */
	/**
	 * Destino del viaje
	 */
	private String lugar = "";

	/**
	 * Fecha del viaje
	 */
	private String fecha = "";

	/**
	 * Precio del viaje
	 */
	private double precio;

	/* Constructor */
	/**
	 * Constructor con parámetros
	 * 
	 * @param lugar  Destino del viaje
	 * @param fecha  Fecha del viaje
	 * @param precio Precio del viaje
	 * @throws InvalidLugarException
	 * @throws InvalidFechaException
	 * @throws InvalidPrecioException
	 */
	public Viaje(String lugar, String fecha, double precio)
			throws InvalidLugarException, InvalidFechaException, InvalidPrecioException {
		if (lugar == null || lugar.isBlank())
			throw new InvalidLugarException();
		else
			this.lugar = lugar;

		if (fecha == null || fecha.isBlank())
			throw new InvalidFechaException();
		else
			this.fecha = fecha;

		// Checking Precio para que no sea negativo
		if (precio >= 0)
			this.precio = precio;
		else
			throw new InvalidPrecioException();

	}// Fin Constructor

	/**
	 * Constructor para buscar el objeto
	 * 
	 * @param lugar Destino del viaje
	 */
	public Viaje(String lugar) {
		this.lugar = lugar;
	}

	/* Getters & Setters */
	/**
	 * Getter del Lugar
	 * 
	 * @return this.lugar Destino del viaje
	 */
	public String getLugar() {
		return this.lugar;
	}

	/**
	 * Setter del Lugar
	 * 
	 * @param lugar Destino del viaje
	 * @throws InvalidLugarException 
	 */
	public void setLugar(String lugar) throws InvalidLugarException {
		if (lugar == null || lugar.isBlank())
			throw new InvalidLugarException();
		else
			this.lugar = lugar;
	}

	/**
	 * Getter de la Fecha
	 * 
	 * @return this.fecha Fecha del Viaje
	 */
	public String getFecha() {
		return this.fecha;
	}

	/**
	 * Setter del Lugar
	 * 
	 * @param fecha Fecha del viaje
	 * @throws InvalidFechaException 
	 */
	public void setFecha(String fecha) throws InvalidFechaException {
		if (fecha == null || fecha.isBlank())
			throw new InvalidFechaException();
		else
			this.fecha = fecha;
	}

	/**
	 * Getter del Precio
	 * 
	 * @return this.precio Precio del Viaje
	 */
	public double getPrecio() {
		return this.precio;
	}

	/**
	 * Setter del Precio
	 * 
	 * @param precio Precio del viaje
	 * @throws InvalidPrecioException 
	 */
	public void setPrecio(double precio) throws InvalidPrecioException {
		if (precio >= 0)
			this.precio = precio;
		else
			throw new InvalidPrecioException();
	}

	/* Métodos */
	/**
	 * Método para pasar el viaje a String
	 * 
	 * return string Cadeneta
	 */
	@Override
	public String toString() {
		return " %-20s | %8s | %7.2f €".formatted(this.lugar, this.fecha, this.precio);
		// return "Lugar: " + this.lugar + "\t | Fecha: " + this.fecha + "\t | Precio: "
		// + precio;
	}

	/**
	 * Método para pasar el viaje a modelo fichero
	 * 
	 * return string Cadeneta
	 */
	public String toFileString() {
		return this.lugar + "::" + this.fecha + "::" + precio;
	}

	/**
	 * Compara dos objetos de tipo Viaje
	 * 
	 * @returns Devuelve true si los viajes comparados van al mismo lugar, false en
	 *          caso contrario
	 */
	@Override
	public boolean equals(Object obj) {
		boolean res = false;

		if (obj instanceof Viaje v) {
			res = this.lugar.equals(v.lugar);
		}

		return res;
	}
}
