package tp.pr5.comandos;

import tp.pr5.control.ControladorConsola;

public class ComandoDeshacer implements Comando {

	// String que describe el nombre y formato de entrada del comando,
	// asi como una breve explicacion de su funcion:
	private static final String ayuda = "DESHACER: deshace el último movimiento hecho en la partida.";

	private ControladorConsola controlador;

	public ComandoDeshacer(ControladorConsola cntr) {
		controlador = cntr;
	}

	@Override
	public boolean analizar(String cad) {
		return (cad.equals("deshacer"));
	}

	@Override
	public void ejecutar() {
		controlador.deshacer();
	}

	@Override
	public String ayuda() {
		return ayuda;
	}
}
