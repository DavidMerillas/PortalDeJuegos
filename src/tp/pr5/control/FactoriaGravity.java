package tp.pr5.control;

import tp.pr5.logica.*;

/**
 * Jugador que juega de forma aleatoria a Gravity. Simplemente elige una
 * posici�n aleatoria en el tablero, comprobando antes que esa casilla est�
 * vac�a (se podr� poner).
 */
public class FactoriaGravity implements FactoriaTipoJuego {

	// Estos atributos guardaran las dimensiones de tablero introducidas por el
	// usuario:
	private int columnas, filas;

	public FactoriaGravity(int x, int y) {
		columnas = x;
		filas = y;
	}

	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoGravity(col, fila, color);
	}

	@Override
	public Reglas creaReglas() {
		return new ReglasGravity(columnas, filas);
	}

	@Override
	public Jugador creaJugadorHumanoConsola(java.util.Scanner in) {
		return new JugadorHumanoGravity(in);
	}

	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioGravity();
	}

}
