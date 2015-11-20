package tp.pr5.swing;

import javax.swing.JButton;

/**
 * Esta clase representa cada uno de los botones que componen el tablero de la
 * pantalla, y guarda las coordenadas de cada uno de ellos
 */
public class Casilla extends JButton {

	private static final long serialVersionUID = 1L;
	private int col;
	private int fil;

	public Casilla(int i, int j) {
		col = j;
		fil = i;
	}

	/**
	 * Devuelve la columna a la que pertenece la casilla
	 * 
	 * @return
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Devuelve la fila a la que pertenece la casilla
	 * 
	 * @return
	 */
	public int getFil() {
		return fil;
	}
}
