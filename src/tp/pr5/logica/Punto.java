package tp.pr5.logica;

/**
 * La clase Punto crea objetos que guardan coordenadas enteras X e Y
 */
public class Punto {
	private int x;
	private int y;

	public Punto(int a, int b) {
		x = a;
		y = b;
	}

	/**
	 * Devuelve la coordenada X del punto
	 * 
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Devuelve la coordenada Y del punto
	 * 
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Dos puntos son iguales cuando sus dos coordenadas coinciden
	 * 
	 * @param p
	 * @return
	 */
	public boolean equals(Punto p) {
		return (this.x == p.getX() && this.y == p.getY());
	}
}
