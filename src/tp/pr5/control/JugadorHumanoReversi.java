package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.*;

public class JugadorHumanoReversi implements Jugador {

	private Scanner sc;

	public JugadorHumanoReversi(Scanner in) {
		sc = in;
	}

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {

		System.out.print("Introduce la columna: ");
		int col = sc.nextInt();

		System.out.print("Introduce la fila: ");
		int fil = sc.nextInt();

		// Se limpia buffer del escaner:
		sc.nextLine();

		return (new MovimientoReversi(col, fil, color));
	}
}
