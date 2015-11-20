package tp.pr5.logica;

public class MovimientoGravity extends Movimiento {

	public MovimientoGravity(int x, int y, Ficha color) {
		super(x, color);
		fila = y;
	}

	@Override
	public boolean posicionValida(Tablero tab) {
		return (columna >= 1 && columna <= tab.getAncho() && fila >= 1 && fila <= tab
				.getAlto());
	}

	@Override
	public boolean ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		boolean MovimientoOK = false;
		// Contadores:
		int izq = 0, der = 0, sup = 0, inf = 0;

		if (posicionValida(tab)) {
			if (tab.getCasilla(columna, fila) == Ficha.VACIA) {

				// Se calcula la distancia desde la posicion indicada
				// por el jugador hasta los bordes respectivos del tablero:
				// Izquierdo:
				for (int i = columna - 1; i >= 1; i--)
					izq++;
				// Derecho:
				for (int i = columna + 1; i <= tab.getAncho(); i++)
					der++;
				// Superior:
				for (int i = fila - 1; i >= 1; i--)
					sup++;
				// Inferior:
				for (int i = fila + 1; i <= tab.getAlto(); i++)
					inf++;

				// Con las distancias hacia cada borde calculadas,
				// se coloca la ficha en su posici�n final:
				calculaPosicion(tab, izq, der, sup, inf);
				MovimientoOK = true;
			} else
				throw (new MovimientoInvalido("Casilla ocupada."));
		} else
			throw (new MovimientoInvalido("Posición incorrecta."));
		return MovimientoOK;
	}

	/**
	 * Determina el/los borde/s que atraen a la ficha colocada por el jugador y
	 * la colocan en la posicion final
	 * 
	 * @param tab
	 *            - tablero en el que se est� jugando la partida
	 * @param izq
	 *            - contador posiciones desde ficha hasta borde izquierdo
	 * @param der
	 *            - contador posiciones desde ficha hasta borde derecho
	 * @param sup
	 *            - contador posiciones desde ficha hasta borde superior
	 * @param inf
	 *            - contador posiciones desde ficha hasta borde inferior
	 */
	private void calculaPosicion(Tablero tab, int izq, int der, int sup, int inf) {

		// Case Movimiento hacia el borde izquierdo:
		if ((izq < der && izq < sup && izq < inf) || (sup == inf && izq < der))
			colocaFicha(-1, 0, jugador, tab);

		// Case Movimiento hacia el borde derecho:
		else if ((der < izq && der < sup && der < inf)
				|| (sup == inf && der < izq))
			colocaFicha(1, 0, jugador, tab);

		// Case Movimiento hacia el borde superior:
		else if ((sup < izq && sup < der && sup < inf)
				|| (izq == der && sup < inf))
			colocaFicha(0, -1, jugador, tab);

		// Case Movimiento hacia el borde inferior:
		else if ((inf < izq && inf < der && inf < sup)
				|| (izq == der && inf < sup))
			colocaFicha(0, 1, jugador, tab);

		// Case Centro exacto del tablero:
		else if (izq == der && sup == inf)
			tab.setCasilla(columna, fila, jugador);

		// Case Movimientos diagonales:
		else {
			// Hacia esquina superior izquierda:
			if (izq == sup && menor(izq, sup) < menor(der, inf))
				colocaFicha(-1, -1, jugador, tab);
			// Hacia esquina inferior izquierda:
			else if (izq == inf && menor(izq, inf) < menor(der, sup))
				colocaFicha(-1, 1, jugador, tab);
			// Hacia esquina superior derecha:
			else if (der == sup && menor(der, sup) < menor(izq, inf))
				colocaFicha(1, -1, jugador, tab);
			// Hacia esquina inferior derecha:
			else
				colocaFicha(1, 1, jugador, tab);
		}
	}

	/**
	 * Comprueba casillas del tablero en una determinada direccion verificando
	 * si estan ocupadas o vacias y finalmente colocando una ficha en la ultima
	 * posicion vacia en esa direccion
	 * 
	 * @param dx
	 *            - direccion hacia la que hay que avanzar en el eje x
	 * @param dy
	 *            - direccion hacia la que hay que avanzar en el eje y
	 * @param color
	 *            - color de la ficha que se desea colocar
	 * @param tab
	 *            - tablero en el que se está jugando
	 */
	private void colocaFicha(int dx, int dy, Ficha color, Tablero tab) {
		boolean parar = false;

		while (!parar) {
			// Si se encuentra una casilla ocupada, se coloca la ficha en la
			// anterior casila:
			if (tab.getCasilla(columna + dx, fila + dy) != Ficha.VACIA) {
				tab.setCasilla(columna, fila, color);
				parar = true;

				// Si se encuentra una posicion invalida es porque nos hemos
				// salido de los limites del tablero
				// por lo que colocamos la ficha en la casilla anterior:
			} else if (!posicionValida(tab)) {
				columna = columna - dx;
				fila = fila - dy;
				tab.setCasilla(columna, fila, color);
				parar = true;

				// Si no se encuentra una casilla ocupada o fuera de los limites
				// seguimos buscando en la siguiente posicion:
			} else {
				columna = columna + dx;
				fila = fila + dy;
			}
		}
	}

	/**
	 * Calcula y devuelve el menor de dos enteros, si ambos son iguales,
	 * devuelve el primero de ellos
	 * 
	 * @param a
	 * @param b
	 * @return menor entre a y b
	 */
	private int menor(int a, int b) {
		int menor;

		if (a < b)
			menor = a;
		else if (a > b)
			menor = b;
		else
			menor = a;

		return menor;
	}
}
