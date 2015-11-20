package tp.pr5.logica;

/**
 * Clase que implementa el movimiento para el juego del Complica. Se deben
 * implementar los m�todos abstractos de la clase padre.
 * 
 * @author David Merillas Pinar / Simon Da Silva
 */
public class MovimientoComplica extends Movimiento {

	private Ficha fichaDesplazada;

	public MovimientoComplica(int donde, Ficha color) {
		super(donde, color);

		// En fichaDesplazada se guardara, en caso de que exista,
		// la ficha que el movimiento haya desplazado del tablero:
		fichaDesplazada = Ficha.VACIA;
	}

	public Ficha getFichaDesplazada() {
		return fichaDesplazada;
	}

	@Override
	public boolean ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {

		boolean movimientoOK = super.ejecutaMovimiento(tab);
		Ficha color = jugador;

		// Se comprueba si la columna en donde se quiere poner est� llena:
		// La columna SI esta llena:

		if (!movimientoOK) {
			// Se guarda la ficha que va a ser desplazada:
			fichaDesplazada = tab.getCasilla(columna, tab.getAlto());

			// Se desplazan todas las fichas hacia una fila mas abajo
			// (desapareciendo asi la ficha inferior de la columna, que
			// ya ha sido guardada en el atributo fichaDesplazada):
			for (int i = tab.getAlto(); i >= 2; i--) {
				color = tab.getCasilla(columna, (i - 1));
				tab.setCasilla(columna, i, color);
			}

			// Se pone la nueva ficha en la fila superior:
			fila = 1;
			tab.setCasilla(columna, fila, jugador);
			movimientoOK = true;
		}

		return movimientoOK;
	}

	@Override
	public void undo(Tablero tab) {

		// Se comprueba si el movimiento a deshacer habia desplazado alguna
		// ficha:
		// NO hab�a ficha desplazada:
		if (fichaDesplazada == Ficha.VACIA)
			tab.setCasilla(columna, fila, Ficha.VACIA);

		// SI hab�a ficha desplazada:
		else {
			// Se sube una posicion (fila) todas las fichas de dicha columna:
			for (int i = 2; i <= tab.getAlto(); i++) {
				tab.setCasilla(columna, i - 1, tab.getCasilla(columna, i));
			}

			// Se vuelve a colocar la ficha desplazada en la ultima fila de la
			// columna:
			tab.setCasilla(columna, tab.getAlto(), fichaDesplazada);
		}
	}
}
