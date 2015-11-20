package tp.pr5.swing;

import javax.swing.SwingUtilities;

import tp.pr5.control.ControladorVentana;

public class HebraJugadorAleatorio extends Thread {

	private ControladorVentana control;

	public HebraJugadorAleatorio(ControladorVentana c) {
		control = c;
	}

	@Override
	public void run() {
		try {
			// Se produce una pausa de 1200 ms
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			return;
		}

		// Posteriormente se pide a la hebra de Swing
		// (mediante un metodo del controlador) que
		// cree y ejecute un movimiento aleatorio
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				control.ponerAleatorio();
			}
		});
	}
}
