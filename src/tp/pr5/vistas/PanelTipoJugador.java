package tp.pr5.vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tp.pr5.control.ControladorVentana;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.ObservadorPartida;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.swing.JugadorSwingAleatorio;
import tp.pr5.swing.JugadorSwingHumano;
import tp.pr5.swing.Ventana;

public class PanelTipoJugador extends JPanel implements ObservadorPartida {

	private static final long serialVersionUID = 1L;
	private JPanel panelJugador;
	private String[] listaTipoJugadores = { "HUMANO", "ALEATORIO" };
	private JComboBox<String> tipoJugadorBlancas;
	private JComboBox<String> tipoJugadorNegras;
	private JLabel labelJugadorBlancas;
	private JLabel labelJugadorNegras;
	private ControladorVentana control;
	private Ventana ventana;

	public PanelTipoJugador(ControladorVentana c, Ventana v) {
		control = c;
		control.addObservador(this);

		ventana = v;

		panelJugador = new JPanel();
		panelJugador.setLayout(new GridLayout(4, 2));

		labelJugadorBlancas = new JLabel("Jugador Blancas");
		labelJugadorNegras = new JLabel("Jugador negras");

		tipoJugadorBlancas = new JComboBox<String>(listaTipoJugadores);

		tipoJugadorBlancas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tipoJugadorBlancas.getSelectedItem().equals("HUMANO")) {// Jugador
																			// HUMANO

					// Si el turno pertenece al mismo jugador cuyo 'tipo'
					// estamos cambiando (blancas en este caso),
					// hay que quitarle el turno, setear dicho jugador con el
					// nuevo tipo, y volver a concederle el turno
					if (control.getTurno() == Ficha.BLANCA) {

						ventana.getJugadorConTurno().PierdeTurno();
						ventana.setJugadorBlancas(new JugadorSwingHumano(
								control));
						ventana.actualizaJugadorConTurno(control.getTurno());

					} else
						// En caso de que se cambie el tipo de jugador DURANTE
						// EL TURNO CONTRARIO,
						// simplemente se setea dicho jugador al nuevo tipo
						ventana.setJugadorBlancas(new JugadorSwingHumano(
								control));

				} else { // Jugador ALEATORIO
					if (control.getTurno() == Ficha.BLANCA) {

						ventana.setJugadorBlancas(new JugadorSwingAleatorio(
								control));
						ventana.actualizaJugadorConTurno(control.getTurno());
						ventana.getJugadorConTurno().RecibeTurno();

					} else
						ventana.setJugadorBlancas(new JugadorSwingAleatorio(
								control));
				}
			}
		});

		tipoJugadorNegras = new JComboBox<String>(listaTipoJugadores);

		tipoJugadorNegras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tipoJugadorNegras.getSelectedItem().equals("HUMANO")) { // Jugador
																			// HUMANO

					// Si el turno pertenece al mismo jugador cuyo 'tipo'
					// estamos cambiando (negras en este caso),
					// hay que quitarle el turno, setear dicho jugador con el
					// nuevo tipo, y volver a concederle el turno
					if (control.getTurno() == Ficha.NEGRA) {

						ventana.getJugadorConTurno().PierdeTurno();
						ventana.setJugadorNegras(new JugadorSwingHumano(control));
						ventana.actualizaJugadorConTurno(control.getTurno());

					} else
						// En caso de que se cambie el tipo de jugador DURANTE
						// EL TURNO CONTRARIO,
						// simplemente se setea dicho jugador al nuevo tipo
						ventana.setJugadorNegras(new JugadorSwingHumano(control));

				} else { // Jugador ALEATORIO

					if (control.getTurno() == Ficha.NEGRA) {
						ventana.setJugadorNegras(new JugadorSwingAleatorio(
								control));
						ventana.actualizaJugadorConTurno(control.getTurno());
						ventana.getJugadorConTurno().RecibeTurno();

					} else
						ventana.setJugadorNegras(new JugadorSwingAleatorio(
								control));
				}
			}
		});

		panelJugador.add(labelJugadorBlancas);
		panelJugador.add(tipoJugadorBlancas);
		panelJugador.add(labelJugadorNegras);
		panelJugador.add(tipoJugadorNegras);

		panelJugador.setBorder(BorderFactory.createLineBorder(Color.gray));
		panelJugador.setBorder(BorderFactory
				.createTitledBorder("Gestion de jugadores"));
		panelJugador.setPreferredSize(new Dimension(198, 120));
		this.add(panelJugador);
	}

	@Override
	public void onReset(TableroInmutable tablero, Ficha turno) {

		ActivaCambioJugadores();
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {

		DesativaCambioJugadores();
	}

	@Override
	public void onCambioJuego(TableroInmutable tablero, Ficha turno) {

		ActivaCambioJugadores();
	}

	@Override
	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {
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
	}

	/**
	 * Activa los comboBox que permiten seleccionar el tipo de jugador de cada
	 * color
	 */
	private void ActivaCambioJugadores() {
		tipoJugadorBlancas.setEnabled(true);
		tipoJugadorNegras.setEnabled(true);
	}

	/**
	 * Desactiva los comboBox que permiten seleccionar el tipo de jugador de
	 * cada color
	 */
	private void DesativaCambioJugadores() {
		tipoJugadorBlancas.setEnabled(false);
		tipoJugadorNegras.setEnabled(false);
	}

}
