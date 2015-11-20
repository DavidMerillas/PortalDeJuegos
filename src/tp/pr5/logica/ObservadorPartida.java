package tp.pr5.logica;

/**
 * Describe las notificaciones que el modelo enviará a los observadores (por
 * ejemplo: las vistas) cuando el estado cambie. Todo el que quiera observar
 * cambios en el modelo deberá implementar esta interface.
 */
public interface ObservadorPartida {

	/**
	 * Estos m�todos ser�n llamados por el controlador para enviarle las
	 * correspondientes notificaciones. La(s) vista(s) reaccionar�n de acuerdo a
	 * estas notificaciones.
	 */

	// Gesti�n de partida:
	/**
	 * M�todo invocado por la clase Partida que permite notificar a sus
	 * observadores( las vistas) que se ha reiniciado la partida. Proporciona
	 * informaci�n del estado inicial del tablero y el turno (que ser� una ficha
	 * blanca o negra).
	 * 
	 * @param tablero
	 * @param turno
	 */
	public void onReset(TableroInmutable tablero, Ficha turno);

	// Partida terminada (Se distinguen los dos casos):
	/**
	 * La partida notifica a los observadores que ha terminado la partida
	 * llamando a este m�todo. Adem�s proporciona al observador una vista del
	 * tablero de s�lo lectura y el ganador.
	 * 
	 * @param tablero
	 * @param ganador
	 * */
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador);

	/**
	 * La partida notifica a los observadores que se ha cambiado el juego. Se
	 * proporciona el estado inicial del tablero y el turno
	 * 
	 * @param tablero
	 * @param turno
	 */
	public void onCambioJuego(TableroInmutable tablero, Ficha turno);

	// Gesti�n de movimientos:
	/**
	 * La partida notifica a los observadores que una operaci�n deshacer no ha
	 * tenido �xito porque no se puede deshacer.
	 * 
	 * @param tablero
	 * @param turno
	 */
	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno);

	/**
	 * La partida notifica a los observadores que se ha deshecho un movimiento.
	 * Adem�s, proporciona el estado final del tablero, el turno del siguiente
	 * jugador y si hay m�s movimientos a deshacer o no.
	 * 
	 * @param tablero
	 * @param turno
	 * @param HayMas
	 */
	public void onUndo(TableroInmutable tablero, Ficha turno, boolean HayMas);

	/**
	 * La partida notifica a los observadores que se ha terminado de realizar un
	 * movimiento. Se proporciona adem�s una vista del tablero de s�lo lectura,
	 * el jugador que ha jugado, y el turno del siguiente jugador.
	 * 
	 * @param tablero
	 * @param jugador
	 * @param turno
	 */
	public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador,
			Ficha turno);

	/**
	 * La partida notifica que se ha producido un movimiento incorrecto
	 * proporcionando el objeto MovimientoInvalido con una explicaci�n del
	 * problema que se ha producido.
	 * 
	 * @param movimientoException
	 */
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException);
}
