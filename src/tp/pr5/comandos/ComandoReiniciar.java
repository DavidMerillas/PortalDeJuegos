package tp.pr5.comandos;

import tp.pr5.control.ControladorConsola;

public class ComandoReiniciar implements Comando {

	// String que describe el nombre y formato de entrada del comando,
	// asi como una breve explicacion de su funcion:
	private static final String ayuda = "REINICIAR: reinicia la partida.";

	private ControladorConsola controlador;

	public ComandoReiniciar(ControladorConsola cntr) {
		controlador = cntr;
	}

	@Override
	public boolean analizar(String cad) {
		return (cad.equals("reiniciar"));
	}

	@Override
	public void ejecutar() {

		// Se reinicia la partida:
		controlador.resetPartida();

	}

	@Override
	public String ayuda() {
		return ayuda;
	}
}
