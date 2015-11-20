package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoConecta4;
import tp.pr5.logica.Tablero;

public class JugadorAleatorioConecta4 implements Jugador {

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		boolean movOK = false;
		int colRnd = 0;

		while (!movOK) {
			// Se genera un entero en el rango del numero de columnas del
			// tablero
			colRnd = (int) (Math.random() * tab.getAncho() + 1);

			// Se comprueba si dicha columna está llena
			if (tab.getCasilla(colRnd, 1) == Ficha.VACIA)
				// Si lo está, se detiene la busqueda
				// Si no, se repite el procedimiento
				// hasta encontrar una columna con posiciones vacías
				movOK = true;
		}
		return new MovimientoConecta4(colRnd, color);
	}
}
