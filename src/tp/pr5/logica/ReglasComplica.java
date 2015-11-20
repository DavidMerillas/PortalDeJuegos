package tp.pr5.logica;

/**
 * Implementaci�n de las reglas del Complica. Se deben implementar todos los
 * m�todos del interfaz, adem�s del constructor.
 * 
 * @author David Merillas Pinar / Simon Da Silva
 */

public class ReglasComplica extends Reglas {
	private Ficha colorGanador;

	/**
	 * Constructor de la clase, sin parametros.
	 */
	public ReglasComplica() {
	}

	@Override
	public Tablero iniciaTablero() {
		Tablero tab = new Tablero(4, 7);
		tab.reset();
		return tab;
	}

	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		Ficha ganador;

		if (hayLinea(ultimoMovimiento, t))
			ganador = colorGanador;

		else
			ganador = Ficha.VACIA;

		return ganador;
	}

	@Override
	/**
	 * En el juego Complica la partida nunca puede quedar en tablas,
	 * por lo que este método devuelve un false en cualquier caso.
	 */
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		return false;
	}

	/**
	 * Comprueba si hay 4 fichas de un mismo color conectadas en una misma
	 * direccion.
	 * 
	 * @return hayConecta4 - True si se han encontrado 4 fichas seguidas de un
	 *         mismo color.
	 */
	private boolean hayLinea(Movimiento mov, Tablero tab) {

		boolean hayLinea = false;
		boolean gananBlancas = false;
		boolean gananNegras = false;

		// SE BUSCAN LAS COORDENADAS de la ultima ficha colocada:
		int x = mov.getColumna();
		int y = mov.getFila();

		// Se busca un ganador en todas las filas ocupadas de dicha columna:
		for (int i = y; i <= tab.getAlto(); i++) {
			int contDiagDerecha = 1;
			int contDiagIzquierda = 1;
			int contHorizontales = 1;
			int contVerticales = 1;
			Ficha turno = tab.getCasilla(x, i);
			// SE INICIA LA BUSQUEDA en funcion de la direccion:
			// Diagonal hacia la derecha (hacia arriba y abajo respectivamente):
			contDiagDerecha += buscarConectadas(x, i, 1, -1, turno, tab);
			contDiagDerecha += buscarConectadas(x, i, -1, 1, turno, tab);

			// Diagonal hacia la izquierda (hacia arriba y abajo
			// respectivamente):
			contDiagIzquierda += buscarConectadas(x, i, -1, -1, turno, tab);
			contDiagIzquierda += buscarConectadas(x, i, 1, 1, turno, tab);

			// Horizontal (derecha e izquierda respectivamente):
			contHorizontales += buscarConectadas(x, i, 1, 0, turno, tab);
			contHorizontales += buscarConectadas(x, i, -1, 0, turno, tab);

			// Vertical (la unica posibilidad es hacia abajo):
			contVerticales += buscarConectadas(x, i, 0, 1, turno, tab);

			if (contDiagDerecha >= 4 || contDiagIzquierda >= 4
					|| contHorizontales >= 4 || contVerticales >= 4) {
				if (turno == Ficha.BLANCA) {
					gananBlancas = true;
					colorGanador = Ficha.BLANCA;
				} else if (turno == Ficha.NEGRA) {
					gananNegras = true;
					colorGanador = Ficha.NEGRA;
				}
			}
		}

		if ((gananBlancas && !gananNegras) || (!gananBlancas && gananNegras))
			hayLinea = true;

		return hayLinea;
	}
}
