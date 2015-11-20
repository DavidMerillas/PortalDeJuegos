package tp.pr5.logica;

import java.util.ArrayList;

/**
 * Clase que almacena la informaci�n de un tablero. El tamano se fija en el
 * momento de la construccion, y contiene metodos para acceder a la informacion
 * de cada celda y para colocar fichas.
 */

public class Tablero implements TableroInmutable {

	private Ficha[][] tablero;
	private int ancho;
	private int alto;

	// Atributo que solo es utilizado en el juego Reversi
	// Guarda la lista de Movimientos validos que hay en el tablero
	// Se actualiza con cada turno
	private ArrayList<Movimiento> movimientosValidos;

	/**
	 * Construye un tablero vacio
	 * 
	 * @param tx
	 *            - numero de columnas que tendra el tablero
	 * @param ty
	 *            - numero de filas que tendra el tablero
	 */
	public Tablero(int tx, int ty) {
		ancho = tx;
		alto = ty;
		if (ancho <= 0 || alto <= 0) {
			ancho = 1;
			alto = 1;
		}
		tablero = new Ficha[ancho][alto];
		movimientosValidos = new ArrayList<>();

		reset();
	}

	/**
	 * Devuelve la lista de movimientos válidos del tablero
	 * 
	 * @return
	 */
	public ArrayList<Movimiento> getMovimientosValidos() {
		return movimientosValidos;
	}

	/**
	 * Agrega un movimiento valido a la lista de movimientos validos del tablero
	 * 
	 * @param newList
	 */
	protected void addMovimientoValido(Movimiento mov) {
		movimientosValidos.add(mov);
	}

	/**
	 * Vacía la lista de movimientos validos
	 */
	protected void vaciaMovimientosValidos() {
		movimientosValidos.clear();
	}

	/**
	 * Vacia todas las casillas del tablero. Todas las casillas == Ficha.VACIA
	 */
	public void reset() {

		for (int columna = 0; columna < ancho; columna++)
			for (int fila = 0; fila < alto; fila++) {
				tablero[columna][fila] = Ficha.VACIA;
			}
	}

	@Override
	public int getAncho() {
		return ancho;
	}

	@Override
	public int getAlto() {
		return alto;
	}

	@Override
	public Ficha getCasilla(int x, int y) {
		Ficha color;
		if (validaCasilla(x, y))
			color = tablero[x - 1][y - 1];
		else
			color = Ficha.VACIA;

		return color;
	}

	/**
	 * Permite especificar el nuevo contenido de una casilla. Tambien puede
	 * utilizarse para quitar una ficha
	 * 
	 * @param x
	 *            - Numero de columna
	 * @param y
	 *            - Numero de fila
	 * @param color
	 *            - Color de la ficha que se desea colocar en la posicion (x,y)
	 */
	public void setCasilla(int x, int y, Ficha color) {
		if (validaCasilla(x, y))
			tablero[x - 1][y - 1] = color;
	}

	/**
	 * Muestra tablero por pantalla en su estado actual
	 */
	public String toString() {
		// Se crea variable StringBuilder con tama�o de 72 caracteres (8 columna
		// x 9 fila)
		// que es el tama�o del tablero por defecto del Conecta 4:
		StringBuilder cadena = new StringBuilder(8 * 9);

		for (int y = 0; y < alto; y++) {
			cadena.append('|');
			for (int x = 0; x < ancho; x++) {
				cadena.append(tablero[x][y].getSimbolo());
			}
			cadena.append("|\n");
		}
		cadena.append("+");
		for (int x = 0; x < ancho; x++)
			cadena.append('-');
		cadena.append("+\n ");

		for (int x = 0; x < ancho; x++)
			/*
			 * Aqui es donde imprimimos los numeros que van debajo del tablero.
			 * Nosotros imprimiamos numeros de 1 a n, pero el validador solo
			 * imprime numeros de 1 al 9 y luego en el 10 imprime un 0, en 11 un
			 * 1 y asi sucesivamente, asique cambio el metodo para que de el
			 * 100% en el validador.
			 */
			if (x < 9)
				cadena.append(x + 1);
			else
				cadena.append(x - 9);
		cadena.append('\n');

		return cadena.toString();
	}

	/**
	 * Comprueba si el tablero esta totalmente lleno (no hay
	 * casillas==Ficha.VACIA).
	 * 
	 * @return tableroLleno==true si el tablero esta completamente lleno.
	 */
	public boolean tableroLleno() {
		boolean tableroLleno = true;
		for (int col = 0; col < ancho; col++)
			for (int fil = 0; fil < alto; fil++)
				if (tablero[col][fil] == Ficha.VACIA)
					tableroLleno = false;

		return tableroLleno;
	}

	/**
	 * Comprueba si es una casilla correcta
	 * 
	 * @param x
	 *            - Numero de columna
	 * @param y
	 *            - Numero de fila
	 * @return true si es correcta
	 */
	private boolean validaCasilla(int x, int y) {
		return (x >= 1 && x <= ancho && y >= 1 && y <= alto);
	}
}
