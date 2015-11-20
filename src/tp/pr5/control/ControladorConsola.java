package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.comandos.Comando;
import tp.pr5.logica.*;
import tp.pr5.vistas.VistaConsola;

/**
 * Clase que controla la ejecucion de la partida, pidiendo al usuario que quiere
 * ir haciendo, hasta que la partida termina.
 * 
 * @author David Merillas Pinar / Simon Da Silva
 */
public class ControladorConsola {
	private Partida partida;
	private Scanner in;
	private FactoriaTipoJuego factoria;
	GestorComandos gestor;

	// Estos atributos guardaran el TIPO de jugador (humano/aleatorio)
	// correspondiente a cada turno:
	private Jugador jugBlancas;
	private Jugador jugNegras;

	public ControladorConsola(FactoriaTipoJuego fact, Partida p,
			java.util.Scanner sc) {
		partida = p;
		in = sc;
		gestor = new GestorComandos(this);
		reset(fact, in);
	}

	/**
	 * Recibe un String con la elección que ha introducido el usuario (Desde la
	 * VistaConsola) y con él hace un parseo para determinar el comando que se
	 * debe ejecutar
	 * 
	 * @param eleccion
	 * @return
	 * @throws Exception
	 */
	public void run(String eleccion) throws Exception {
		Comando comando = gestor.parser(eleccion);
		comando.ejecutar();
	}

	/**
	 * Reinicia la factoria y los jugadores del controlador.
	 * 
	 * @param fact
	 * @param sc
	 */
	public void reset(FactoriaTipoJuego fact, java.util.Scanner sc) {
		factoria = fact;
		// Ambos turnos de la partida se inicializar a Jugadores Humanos por
		// defecto:
		jugBlancas = factoria.creaJugadorHumanoConsola(in);
		jugNegras = factoria.creaJugadorHumanoConsola(in);
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
	 * Devuelve el escaner que está usando el controlador
	 * 
	 * @return in - escaner actual
	 */
	public Scanner getScanner() {
		return in;
	}

	/**
	 * Devuelve la factoria (tipo de juego) que se está jugando
	 * 
	 * @return factoria - factoria actual
	 */
	public FactoriaTipoJuego getFactoria() {
		return factoria;
	}

	/**
	 * Devuelve el tipo de jugador correspondiente al turno de fichas Blancas
	 * 
	 * @return jugBlancas - tipo jugador blancas
	 */
	public Jugador getJugadorBlancas() {
		return jugBlancas;
	}

	/**
	 * Permite setear el tipo de jugador correspondiente al turno de Blancas
	 */
	public void setJugadorBlancas(Jugador jugador) {
		jugBlancas = jugador;
	}

	/**
	 * Devuelve el tipo de jugador correspondiente al turno de fichas Negras
	 * 
	 * @return jugNegras - tipo jugador negras
	 */
	public Jugador getJugadorNegras() {
		return jugNegras;
	}

	/**
	 * Permite setear el tipo de jugador correspondiente al turno de Negras
	 */
	public void setJugadorNegras(Jugador jugador) {
		jugNegras = jugador;
	}

	/**
	 * Envia un objeto observador de tipo VistaConsola a la partida para ser
	 * dada de alta como un observador de la misma
	 * 
	 * @param vista
	 */
	public void addObserver(VistaConsola vista) {
		getPartida().addObservador(vista);
	}

	/**
	 * Llama al undo() de la partida que se está jugando
	 */
	public void deshacer() {
		partida.undo();
	}

	/**
	 * Actualiza el TIPO de jugador (humano/aleatorio) correspondiente a un
	 * determinado color, segun las indicaciones del usuario
	 * 
	 * @param color
	 * @param tipoJugador
	 */
	public void cambiaTipoJugador(String color, Jugador tipoJugador) {

		if (color.equals("blancas"))
			setJugadorBlancas(tipoJugador);
		else
			setJugadorNegras(tipoJugador);
	}

	/**
	 * Crea y ejecuta un movimiento, llamando al ejecutaMovimiento de la partida
	 * 
	 * @return
	 * @throws MovimientoInvalido
	 */
	public boolean poner() throws MovimientoInvalido {
		Movimiento mov;
		boolean salir = false;

		// Se comprueba el turno que se está jugando y se crea el movimiento
		// que se pretende ejecutar, guardandolo en la variable "mov":
		if (partida.getTurno() == Ficha.BLANCA) {
			mov = jugBlancas.getMovimiento(partida.getTablero(), Ficha.BLANCA);
		} else {
			mov = jugNegras.getMovimiento(partida.getTablero(), Ficha.NEGRA);
		}

		// Se intenta ejecutar dicho movimiento:
		partida.ejecutaMovimiento(mov);

		if (partida.isTerminada())
			salir = true;

		return salir;
	}

	/**
	 * Reinicia la partida con las mismas reglas del juego que se venía jugando
	 * hasta ahora
	 */
	public void resetPartida() {
		partida.reset(partida.getReglas());
	}

	/**
	 * Reinicia la partida con unas reglas de juego diferentes a las que se
	 * venía jugando
	 * 
	 * @param reglas
	 */
	public void resetPartida(ReglasJuego reglas) {
		partida.reset(reglas);
	}
}
