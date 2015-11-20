package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoConecta4;
import tp.pr5.logica.Tablero;

public class JugadorHumanoC4 implements Jugador {

	private Scanner sc;

	public JugadorHumanoC4(Scanner in) {
		sc = in;
	}

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {

		System.out.print("Introduce la columna: ");
		int col = sc.nextInt();

		// Se limpia buffer del escaner:
		sc.nextLine();

		return (new MovimientoConecta4(col, color));
	}
}
