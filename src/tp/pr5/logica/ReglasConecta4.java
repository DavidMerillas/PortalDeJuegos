package tp.pr5.logica;

/**
 * Implementacion de las reglas del Conecta 4. Se deben implementar todos los
 * m�todos del interfaz, adem�s del constructor.
 * 
 * @author David Merillas Pinar / Simon Da Silva
 */
public class ReglasConecta4 extends Reglas {

	/**
	 * Constructor de la clase, sin parametros.
	 */
	public ReglasConecta4() {
	};

	@Override
	public Tablero iniciaTablero() {
		Tablero tab = new Tablero(7, 6);
		tab.reset();
		return tab;
	}

	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		return super.hayGanador(ultimoMovimiento, t);
	}

	@Override
	protected boolean hayConecta4(Movimiento mov, Tablero tab) {
		return super.hayConecta4(mov, tab);
	}

	@Override
	protected int buscarConectadas(int col, int fil, int dx, int dy,
			Ficha color, Tablero tablero) {
		return super.buscarConectadas(col, fil, dx, dy, color, tablero);
	}

	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		return super.tablas(ultimoEnPoner, t);
	}
}
