package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoComplica;
import tp.pr5.logica.Tablero;

public class JugadorAleatorioComplica implements Jugador {

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {

		// Se genera un entero en el rango del numero de columnas del tablero
		int colRnd = (int) (Math.random() * tab.getAncho() + 1);

		return new MovimientoComplica(colRnd, color);
	}
}
