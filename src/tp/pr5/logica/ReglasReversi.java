package tp.pr5.logica;

public class ReglasReversi extends Reglas {
	private boolean hayMovimientosValidos;

	// Contadores que serviran para guardar el numero de fichas/casillas que hay
	// de cada tipo
	private int numFichasBlancas;
	private int numFichasNegras;
	private int numCasillasVacias;

	public ReglasReversi() {
		resetNumFichas();
		hayMovimientosValidos = false;
	}

	@Override
	/**
	 * Devuelve el tablero inicial por defecto del Reversi
	 */
	public Tablero iniciaTablero() {
		Tablero tab = creaTableroInicial();
		buscaMovimientosValidos(Ficha.NEGRA, tab);
		return tab;
	}

	/**
	 * Crea el tablero inicial por defecto del Reversi. Consiste en un tablero
	 * de 8 x 8 casillas con cuatro fichas centrales (2 blancas y 2 negras)
	 * 
	 * @return tablero
	 */
	private Tablero creaTableroInicial() {
		// Se crea el tablero con las dimensiones por defecto:
		Tablero tab = new Tablero(8, 8);
		tab.reset();

		// Se colocan las cuatro fichas centrales:
		tab.setCasilla(tab.getAncho() / 2, tab.getAlto() / 2, Ficha.BLANCA);
		tab.setCasilla(tab.getAncho() / 2 + 1, tab.getAlto() / 2 + 1,
				Ficha.BLANCA);
		tab.setCasilla(tab.getAncho() / 2 + 1, tab.getAlto() / 2, Ficha.NEGRA);
		tab.setCasilla(tab.getAncho() / 2, tab.getAlto() / 2 + 1, Ficha.NEGRA);

		return tab;
	}

	@Override
	public Ficha jugadorInicial() {

		return Ficha.NEGRA;
	}

	/**
	 * Este método es el encargado de realizar el cambio de turno del Reversi
	 */
	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero tab) {

		Ficha siguienteTurno = super.siguienteTurno(ultimoEnPoner, tab);

		// Antes de devolver el siguiente turno hay que hacer una busqueda de
		// movimientos validos, para comprobar si el turno cambia de color o no
		buscaMovimientosValidos(siguienteTurno, tab);

		// Si hay movimientos validos para el jugador contrario, el turno cambia
		// de color:
		if (hayMovimientosValidos)
			return siguienteTurno;

		// Si no hay movimientos validos, el turno mantiene el color:
		else {
			buscaMovimientosValidos(ultimoEnPoner, tab);
			return ultimoEnPoner;
		}

	}

	/**
	 * Este método está encargado de buscar movimientos válidos (que flanquean
	 * fichas contrarias) en un tablero y turno determinados. Hace un recorrido
	 * del tablero, casilla por casilla, buscando la posibilidad de realizar
	 * "un reversi" desde cada una de ellas. Actualiza el flag
	 * "hayMovimientosValidos" y guarda dichos movimientos (si existen) en el
	 * array "movimientosValidos" del tablero.
	 */
	private void buscaMovimientosValidos(Ficha turno, Tablero tab) {

		hayMovimientosValidos = false;
		MovimientoReversi mov;

		// Se vacia el array de movimientos validos del
		// tablero,
		// ya que pertenecen al turno anterior:
		tab.vaciaMovimientosValidos();

		for (int i = 1; i <= tab.getAncho(); i++)
			for (int j = 1; j <= tab.getAlto(); j++) {

				// Se crea un movimiento con las coordenadas del tablero
				// en las que nos encontramos:
				mov = new MovimientoReversi(i, j, turno);

				// Se comprueba si "hay reversi" desde esa posicion:
				if (tab.getCasilla(i, j) == Ficha.VACIA
						&& mov.buscaReversi(tab)) {


					// Se añade este movimiento (por ser válido) al array de
					// movimientos validos del tablero:
					tab.addMovimientoValido(mov);

					hayMovimientosValidos = true;
				}
			}
	}

	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		Ficha ganador = Ficha.VACIA;

		cuentaFichas(t);

		// Si el tablero se ha llenado completamente:
		if (numCasillasVacias == 0) {
			if (numFichasBlancas > numFichasNegras)
				ganador = Ficha.BLANCA;// blanca
			else if (numFichasNegras > numFichasBlancas)
				ganador = Ficha.NEGRA;// negra
		}

		// Si solo quedan fichas de un solo color:
		else if (numFichasBlancas == 0)
			ganador = Ficha.NEGRA;

		else if (numFichasNegras == 0)
			ganador = Ficha.BLANCA;

		return ganador;
	}

	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		boolean tablas = false;

		cuentaFichas(t);

		// En el caso de no haber casillas vacías y que el número de fichas de
		// ambos colores sea el mismo, la partida queda en tablas:
		if (numCasillasVacias == 0 && (numFichasNegras == numFichasBlancas))
			tablas = true;

		return tablas;
	}

	/**
	 * Recorre el tablero y actualiza los contadores que indican el número de
	 * fichas/casillas de cada tipo que hay en el tablero, siendo estas: negras,
	 * blancas o vacías (en las que se incluyen las casillas sombreadas)
	 */
	private void cuentaFichas(Tablero t) {

		resetNumFichas();

		for (int i = 1; i <= t.getAncho(); i++) {
			for (int j = 1; j <= t.getAlto(); j++) {
				if (t.getCasilla(i, j) == Ficha.BLANCA)
					numFichasBlancas++;
				else if (t.getCasilla(i, j) == Ficha.NEGRA)
					numFichasNegras++;
				else
					numCasillasVacias++;
			}
		}
	}

	/**
	 * Pone a 0 los contadores del tipo de fichas/casillas
	 */
	private void resetNumFichas() {
		numFichasBlancas = 0;
		numFichasNegras = 0;
		numCasillasVacias = 0;
	}
}
