package tp.pr5.logica;

/**
 * Esta interfaz sirve para restringir la información a la que tiene acceso un
 * usuario de un tablero
 */

public interface TableroInmutable {

	/**
	 * Metodo para obtener el ancho del tablero.
	 * 
	 * @return ancho del tablero
	 */
	public int getAncho();

	/**
	 * Metodo para obtener el alto del tablero.
	 * 
	 * @return alto del tablero
	 */
	public int getAlto();

	/**
	 * Metodo para acceder a la informacion de una casilla o celda concreta
	 * 
	 * @param x
	 *            - Numero de columna
	 * @param y
	 *            - Numero de fila
	 * @return Informacion de la casilla. Si la casilla no es valida, devuelve
	 *         Ficha.VACIA
	 */
	public Ficha getCasilla(int x, int y);
}
