package exceptions;

/**
 * Excepcion lanzada al introducir un precio invalido
 */
public class InvalidPrecioException extends Exception {
	@Override
	public String toString() {
		return "Precio Invalido";
	}
}
