package tp.pr5.vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.pr5.control.ControladorVentana;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.ObservadorPartida;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.swing.Ventana;

public class PanelInferior extends JPanel implements ObservadorPartida {

	private static final long serialVersionUID = 1L;
	private ControladorVentana control;
	// Botonera del panel inferior:
	private JPanel botInf1;
	private JButton poner;
	private JButton salir;
	private Ventana ventana;

	public PanelInferior(ControladorVentana c, Ventana v) {
		control = c;
		control.addObservador(this);

		ventana = v;
		botInf1 = new JPanel();

		// Boton Poner:
		poner = new JButton("Poner Aleatorio");
		poner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.ponerAleatorio();
			}
		});
		botInf1.add(poner);

		// Boton Salir:
		salir = new JButton("Salir");
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.getJugadorConTurno().PierdeTurno();
				close();
			}
		});
		botInf1.add(salir);
		this.add(botInf1);
	}

	@Override
	public void onReset(TableroInmutable tablero, Ficha turno) {
		// Si el jugador al que corresponde el turno es aleatorio,
		// se desactiva el botón que permite realizar un movimiento aleatorio
		if (!ventana.getJugadorConTurno().esHumano())
			poner.setEnabled(false);
		else
			poner.setEnabled(true);
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {
		poner.setEnabled(false);
	}

	@Override
	public void onCambioJuego(TableroInmutable tablero, Ficha turno) {
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
		// Si el jugador al que corresponde el turno es aleatorio,
		// se desactiva el botón que permite realizar un movimiento aleatorio
		if (!ventana.getJugadorConTurno().esHumano())
			poner.setEnabled(false);
		else
			poner.setEnabled(true);
	}

	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
	}

	/** Muestra una ventana de confirmacion al salir */
	private void close() {
		if (JOptionPane.showConfirmDialog(this,
				"¿Desea realmente salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}
}
