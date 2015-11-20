package tp.pr5.swing;

import tp.pr5.control.ControladorVentana;

public class JugadorSwingAleatorio implements JugadorSwing {
	private HebraJugadorAleatorio h;
	private ControladorVentana control;

	public JugadorSwingAleatorio(ControladorVentana c) {
		control = c;
	}

	@Override
	public void TableroPulsado(int x, int y) {
	}

	@Override
	public void RecibeTurno() {

		h = new HebraJugadorAleatorio(control);
		h.start();
	}

	@Override
	public void PierdeTurno() {
		if (h != null) {
			h.interrupt();
			h = null;
		}
	}

	@Override
	public boolean esHumano() {
		return false;
	}
}
