package tp.pr5.swing;

public interface JugadorSwing {

	/**
	 * Recibe unas coordenadas en donde se espera que el jugador realice un
	 * movimiento
	 * 
	 * @param x
	 * @param y
	 */
	void TableroPulsado(int x, int y);

	/**
	 * Inicia el turno del jugador
	 */
	void RecibeTurno();

	/**
	 * Finaliza el turno del jugador
	 */
	void PierdeTurno();

	/**
	 * Devuelve true si se trata de un jugador humano y false en caso contrario
	 * 
	 * @return
	 */
	boolean esHumano();
}
