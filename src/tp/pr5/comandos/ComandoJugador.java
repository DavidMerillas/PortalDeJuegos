package tp.pr5.comandos;

import tp.pr5.control.ControladorConsola;
import tp.pr5.control.Jugador;

public class ComandoJugador implements Comando {
	private ControladorConsola controlador;

	// String que describe el nombre y formato de entrada del comando,
	// asi como una breve explicacion de su funcion:
	private static final String ayuda = "JUGADOR [blancas|negras] [humano|aleatorio]: cambia el tipo de jugador.";

	// Atributo en donde se guardara el color del jugador introducido por el
	// usuario:
	private String color;

	// Atributo en donde se guardara el tipo de jugador introducido por el
	// usuario:
	private Jugador tipoJugador;

	public ComandoJugador(ControladorConsola cntr) {
		controlador = cntr;
	}

	@Override
	public boolean analizar(String cad) {
		boolean OK = false;

		// Se comprueba la PRIMERA PALABRA de la cadena:
		if (cad.startsWith("jugador")) {

			// Se comprueba y almacena la SEGUNDA PALABRA de la cadena:
			if (cad.startsWith("blancas", 8) || cad.startsWith("negras", 8)) {
				color = cad.substring(8, 15);

				// Se comprueba la TERCERA PALABRA de la cadena y se inicializa
				// el atributo tipoJugador:
				if (cad.endsWith("humano")) {
					tipoJugador = controlador.getFactoria()
							.creaJugadorHumanoConsola(null);
					OK = true;
				} else if (cad.endsWith("aleatorio")) {
					tipoJugador = controlador.getFactoria()
							.creaJugadorAleatorio();
					OK = true;
				}
			}
		}
		return OK;
	}

	@Override
	public void ejecutar() {
		controlador.cambiaTipoJugador(color, tipoJugador);
	}

	@Override
	public String ayuda() {
		return ayuda;
	}
}
