package tp.pr5.logica;

import java.util.ArrayList;

public class MovimientoReversi extends Movimiento {

	// En este arraylist se guardaran las coordenadas de todas las fichas
	// de color contrario que este movimiento flanquearía en caso de ser
	// ejecutado:
	private ArrayList<Punto> fichasFlanqueadas;

	/**
	 * Arrays con los deltas de movimiento
	 */
	protected final int[] dX = { 0, 1, 1, 1, 0, -1, -1, -1 };
	protected final int[] dY = { -1, -1, 0, 1, 1, 1, 0, -1 };

	// Guarda el color contrario el jugador del movimiento,
	// ya que será util en varios momentos
	Ficha colorContrario;

	public MovimientoReversi(int x, int y, Ficha color) {
		super(x, color);
		fila = y;
		fichasFlanqueadas = new ArrayList<>();
		colorContrario = calculaColorContrario();
	}

	public ArrayList<Punto> getFichasFlanqueadas() {
		return fichasFlanqueadas;
	}

	@Override
	public boolean posicionValida(Tablero tab) {
		return (columna >= 1 && columna <= tab.getAncho() && fila >= 1 && fila <= tab
				.getAlto());
	}

	@Override
	public boolean ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		boolean MovimientoOK = false;

		if (posicionValida(tab)) {
			if (tab.getCasilla(columna, fila) == Ficha.VACIA) {

				boolean parar = false;
				int i = 0;

				while (!parar && i < tab.getMovimientosValidos().size()) {

					// Se guarda en la variable "mov" el movimiento con indice i
					// del array de movimientos validos del tablero
					MovimientoReversi mov = (MovimientoReversi) tab
							.getMovimientosValidos().get(i);

					// Si el movimiento que intenta realizar el jugador coincide
					// con uno
					// de los guardados en el array de movimientos validos
					// calculados anteriormente:
					// Se detiene el recorrido por el array, se ejecuta el
					// movimiento y se da por valido
					if (this.equals(mov)) {
						parar = true;
						tab.setCasilla(columna, fila, jugador);
						MovimientoOK = true;

						// Se hace una copia del array de fichas flanqueadas del
						// movimiento valido del tablero al propio
						// movimiento ejecutado por el jugador, que será
						// posteriormente guardado en la pila de movimientos de
						// la partida:
						fichasFlanqueadas.addAll(mov.getFichasFlanqueadas());

						// Finalmente, se "voltean" (cambian de color) todas las
						// fichas flanqueadas por este movimiento:
						for (Punto p : fichasFlanqueadas) {
							tab.setCasilla(p.getX(), p.getY(), jugador);
						}

					}

					i++;
				}
				if (!MovimientoOK)
					throw (new MovimientoInvalido("Movimiento no válido."));
			} else
				throw (new MovimientoInvalido("Casilla ocupada."));
		} else
			throw (new MovimientoInvalido("Posición incorrecta."));
		return MovimientoOK;
	}

	/**
	 * Método que busca, en todos las direcciones posibles, si este movimiento
	 * causa "un reversi" (flanquea fichas contrarias):
	 * 
	 */
	protected boolean buscaReversi(Tablero tab) {
		boolean hayReversi = false;

		// Se busca un movimiento válido de Reversi en todas las
		// direcciones posibles
		// (Sentido horario empezando desde la casilla superior):

		for (int i = 0; i < dX.length; i++)
			if (hayReversi(dX[i], dY[i], tab))
				hayReversi = true;

		return hayReversi;
	}

	/**
	 * Comprueba si el movimiento encierra fichas del color contrario entre dos
	 * del color propio (del jugador que hace el movimiento)
	 */
	private boolean hayReversi(int dx, int dy, Tablero tab) {
		boolean hayReversi = false;
		int col = columna + dx;
		int fil = fila + dy;

		boolean parar = false;

		if (tab.getCasilla(col, fil) == colorContrario) {
			// Estas variables contaran las casillas de distancia que hay en
			// cada eje entre la ficha
			// que se está colocando y la siguiente ficha del mismo color
			int distX = 0 + Math.abs(dx);
			int distY = 0 + Math.abs(dy);

			while (!parar) {
				if (tab.getCasilla(col + dx, fil + dy) == Ficha.VACIA)
					parar = true;

				else if (tab.getCasilla(col + dx, fil + dy) == jugador) {
					// El movimiento es válido (flanquea fichas contrarias)
					hayReversi = true;
					guardaCoordenadas(mayorDeDos(distX, distY), dx, dy, tab);
					parar = true;
				}

				else {
					col += dx;
					fil += dy;
					distX += Math.abs(dx);
					distY += Math.abs(dy);
				}
			}
		}
		return hayReversi;
	}

	@Override
	public void undo(Tablero tab) {
		tab.setCasilla(columna, fila, Ficha.VACIA);
		for (Punto p : fichasFlanqueadas) {
			tab.setCasilla(p.getX(), p.getY(), colorContrario);
		}
	}

	/**
	 * Guarda las coordenadas (puntos) de todas las fichas del jugador contrario
	 * que han quedado flanqueadas por este movimiento
	 */
	private void guardaCoordenadas(int distancia, int dx, int dy, Tablero tab) {
		int col = columna + dx;
		int fil = fila + dy;
		for (int i = 1; i <= distancia; i++) {
			fichasFlanqueadas.add(new Punto(col, fil));
			col += dx;
			fil += dy;
		}
	}

	/**
	 * Devuelve el mayor de dos enteros dados, en caso de ser iguales, devuelve
	 * el pasado por el segundo argumento
	 */
	private int mayorDeDos(int a, int b) {
		int mayor;
		if (a > b)
			mayor = a;
		else
			mayor = b;
		return mayor;
	}

	/**
	 * Compara las coordenadas y turno del jugador con el de otro movimiento
	 * pasado por parametro
	 */
	private boolean equals(Movimiento mov) {
		return (this.columna == mov.getColumna() && this.fila == mov.getFila() && this.jugador == mov
				.getJugador());
	}

	/**
	 * Calcula el color opuesto al turno del movimiento
	 * 
	 * @return
	 */
	private Ficha calculaColorContrario() {
		Ficha colorContrario;
		if (jugador == Ficha.BLANCA)
			colorContrario = Ficha.NEGRA;
		else
			colorContrario = Ficha.BLANCA;

		return colorContrario;
	}
}
