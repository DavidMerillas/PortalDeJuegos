package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.*;

public class FactoriaReversi implements FactoriaTipoJuego {

	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoReversi(col, fila, color);
	}

	@Override
	public ReglasJuego creaReglas() {
		return new ReglasReversi();
	}

	@Override
	public Jugador creaJugadorHumanoConsola(Scanner in) {
		return new JugadorHumanoReversi(in);
	}

	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioReversi();
	}
}
