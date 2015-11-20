package tp.pr5.control;

import tp.pr5.logica.*;

/**
 * Clase que controla la ejecucion de la partida, pidiendo al usuario que quiere
 * ir haciendo, hasta que la partida termina.
 * 
 * @author David Merillas Pinar / Simon Da Silva
 */
public class ControladorVentana {
	private Partida partida;
	private FactoriaTipoJuego factoria;

	public ControladorVentana(FactoriaTipoJuego fact, Partida p) {
		partida = p;
		reset(fact);
	}

	/**
	 * Reinicia la factoria
	 * 
	 * @param fact
	 */
	public void reset(FactoriaTipoJuego fact) {
		factoria = fact;
	}

	/**
	 * Devuelve la partida que se está jugando
	 * 
	 * @return partida - partida actual
	 */
	public Partida getPartida() {
		return partida;
	}

	/**
	 * Devuelve la factoria (tipo de juego) que se está jugando
	 * 
	 * @return factoria - factoria actual
	 */
	public FactoriaTipoJuego getFactoria() {
		return factoria;
	}

	// Los siguientes metodos son llamados desde la VistaVentana, y actuan como
	// intermediarios entre la vista y el modelo.
	// Hacen los cambios pertinentes en la partida a partir de los botones
	// pulsados en la ventana por el usuario:

	public int getAnchoTablero() {
		return getPartida().getTablero().getAncho();
	}

	public int getAltoTablero() {
		return getPartida().getTablero().getAlto();
	}

	public Ficha getTurno() {
		return getPartida().getTurno();
	}

	public void deshacer() {
		getPartida().undo();
	}

	public void reiniciar() {
		getPartida().reset(getPartida().getReglas());
	}

	/**
	 * Cambia de juego segun las indicaciones del jugador
	 * 
	 * @param juego
	 * @param col
	 * @param fil
	 */
	public void cambiarJuego(String juego, int col, int fil) {
		FactoriaTipoJuego nuevaFactoria;

		// Se crea una nueva factoría dependiendo del juego al que se desee
		// cambiar
		if (juego.equals("conecta4")) {
			nuevaFactoria = new FactoriaConecta4();
		} else if (juego.equals("complica")) {
			nuevaFactoria = new FactoriaComplica();
		} else if (juego.equals("gravity")) {
			nuevaFactoria = new FactoriaGravity(col, fil);
		} else
			nuevaFactoria = new FactoriaReversi();

		// Se reinicia el propio Controlador y la partida en curso:
		this.reset(nuevaFactoria);
		partida.reset(factoria.creaReglas());
	}

	/**
	 * Crea y ejecuta un movimiento aleatorio
	 */
	public void ponerAleatorio() {
		Movimiento mov = factoria.creaJugadorAleatorio().getMovimiento(
				partida.getTablero(), partida.getTurno());

		try {
			getPartida().ejecutaMovimiento(mov);
		} catch (MovimientoInvalido e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Crea un movimiento con las coordenadas indicadas por el jugador y
	 * posteriormente lo ejecuta
	 * 
	 * @param i
	 * @param j
	 */
	public void poner(int i, int j) {
		Movimiento mov = factoria.creaMovimiento(i, j, getPartida().getTurno());
		try {
			getPartida().ejecutaMovimiento(mov);
		} catch (MovimientoInvalido e) {
			System.err.println(e.getMessage());
		}
	};

	/**
	 * Reinicia la partida sin cambiar de juego
	 */
	public void resetPartida() {
		partida.reset(partida.getReglas());
	}

	/**
	 * Envia un objeto observador de tipo VistaVentana a la partida para ser
	 * dada de alta como un observador de la misma
	 * 
	 * @param vista
	 */
	public void addObservador(ObservadorPartida vista) {
		getPartida().addObservador(vista);
	}

}