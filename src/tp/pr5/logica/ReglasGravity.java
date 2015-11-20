package tp.pr5.logica;

public class ReglasGravity extends Reglas {
	private int columnas, filas;

	public ReglasGravity(int x, int y) {
		columnas = x;
		filas = y;
	}

	@Override
	public Tablero iniciaTablero() {
		Tablero tab = new Tablero(columnas, filas);
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