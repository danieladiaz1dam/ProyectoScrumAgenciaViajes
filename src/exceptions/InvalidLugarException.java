package exceptions;

/**
 * Excepcion lanzada al introducir un lugar invalido
 */
public class InvalidLugarException extends Exception {
	@Override
	public String toString() {
		return "Lugar invalido";
	}
}
