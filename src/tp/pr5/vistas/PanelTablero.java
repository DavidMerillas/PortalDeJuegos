package tp.pr5.vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
//import javax.swing.border.Border;
//import javax.swing.border.LineBorder;

import tp.pr5.control.ControladorVentana;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.ObservadorPartida;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.swing.Casilla;
import tp.pr5.swing.Ventana;

public class PanelTablero extends JPanel implements ObservadorPartida {

	private static final long serialVersionUID = 1L;
	private ControladorVentana control;
	private JPanel panelTablero;
	private Casilla casilla;

	// Matriz de botones (conforman el tablero de la VENTANA):
	private JButton[][] matrizCasillas;

	private Ventana ventana;

	public PanelTablero(ControladorVentana c, Ventana v) {
		control = c;
		control.addObservador(this);
		ventana = v;

		int columnas = control.getAnchoTablero();
		int filas = control.getAltoTablero();

		creaPanelTablero(columnas, filas);
		actualizarTablero(control.getPartida().getTablero(), Ficha.NEGRA);
	}

	@Override
	public void onReset(TableroInmutable tablero, Ficha turno) {

		actualizarTablero(tablero, turno);
		ventana.getJugadorConTurno().PierdeTurno();
		ventana.actualizaJugadorConTurno(turno);
		ventana.getJugadorConTurno().RecibeTurno();
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {

		actualizarTablero(tablero, ganador);
		ventana.getJugadorConTurno().PierdeTurno();
	}

	@Override
	public void onCambioJuego(TableroInmutable tablero, Ficha turno) {

		setPanelTablero(tablero.getAncho(), tablero.getAlto());
		ventana.getJugadorConTurno().PierdeTurno();
		ventana.actualizaJugadorConTurno(turno);
		ventana.getJugadorConTurno().RecibeTurno();
	}

	@Override
	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {
	}

	@Override
	public void onUndo(TableroInmutable tablero, Ficha turno, boolean HayMas) {

		actualizarTablero(tablero, turno);
		ventana.getJugadorConTurno().PierdeTurno();
		ventana.actualizaJugadorConTurno(turno);
		ventana.getJugadorConTurno().RecibeTurno();
	}

	@Override
	public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador,
			Ficha turno) {

		actualizarTablero(tablero, turno);
		ventana.getJugadorConTurno().PierdeTurno();
		ventana.actualizaJugadorConTurno(turno);
		ventana.getJugadorConTurno().RecibeTurno();
	}

	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
	}

	/**
	 * Método que recibe un tablero, comprueba el estado de cada una de sus
	 * casillas y actualiza correctamente la vista del tablero en la ventana.
	 * 
	 * @param tablero
	 * @param turno
	 */
	private void actualizarTablero(TableroInmutable tablero, Ficha turno) {
		for (int i = 1; i <= tablero.getAncho(); i++)
			for (int j = 1; j <= tablero.getAlto(); j++)
				setCasilla(i, j, tablero.getCasilla(i, j).toString());
	}

	/**
	 * Pinta la casilla del color correspondiente
	 */
	private void setCasilla(int col, int fil, String turno) {
		if (turno.equals("blancas"))
			matrizCasillas[col - 1][fil - 1].setBackground(Color.white);

		else if (turno.equals("negras"))
			matrizCasillas[col - 1][fil - 1].setBackground(Color.black);

		else
			matrizCasillas[col - 1][fil - 1].setBackground(Color.gray);
	}

	/**
	 * Elimina el tablero existente en la ventana y crear uno nuevo del tamaño
	 * deseado. Su comportamiento también se puede describir como "actualizar"
	 * las dimensiones del tablero
	 * 
	 * @param columnas
	 * @param filas
	 **/
	private void setPanelTablero(int columnas, int filas) {
		this.remove(panelTablero);
		creaPanelTablero(columnas, filas);
		this.validate();
		this.repaint();
	}

	/**
	 * Crea un tablero con las dimensiones deseadas
	 * 
	 * @param columnas
	 * @param filas
	 */
	private void creaPanelTablero(int columnas, int filas) {
		panelTablero = new JPanel();
		panelTablero.setPreferredSize(new Dimension(350, 350));
		matrizCasillas = new JButton[columnas][filas];
		panelTablero.setLayout(new GridLayout(filas, columnas));

		for (int i = 1; i <= filas; i++) {
			for (int j = 1; j <= columnas; j++) {
				casilla = new Casilla(i, j);
				casilla.setBackground(Color.gray);
				casilla.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Casilla a = (Casilla) e.getSource();
						ventana.getJugadorConTurno().TableroPulsado(a.getCol(),
								a.getFil());
					}
				});
				matrizCasillas[j - 1][i - 1] = casilla;
				panelTablero.add(casilla);
			}
			// Borde:
			panelTablero.setBorder(BorderFactory.createLineBorder(Color.gray));
			panelTablero.setBorder(BorderFactory.createTitledBorder("Tablero"));

			this.add(panelTablero);
		}
	}
}