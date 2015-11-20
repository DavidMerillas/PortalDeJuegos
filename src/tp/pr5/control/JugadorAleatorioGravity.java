package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoGravity;
import tp.pr5.logica.Tablero;

public class JugadorAleatorioGravity implements Jugador {

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		boolean movOK = false;
		int colRnd = 0;
		int filRnd = 0;

		while (!movOK) {

			// Se generan coordenadas aleatoriamente que se encuentren dentro
			// de las dimensiones del tablero
			colRnd = (int) (Math.random() * tab.getAncho() + 1);
			filRnd = (int) (Math.random() * tab.getAlto() + 1);

			// Se comprueba si dichas coordenadas corresponden a una casilla
			// vacía
			// del tablero
			if (tab.getCasilla(colRnd, filRnd) == Ficha.VACIA)
				movOK = true;
		}
		return new MovimientoGravity(colRnd, filRnd, color);
	}
}
