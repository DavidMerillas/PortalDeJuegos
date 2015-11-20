package tp.pr5.vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import tp.pr5.control.ControladorVentana;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.ObservadorPartida;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.swing.Ventana;

public class PanelPartida extends JPanel implements ObservadorPartida {

	private static final long serialVersionUID = 1L;
	private JPanel panelPartida;
	private JButton deshacer;
	private JButton reiniciar;
	private ControladorVentana control;

	private Ventana ventana;

	public PanelPartida(ControladorVentana c, Ventana v) {
		control = c;
		control.addObservador(this);

		ventana = v;

		panelPartida = new JPanel();

		// Boton Deshacer:
		deshacer = new JButton("Deshacer");
		deshacer.setEnabled(false);
		deshacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.getJugadorConTurno().PierdeTurno();
				control.deshacer();
			}
		});
		panelPartida.add(deshacer);

		// Boton Reiniciar:
		reiniciar = new JButton("Reiniciar");
		reiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.getJugadorConTurno().PierdeTurno();
				control.reiniciar();
			}
		});
		panelPartida.add(reiniciar);

		// Borde:
		panelPartida.setBorder(BorderFactory.createLineBorder(Color.gray));
		panelPartida.setBorder(BorderFactory.createTitledBorder("Partida"));
		panelPartida.setPreferredSize(new Dimension(198, 55));
		this.add(panelPartida);

	}

	@Override
	public void onReset(TableroInmutable tablero, Ficha turno) {
		deshacer.setEnabled(false);
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {
		deshacer.setEnabled(false);
	}

	@Override
	public void onCambioJuego(TableroInmutable tablero, Ficha turno) {
	}

	@Override
	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {
	}

	@Override
	public void onUndo(TableroInmutable tablero, Ficha turno, boolean pilaVacia) {
		// Se comprueba si siguen habiendo movimientos en la pila de movimientos
		if (pilaVacia)
			// Si no es así, se desactiva el botón de deshacer
			deshacer.setEnabled(false);
	}

	@Override
	public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador,
			Ficha turno) {
		if (!deshacer.isEnabled())
			deshacer.setEnabled(true);
	}

	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
	}
}
