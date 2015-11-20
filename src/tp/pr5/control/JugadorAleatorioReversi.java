package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoReversi;
import tp.pr5.logica.Tablero;

public class JugadorAleatorioReversi implements Jugador {

	/**
	 * De entre los movimientos validos posibles, devuelve aquel que mayor
	 * numero de fichas del jugador contrario flanquee. En caso de haber 2 o más
	 * movimientos que flanqueen el mismo nº de fichas, devolvera el primero que
	 * haya encontrado de ellos.
	 */

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {

		int nFichasFlanqueadas = 0;
		int indice = 0;

		for (int i = 0; i < tab.getMovimientosValidos().size(); i++) {
			MovimientoReversi mov = (MovimientoReversi) tab
					.getMovimientosValidos().get(i);
			if (mov.getFichasFlanqueadas().size() > nFichasFlanqueadas) {
				indice = i;
				nFichasFlanqueadas = mov.getFichasFlanqueadas().size();
			}
		}

		return tab.getMovimientosValidos().get(indice);

		// Otra implementación posible sería la siguiente, la cual devuelve
		// aleatoriamente
		// cualquiera de los movimientos validos posibles, sin distincion de
		// cual es mas eficiente:
		// int random = (int) (Math.random() *
		// tab.getMovimientosValidos().size());
		// return tab.getMovimientosValidos().get(random);
	}
}
