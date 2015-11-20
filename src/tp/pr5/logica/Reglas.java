package tp.pr5.logica;

public abstract class Reglas implements ReglasJuego {

	@Override
	public Ficha jugadorInicial() {
		return Ficha.BLANCA;
	}

	/**
	 * Actua como un switch, si el ultimo en poner fueron negras, devuelve
	 * blancas, y viceversa
	 */
	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {
		Ficha siguienteTurno;

		if (ultimoEnPoner == Ficha.BLANCA)
			siguienteTurno = Ficha.NEGRA;
		else if (ultimoEnPoner == Ficha.NEGRA)
			siguienteTurno = Ficha.BLANCA;
		else
			siguienteTurno = Ficha.VACIA;

		return siguienteTurno;
	};

	/**
	 * * Determina si se ha producido un ganador a partir del último movimiento
	 * realizado, el cual recibe como parámetro
	 */
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		Ficha ganador;

		if (hayConecta4(ultimoMovimiento, t))
			ganador = ultimoMovimiento.getJugador();

		else
			ganador = Ficha.VACIA;

		return ganador;
	}

	/**
	 * Comprueba si hay 4 fichas de un mismo color conectadas en una misma
	 * direccion.
	 * 
	 * @return hayConecta4 - True si se han encontrado 4 fichas seguidas de un
	 *         mismo color.
	 */
	protected boolean hayConecta4(Movimiento mov, Tablero tab) {
		boolean hayConecta4 = false;
		int contDiagDerecha = 1;
		int contDiagIzquierda = 1;
		int contHorizontales = 1;
		int contVerticales = 1;

		Ficha turno = mov.getJugador();

		// SE BUSCAN LAS COORDENADAS de la ultima ficha colocada:
		int x = mov.getColumna();
		int y = mov.getFila();

		// SE INICIA LA BUSQUEDA en funcion de la direccion:
		// Diagonal hacia la derecha (hacia arriba y abajo respectivamente):
		contDiagDerecha += buscarConectadas(x, y, 1, -1, turno, tab);
		contDiagDerecha += buscarConectadas(x, y, -1, 1, turno, tab);

		// Diagonal hacia la izquierda (hacia arriba y abajo respectivamente):
		contDiagIzquierda += buscarConectadas(x, y, -1, -1, turno, tab);
		contDiagIzquierda += buscarConectadas(x, y, 1, 1, turno, tab);

		// Horizontal (derecha e izquierda respectivamente):
		contHorizontales += buscarConectadas(x, y, 1, 0, turno, tab);
		contHorizontales += buscarConectadas(x, y, -1, 0, turno, tab);

		// Vertical hacia abajo:
		contVerticales += buscarConectadas(x, y, 0, 1, turno, tab);

		// Vertical hacia arriba (solo se puede dar en el juego Gravity):
		contVerticales += buscarConectadas(x, y, 0, -1, turno, tab);

		if (contDiagDerecha >= 4 || contDiagIzquierda >= 4
				|| contHorizontales >= 4 || contVerticales >= 4)
			hayConecta4 = true;

		return hayConecta4;
	}

	/**
	 * Busca fichas contiguas (en una determinada direccion) de un mismo color.
	 * 
	 * @param col
	 *            -Numero de columna de la ficha inicial a partir de la que se
	 *            realiza la busqueda.
	 * @param fil
	 *            - Numero de fila de la ficha inicial a partir de la que se
	 *            realiza la busqueda.
	 * @param dx
	 *            - deltaX - Indica la direccion de la coordenada X en la que se
	 *            debe buscar.
	 * @param dy
	 *            - deltaY - Parametro que indica la direccion de la coordenada
	 *            Y en la que se debe buscar.
	 * @param color
	 *            - Tipo de ficha que se esta buscando.
	 * @return contador - Indica el numero de fichas contiguas (en una misma
	 *         direccion) que se hayan encontrado.
	 */

	protected int buscarConectadas(int col, int fil, int dx, int dy,
			Ficha color, Tablero tablero) {
		int contador = 0;

		while (contador < 4 && color == tablero.getCasilla(col + dx, fil + dy)) {
			col += dx;
			fil += dy;
			contador += 1;
		}
		return contador;
	}

	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		/*
		 * COMENTADO POR ERROR EN EL VALIDADOR. Se supone que si el ultimo
		 * jugador en poner es blancas, nunca habria tablas por lo que no seria
		 * necesario comprobar cada turno si hay tablas, pero en el caso de que
		 * el tablero sea 1x1 habra tablas y el unico que podria ejecutar un
		 * movimiento seria el jugador quejuega con las blancas.
		 * 
		 * La implementacion que teniamos era:
		 * 
		 * boolean tablas = false;
		 * 
		 * if (ultimoEnPoner != Ficha.BLANCA) tablas = t.tableroLleno();
		 */
		return t.tableroLleno();
	}
}
