package tp.pr5.comandos;

import tp.pr5.control.ControladorConsola;
import tp.pr5.logica.MovimientoInvalido;

public class ComandoPoner implements Comando {

	// String que describe el nombre y formato de entrada del comando,
	// asi como una breve explicacion de su funcion:
	private static final String ayuda = "PONER: utilízalo para poner la siguiente ficha.";

	private ControladorConsola cntrl;

	public ComandoPoner(ControladorConsola controlador) {
		cntrl = controlador;
	}

	@Override
	public boolean analizar(String cad) {
		return (cad.equals("poner"));
	}

	@Override
	public void ejecutar() throws MovimientoInvalido {
		// Si se recibe un true, es porque la partida ha acabado con el
		// movimiento que ha sido ejecutado
		// por lo que hay que detener la ejecución
		if (cntrl.poner())
			System.exit(0);
	}

	@Override
	public String ayuda() {
		return ayuda;
	}

}
