package exceptions;

/**
 * Excepcion lanzada al introducir una fecha invalida
 */
public class InvalidFechaException extends Exception {
	@Override
	public String toString() {
		return "Fecha invalida";
	}
}
