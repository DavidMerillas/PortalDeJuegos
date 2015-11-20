package tp.pr5.vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tp.pr5.control.ControladorVentana;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.ObservadorPartida;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.swing.Ventana;

public class PanelCambioJuego extends JPanel implements ObservadorPartida {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControladorVentana control;
	private JPanel panelJuego;
	private String[] juegosString = { "CONECTA4", "COMPLICA", "GRAVITY",
			"REVERSI" };
	private JComboBox<String> juegosLista;
	private JButton cambiar;
	private JPanel dimensiones;

	private Ventana ventana;

	public PanelCambioJuego(ControladorVentana c, Ventana v) {
		control = c;
		control.addObservador(this);

		panelJuego = new JPanel();
		panelJuego.setLayout(new BorderLayout());

		ventana = v;

		// Panel dimensiones tablero Gravity:
		dimensiones = new JPanel();
		JLabel Jfilas = new JLabel("Filas");
		JLabel Jcolumnas = new JLabel("Columnas");
		final JTextField tFilas = new JTextField("  4   ");
		final JTextField tColumnas = new JTextField("  4  ");
		dimensiones.setVisible(false);
		dimensiones.add(Jfilas);
		dimensiones.add(tFilas);
		dimensiones.add(Jcolumnas);
		dimensiones.add(tColumnas);
		panelJuego.add(dimensiones);

		// Lista de juegos:
		juegosLista = new JComboBox<String>(juegosString);
		panelJuego.add(juegosLista, BorderLayout.NORTH);
		juegosLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (juegosLista.getSelectedItem().equals("GRAVITY")) {
					dimensiones.setVisible(true);

				} else
					dimensiones.setVisible(false);
			}
		});

		// Boton Cambiar:
		cambiar = new JButton("Cambiar");
		cambiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Se guarda el juego seleccionado en la lista de juegos:
				String eleccionUsuario = (String) juegosLista.getSelectedItem();

				// Se guardan las dimensiones del tablero Gravity introducidas:
				int filas = Integer.parseInt(tFilas.getText().trim());
				int columnas = Integer.parseInt(tColumnas.getText().trim());

				ventana.getJugadorConTurno().PierdeTurno();
				control.cambiarJuego(eleccionUsuario.toLowerCase(), columnas,
						filas);
			}
		});
		panelJuego.add(cambiar, BorderLayout.SOUTH);

		// Borde:
		panelJuego.setBorder(BorderFactory.createLineBorder(Color.gray));
		panelJuego.setBorder(BorderFactory
				.createTitledBorder("Cambio de Juego"));
		panelJuego.setPreferredSize(new Dimension(198, 100));
		this.add(panelJuego);
	}

	@Override
	public void onReset(TableroInmutable tablero, Ficha turno) {
		ActivaBotones();
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {
		DesactivaBotones();
	}

	@Override
	public void onCambioJuego(TableroInmutable tablero, Ficha turno) {
		ActivaBotones();
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
	 * Activa los botones y subpaneles que conforman el panel
	 */
	private void ActivaBotones() {
		cambiar.setEnabled(true);
		dimensiones.setEnabled(true);
		juegosLista.setEnabled(true);
	}

	/**
	 * Desactiva los botones y subpaneles que conforman el panel
	 */
	private void DesactivaBotones() {
		cambiar.setEnabled(false);
		dimensiones.setEnabled(false);
		juegosLista.setEnabled(false);
	}

}
