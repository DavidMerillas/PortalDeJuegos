package tp.pr5.swing;

import tp.pr5.control.ControladorVentana;

public class JugadorSwingHumano implements JugadorSwing {

	private ControladorVentana control;

	public JugadorSwingHumano(ControladorVentana c) {
		control = c;
	}

	@Override
	public void TableroPulsado(int x, int y) {
		control.poner(x, y);
	}

	@Override
	public void RecibeTurno() {
	}

	@Override
	public void PierdeTurno() {
	}

	@Override
	public boolean esHumano() {
		return true;
	}
}
