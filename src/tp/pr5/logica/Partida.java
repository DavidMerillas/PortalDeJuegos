package tp.pr5.logica;

import java.util.ArrayList;

/**
 * Clase para representar la informacion de una partida. Se encarga de almacenar
 * el estado del tablero, a quien le toca, si ya hay un ganador, etc., asi como
 * la lista de movimientos que se han ido realizando para poder deshacerlos. La
 * partida guarda al menos los 10 ultimos movimientos.
 * 
 * @author David Merillas Pinar / Simon Da Silva
 */
public class Partida {

	private Tablero tablero;
	private Ficha turno;
	private boolean terminada;
	private Ficha ganador;
	private Pila pila;
	private ReglasJuego reglas;

	// Lista de observadores:
	private ArrayList<ObservadorPartida> obs;

	/**
	 * Construye una partida nueva.
	 * 
	 * @param reglas
	 *            - Reglas del juego que se utilizaran durante la partida (al
	 *            menos hasta que se ejecute reset).
	 */
	public Partida(ReglasJuego reg) {
		pila = new Pila();
		obs = new ArrayList<>();
		reset(reg);
	}

	/**
	 * Añade un observador a la lista de observadores
	 * 
	 * @param o
	 *            - Observador que se quiere dar de alta
	 */
	public void addObservador(ObservadorPartida o) {
		obs.add(o);
	}

	/**
	 * (Re)inicia la partida en curso. Esta operacion no puede deshacerse.
	 * Gracias al parametro, permite cambiar el tipo de juego al que se juega.
	 * 
	 * @param reglas
	 *            - Las reglas del juego a utilizar a partir de ahora.
	 */
	public void reset(ReglasJuego reg) {
		reglas = reg;
		tablero = reglas.iniciaTablero();
		turno = reglas.jugadorInicial();
		terminada = false;
		ganador = Ficha.VACIA;
		pila.reset();

		for (ObservadorPartida o : obs) {
			o.onCambioJuego(tablero, turno);
			o.onReset(tablero, turno);
		}
	}

	/**
	 * Ejecuta el movimiento indicado.
	 * 
	 * @param mov
	 *            - Movimiento a ejecutar. Se asume que el movimiento es
	 *            compatible con las reglas de la partida que se esta jugando
	 *            (si se esta jugando a Conecta 4, el tipo de movimiento es
	 *            Conecta 4, etc.). En caso contrario, el comportamiento es
	 *            indeterminado.
	 * @return true si se puede efectuar el movimiento. Es un error intentar
	 *         colocar una ficha del jugador que no tiene el turno, cuando la
	 *         partida esta terminada, columna llena, ...
	 * @throws MovimientoInvalido
	 */

	public void ejecutaMovimiento(Movimiento mov) throws MovimientoInvalido {
		boolean movimientoOK = false;
		try {
			if (!terminada) {
				if (turno == mov.getJugador()) {
					movimientoOK = mov.ejecutaMovimiento(tablero);

					if (movimientoOK) {
						pila.guardarMovimiento(mov);
						for (ObservadorPartida o : obs) {
							o.onMovimientoEnd(tablero, turno,
									reglas.siguienteTurno(turno, tablero));
						}
						estadoPartida(reglas, mov);

						if (terminada)
							for (ObservadorPartida o : obs)
								o.onPartidaTerminada(tablero, ganador);

					} else
						throw new MovimientoInvalido(
								"Jugador erroneo: no se puede ejecutar el movimiento.");

				} else
					throw new MovimientoInvalido(
							"Partida terminada: no se puede ejecutar el movimiento.");
			}
		} catch (MovimientoInvalido e) {
			for (ObservadorPartida o : obs)
				o.onMovimientoIncorrecto(new MovimientoInvalido(e.getMessage()));
		}
	}

	/**
	 * Metodo que actualiza el estado de la partida actual
	 * 
	 * @param reglas
	 * @param ultimoMovimiento
	 */
	private void estadoPartida(ReglasJuego reglas, Movimiento ultimoMovimiento) {
		boolean hayTablas = reglas.tablas(turno, tablero);
		ganador = reglas.hayGanador(ultimoMovimiento, tablero);

		if (hayTablas || ganador != Ficha.VACIA)
			terminada = true;
		else
			turno = reglas.siguienteTurno(turno, tablero);
	}

	/**
	 * Deshace el utimo movimiento ejecutado.
	 * 
	 * @return deshecho - true si se ha podido deshacer el ultimo movimiento
	 *         correctamente.
	 */
	public void undo() {
		Movimiento mov;
		if (!pila.isVacia()) {
			mov = pila.cima();
			mov.undo(tablero);
			// Se resta una posicion a la pila:
			pila.desapila();
			turno = reglas.siguienteTurno(turno, tablero);
			for (ObservadorPartida o : obs)
				o.onUndo(tablero, turno, pila.isVacia());
		} else
			for (ObservadorPartida o : obs)
				o.onUndoNotPossible(tablero, turno);
	}

	/**
	 * Devuelve el color del jugador al que le toca poner en el momento de
	 * ejecucion
	 * 
	 * @return Color del jugador, o Ficha.VACIA si la partida ha terminado.
	 */
	public Ficha getTurno() {

		if (terminada)
			turno = Ficha.VACIA;

		return turno;
	}

	/**
	 * Devuelve el color del ganador. Solo valido si la partida ya ha terminado
	 * 
	 * @return ganador - Color del ganador. Si la partida termina en tablas,
	 *         Ficha.VACIA. Si la partida no ha terminado aún, el resultado es
	 *         indeterminado.
	 */
	public Ficha getGanador() {
		return ganador;
	}

	/**
	 * Devuelve la reglas de la partida que se esta ejecutando
	 * 
	 * @return reglas - (reglas conecta4, complica, gravity, etc.)
	 */
	public ReglasJuego getReglas() {
		return reglas;
	}

	/**
	 * Devuelve un indicador sobre el estado de la partida
	 * 
	 * @return terminada - True si la partida ha concluido
	 */
	public boolean isTerminada() {
		return terminada;
	}

	/**
	 * Devuelve el tablero sobre el que se está jugando en la partida
	 * 
	 * @return tablero - tablero actual de la partida
	 */
	public Tablero getTablero() {
		return tablero;
	}

	/**
	 * Permite setear el tablero de la partida (reemplazarlo por uno nuevo)
	 * 
	 * @param tab
	 *            - tablero nuevo
	 */
	public void setTablero(Tablero tab) {
		tablero = tab;
	}

	/**
	 * Devuelve la Pila de la partida
	 * 
	 * @return
	 */
	public Pila getPila() {
		return pila;
	}

	/**
	 * Devuelve la funcion toString de la clase tablero (Muestra tablero por
	 * pantalla).
	 */
	public String toString() {
		return tablero.toString();
	}
}
