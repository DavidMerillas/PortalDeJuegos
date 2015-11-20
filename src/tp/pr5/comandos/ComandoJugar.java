package tp.pr5.comandos;

import tp.pr5.control.ControladorConsola;
import tp.pr5.control.FactoriaComplica;
import tp.pr5.control.FactoriaConecta4;
import tp.pr5.control.FactoriaGravity;
import tp.pr5.control.FactoriaReversi;
import tp.pr5.control.FactoriaTipoJuego;

public class ComandoJugar implements Comando {

	private ControladorConsola cntrl;

	// String que describe el nombre y formato de entrada del comando,
	// asi como una breve explicacion de su funcion:
	private static final String ayuda = "JUGAR [c4|co|gr|rv] [tamX tamY]: cambia el tipo de juego.";

	// Guardara el tipo de juego que ha introducido el
	// usuario:
	private String juego;

	// Guardaran las dimensiones del tablero que ha
	// introducido el usuario:
	private int ancho, alto;

	public ComandoJugar(ControladorConsola controlador) {
		cntrl = controlador;
	}

	@Override
	public boolean analizar(String cad) {
		boolean OK = false;
		try {
			String palabra[] = cad.split(" ");

			// Se comprueba la PRIMERA PALABRA de la cadena:
			if (palabra[0].equals("jugar")) {

				// Se comprueba y almacena la SEGUNDA PALABRA de la cadena:
				if (palabra[1].equals("c4") || palabra[1].equals("co")
						|| palabra[1].equals("rv")) {
					juego = palabra[1];
					OK = true;

				} else if (palabra[1].equals("gr")) {
					juego = palabra[1];

					// Se guardan las dimensiones de tablero introducidas:
					if (palabra.length > 2) {
						ancho = Integer.parseInt(palabra[2]);
						alto = Integer.parseInt(palabra[3]);
						OK = true;
					}
				}
			}
		} catch (Exception c) {
			// Excepcion para controlar que se meten valores correctos en ancho
			// y alto.
		}
		return OK;
	}

	@Override
	public void ejecutar() {
		FactoriaTipoJuego nuevaFactoria;

		// Se comprueba qué juego ha introducido el usuario y se crea la
		// factoria correspondiente:
		if (juego.equals("c4")) {
			nuevaFactoria = new FactoriaConecta4();
		} else if (juego.equals("co")) {
			nuevaFactoria = new FactoriaComplica();
		} else if (juego.equals("rv")) {
			nuevaFactoria = new FactoriaReversi();
		} else {
			nuevaFactoria = new FactoriaGravity(ancho, alto);
		}

		// Se reinicia el controlador (se actualiza la Factoria y Jugadores a
		// humanos):
		cntrl.reset(nuevaFactoria, cntrl.getScanner());

		// Se reinicia partida:
		cntrl.resetPartida(nuevaFactoria.creaReglas());

	}

	@Override
	public String ayuda() {
		return ayuda;
	}
}
