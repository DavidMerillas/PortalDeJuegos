package tp.pr5.logica;

/**
 * Clase que implementa el movimiento para el juego del Conecta 4. Se deben
 * implementar los m�todos abstractos de la clase padre.
 * 
 * @author David Merillas Pinar / Simon Da Silva
 */

public class MovimientoConecta4 extends Movimiento {

	public MovimientoConecta4(int donde, Ficha color) {
		super(donde, color);
	}

	@Override
	public boolean ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		boolean movimientoOK = super.ejecutaMovimiento(tab);

		if (!movimientoOK)
			throw (new MovimientoInvalido("Columna llena."));

		return movimientoOK;
	}
}
