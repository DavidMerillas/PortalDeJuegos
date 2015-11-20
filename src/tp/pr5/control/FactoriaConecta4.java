package tp.pr5.control;

import tp.pr5.logica.*;

/**
 * Implementaci�n de la factor�a para el juego del Conecta 4. Los m�todos
 * devuelven los objetos capaces de jugar a ese juego.
 */
public class FactoriaConecta4 implements FactoriaTipoJuego {

	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoConecta4(col, color);
	}

	@Override
	public Reglas creaReglas() {
		return new ReglasConecta4();
	}

	@Override
	public Jugador creaJugadorHumanoConsola(java.util.Scanner in) {
		return new JugadorHumanoC4(in);
	}

	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioConecta4();
	}

}
