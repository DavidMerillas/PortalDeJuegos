package tp.pr5.swing;

import tp.pr5.control.ControladorVentana;
import tp.pr5.logica.Ficha;
import tp.pr5.vistas.PanelCambioJuego;
import tp.pr5.vistas.PanelInferior;
import tp.pr5.vistas.PanelPartida;
import tp.pr5.vistas.PanelTablero;
import tp.pr5.vistas.PanelTipoJugador;
import tp.pr5.vistas.PanelTurno;

import java.awt.*;

import javax.swing.*;

/**
 * Esta clase se encarga de construir la ventana con la que va a interactuar el
 * usuario del programa, utilizando todos los componentes Swing necesarios.
 */
public class Ventana {

	private ControladorVentana control;
	private final JFrame contPrincipal;

	// Atributos que almacenan el tipo los jugadores (aleatorio o humano) que
	// forman parte de la partida
	// que se está jugando en la ventana
	private JugadorSwing jugadorNegras;
	private JugadorSwing jugadorBlancas;

	// Este atributo no es necesario pero ofrece algunas ventajas (comodidad) a
	// la hora de
	// implementar ciertas operaciones
	// Se mantendrá constantemente actualizado (apuntando a jugadorNegras o
	// jugadorBlancas)
	// según corresponda el turno
	private JugadorSwing jugadorConTurno;

	// Se declaran los paneles globales que conformaran la Ventana:
	private JPanel panelIzquierdo; // oeste
	private JPanel panelDerecho; // este
	private JPanel panelInferior; // sur

	public Ventana(ControladorVentana controlador) throws HeadlessException {
		// Se inicializa el JFrame y se coloca el titulo ventana principal
		contPrincipal = new JFrame("Practica 5 - TP");
		contPrincipal.setLayout(new BorderLayout());

		control = controlador;

		// Ambos jugadores se inicializan a humanos por primera vez
		jugadorNegras = new JugadorSwingHumano(control);
		jugadorBlancas = new JugadorSwingHumano(control);
		jugadorConTurno = jugadorNegras;

		// Se inicializan los paneles globales:
		panelIzquierdo = new JPanel();
		panelIzquierdo.setLayout(new BorderLayout());
		panelDerecho = new JPanel();
		panelDerecho.setLayout(new BorderLayout());
		panelInferior = new JPanel();
		panelInferior.setLayout(new BorderLayout());

		// Se crean y añaden los paneles concretos que contienen las distintas
		// funcionalidades de la ventana:
		panelIzquierdo.add(new PanelTablero(control, this), BorderLayout.NORTH);
		panelIzquierdo.add(new PanelTurno(control), BorderLayout.CENTER);
		panelDerecho.add(new PanelPartida(control, this), BorderLayout.NORTH);
		panelDerecho.add(new PanelTipoJugador(control, this),
				BorderLayout.CENTER);
		panelDerecho.add(new PanelCambioJuego(control, this),
				BorderLayout.SOUTH);
		panelInferior.add(new PanelInferior(control, this), BorderLayout.SOUTH);

		// Se agregan los paneles globales al Container principal:
		contPrincipal.add(panelIzquierdo, BorderLayout.WEST);
		contPrincipal.add(panelDerecho, BorderLayout.EAST);
		contPrincipal.add(panelInferior, BorderLayout.SOUTH);

		// Se especifican valores de la ventana:
		contPrincipal.setPreferredSize(new Dimension(592, 469)); // Tamano 448
		contPrincipal.setLocation(0, 22); // Posicion
		contPrincipal.pack();
		contPrincipal.validate();
		contPrincipal.setVisible(true); // La hacemos visible
		contPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar
																			// programa
																			// al
		// cerrar ventana
	}

	public JugadorSwing getJugadorNegras() {
		return jugadorNegras;
	}

	public void setJugadorNegras(JugadorSwing jug) {
		jugadorNegras = jug;
	}

	public JugadorSwing getJugadorBlancas() {
		return jugadorBlancas;
	}

	public void setJugadorBlancas(JugadorSwing jug) {
		jugadorBlancas = jug;
	}

	public JugadorSwing getJugadorConTurno() {
		return jugadorConTurno;
	}

	/**
	 * Actualiza el atributo jugadorConTurno
	 * 
	 * @param turno
	 */
	public void actualizaJugadorConTurno(Ficha turno) {
		if (turno.toString().equals("blancas"))
			jugadorConTurno = jugadorBlancas;
		else
			jugadorConTurno = jugadorNegras;
	}
}
