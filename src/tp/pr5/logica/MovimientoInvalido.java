package tp.pr5.logica;

/**
 * Excepción generada cuando se intenta ejecutar un movimiento incorrecto.
 * 
 * @author David Merillas Pinar / Simon Da Silva
 */
public class MovimientoInvalido extends java.lang.Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor sin par�metros.
	 */
	public MovimientoInvalido() {
	}

	/**
	 * Constructor con un par�metro para el mensaje.
	 * 
	 * @param msg
	 */
	public MovimientoInvalido(java.lang.String msg) {
		super(msg);
	}

	/**
	 * Constructor con un par�metro para el mensaje y otro para la causa.
	 * 
	 * @param msg
	 * @param arg
	 */
	public MovimientoInvalido(java.lang.String msg, java.lang.Throwable arg) {
	}

	/**
	 * Constructor con un par�metro para la causa inicial que provoc� la
	 * excepci�n.
	 * 
	 * @param arg
	 */
	public MovimientoInvalido(java.lang.Throwable arg) {
	}

}
