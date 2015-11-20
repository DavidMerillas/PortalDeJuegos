package tp.pr5.vistas;

import tp.pr5.control.ControladorConsola;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.ObservadorPartida;
import tp.pr5.logica.TableroInmutable;

public class VistaConsola implements ObservadorPartida {
	private ControladorConsola control;

	public VistaConsola(ControladorConsola controladora) {
		control = controladora;
		// Se da de alta este observador en el modelo:
		control.addObserver(this);
	}

	/**
	 * Ejecuta la partida hasta que esta termina.
	 */
	public void run() {
		String eleccion = null;

		// Inicio del juego:
		while (true) {
			// "Cabecera" de la salida por pantalla. Se muestra tablero por
			// pantalla,
			// se indica el turno correspondiente y se pregunta qué desea hacer
			// el usuario:
			System.out.println(control.getPartida().toString());
			System.out.println("Juegan " + control.getPartida().getTurno());
			System.out.print("Qué quieres hacer? ");
			eleccion = control.getScanner().nextLine().toLowerCase();

			try {
				control.run(eleccion);
			} catch (Exception e) {
				System.err.println("No te entiendo.");
			}
		}
	}

	@Override
	public void onReset(TableroInmutable tablero, Ficha turno) {

		System.out.println("Partida reiniciada.");
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {

		if (ganador != Ficha.VACIA)
			System.out.println("Ganan las " + ganador);

		else
			System.out.println("Partida terminada en tablas.");
	}

	@Override
	public void onCambioJuego(TableroInmutable tablero, Ficha turno) {
	}

	@Override
	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {

		System.err.println("Imposible deshacer");
	}

	@Override
	public void onUndo(TableroInmutable tablero, Ficha turno, boolean HayMas) {
	}

	@Override
	public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador,
			Ficha turno) {
	}

	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
		System.err.println(movimientoException.getMessage());
	}
}
