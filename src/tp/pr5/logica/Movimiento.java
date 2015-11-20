package tp.pr5.logica;

/**
 * Clase que representa el movimiento de un jugador. Tiene un m�todo para
 * ejecutar el movimiento sobre la partida, y otro para deshacerlo. Es una clase
 * abstracta; habr� una clase no abstracta por cada tipo de juego soportado.
 * 
 * @author David Merillas Pinar / Simon Da Silva
 */

public abstract class Movimiento {
	protected int columna;
	protected int fila;
	protected Ficha jugador;

	/**
	 * Constructor:
	 * 
	 * @param x
	 *            Columna donde se colocar� la ficha
	 * @param color
	 *            Color de la ficha que se pondra (o jugador que pone).
	 */
	protected Movimiento(int x, Ficha color) {
		columna = x;
		jugador = color;
		// El atributo fila guarda la fila en la que
		// se ha quedado colocada la ficha
		// UNA VEZ EJECUTADO EL MOVIMIENTO, por defecto se inicializa a 1:
		fila = 1;
	}

	/**
	 * Devuelve el color del jugador al que pertenece el movimiento.
	 * 
	 * @return Color del jugador (coincide con el pasado al constructor).
	 */

	public Ficha getJugador() {
		return jugador;
	}

	/**
	 * Devuelve la columna en donde esta colocada la ficha del movimiento
	 * 
	 * @return Columna
	 */
	public int getColumna() {
		return columna;
	}

	/**
	 * Devuelve la fila en donde esta colocada la ficha del movimiento
	 * 
	 * @return Fila
	 */
	public int getFila() {
		return fila;
	}

	/**
	 * Determina si las coordenadas del movimiento existen dentro de los l�mites
	 * del tablero
	 * 
	 * @param tab
	 *            - Tablero en donde se est� jugando
	 * @return true si el movimiento es valida
	 */

	public boolean posicionValida(Tablero tab) {
		return (columna > 0 && columna <= tab.getAncho());
	}

	/**
	 * Ejecuta el movimiento sobre el tablero que se recibe como parametro. Se
	 * puede dar por cierto que tablero recibido sigue las reglas del tipo de
	 * juego al que pertenece el movimiento. En caso contrario, el
	 * comportamiento es indeterminado.
	 * 
	 * @param tab
	 *            - Tablero sobre el que ejecutar el movimiento
	 * @return true si todo fue bien. Se devuelve false si el movimiento no
	 *         puede ejecutarse sobre el tablero.
	 */

	public boolean ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		boolean movimientoOK = false;

		if (posicionValida(tab)) {
			// Se comprueba si la columna est� llena:
			// La columna NO est� llena:
			if (tab.getCasilla(columna, 1) == Ficha.VACIA) {

				for (int fila = 1; fila <= tab.getAlto() && !movimientoOK; fila++) {
					// Si encuentro una casilla ocupada, meto la nueva ficha en
					// la casilla anterior (una fila mas arriba):
					if (tab.getCasilla(columna, fila) != Ficha.VACIA) {
						tab.setCasilla(columna, fila - 1, jugador);
						this.fila = fila - 1;
						movimientoOK = true;
					}

					// Si llego a la ultima fila de esa columna, y la casilla
					// esta vacia, coloco la ficha ahi:
					else if (fila == tab.getAlto()
							&& tab.getCasilla(columna, fila) == Ficha.VACIA) {
						tab.setCasilla(columna, fila, jugador);
						this.fila = fila;
						movimientoOK = true;
					}
				}
			}
		} else
			throw (new MovimientoInvalido(
					"Columna incorrecta. Debe estar entre 1 y "
							+ tab.getAncho() + "."));

		return movimientoOK;
	}

	/**
	 * Deshace el movimiento en el tablero recibido como parametro. Se puede dar
	 * por cierto que el movimiento se ejecut� sobre ese tablero; en caso
	 * contrario, el comportamiento es indeterminado. Por lo tanto, es de
	 * suponer que el m�todo siempre funcionar� correctamente.
	 * 
	 * @param tab
	 *            - Tablero de donde deshacer el movimiento.
	 */
	public void undo(Tablero tab) {
		tab.setCasilla(columna, fila, Ficha.VACIA);
	}

}