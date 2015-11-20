package tp.pr5.control;

import tp.pr5.logica.*;

/**
 * Implementacion de la factoria para el juego del Complica. Los m�todos
 * devuelven los objetos capaces de jugar a ese juego.
 */
public class FactoriaComplica implements FactoriaTipoJuego {

	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoComplica(col, color);
	}

	@Override
	public Reglas creaReglas() {
		return new ReglasComplica();
	}

	@Override
	public Jugador creaJugadorHumanoConsola(java.util.Scanner in) {
		return new JugadorHumanoComplica(in);
	}

	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioComplica();
	}
}
