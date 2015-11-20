package tp.pr5.logica;

/**
 * Clase para representar la informacion y metodos relacionados con la pila
 * 
 * @author David Merillas Pinar / Simon Da Silva
 */

public class Pila {
	private Movimiento[] undoStack;
	private int numUndo;

	public Pila() {
		undoStack = new Movimiento[10];
		reset();
	}

	/**
	 * Metodo que (re)inicia el contador de la pila a 0
	 */
	public void reset() {
		// Se libera la memoria de cada posici�n de la pila:
		// for (int i = 0; i <= numUndo; i++)
		// undoStack[i] = null;
		// La cima se pone a 0:
		numUndo = 0;
	}

	public Movimiento cima() {
		Movimiento mov = null;

		if (!isVacia())
			mov = undoStack[numUndo - 1];

		return mov;
	}

	/**
	 * Metodo que almacena el movimiento en la pila.
	 * 
	 * @param mov
	 *            - Movimiento que se guardara en la pila.
	 */
	public void guardarMovimiento(Movimiento mov) {
		// En caso de que la pila esté llena, se elimina el movimiento de la
		// posicion 0
		// y se abre espacio para guardar un nuevo movimiento en la posición 9:
		if (numUndo == 10) {
			for (int i = 1; i < numUndo; i++)
				undoStack[i - 1] = undoStack[i];
			numUndo -= 1;
		}

		//
		undoStack[numUndo] = mov;
		numUndo += 1;
	}

	/**
	 * Metodo que desapila el �ltimo elemento de la pila.
	 */
	public void desapila() {
		if (!isVacia()) {

			/*
			 * Comentado debido a que los tests fallan si ponemos a null un
			 * elemento desapilado. Nos lo pidio la profesora que lo hicieramos
			 * en la pr2.
			 * 
			 * //Se libera la memoria del movimiento a desapilar:
			 * undoStack[numUndo] = null;
			 * 
			 * Se resta una posicion a la cima:
			 */
			numUndo--;
		}
	}

	public boolean isVacia() {
		return (numUndo == 0);
	}
}
