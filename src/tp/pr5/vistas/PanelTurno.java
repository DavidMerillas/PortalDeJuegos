package tp.pr5.vistas;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.pr5.control.ControladorVentana;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.ObservadorPartida;
import tp.pr5.logica.TableroInmutable;

public class PanelTurno extends JPanel implements ObservadorPartida {

	private JPanel panelTurno;
	private JLabel turnoActual;

	private static final long serialVersionUID = 1L;

	public PanelTurno(ControladorVentana c) {
		c.addObservador(this);

		panelTurno = new JPanel();
		turnoActual = new JLabel("Juegan " + c.getTurno());
		turnoActual.setForeground(Color.BLUE);
		panelTurno.add(turnoActual);
		this.add(panelTurno);
	}

	public void onReset(TableroInmutable tablero, Ficha turno) {
		turnoActual.setText("Juegan " + turno.toString());
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {
		if (ganador != Ficha.VACIA) {
			turnoActual.setText("Partida terminada");
			JOptionPane.showMessageDialog(this,
					"Ganan las " + ganador.toString());
		} else {
			turnoActual.setText("Partida terminada en tablas");
		}
	}

	@Override
	public void onCambioJuego(TableroInmutable tablero, Ficha turno) {
		turnoActual.setText("Juegan " + turno.toString());
	}

	@Override
	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {
		turnoActual.setText("Imposible deshacer - Juegan " + turno.toString());
	}

	@Override
	public void onUndo(TableroInmutable tablero, Ficha turno, boolean HayMas) {
		turnoActual.setText("Juegan " + turno.toString());
	}

	@Override
	public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador,
			Ficha turno) {
		turnoActual.setText("Juegan " + turno.toString());
	}

	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
	}
}
