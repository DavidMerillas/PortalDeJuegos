package tp.pr5.logica;

/**
 * Enumerado de Fichas. Repesenta la informacion del color de una ficha.
 * 
 * @author David Merillas Pinar / Simon Da Silva
 */
public enum Ficha {
	VACIA("", ' '), BLANCA("blancas", 'O'), NEGRA("negras", 'X');

	private String cadena;
	private char simbolo;

	private Ficha(String cad, char simb) {
		cadena = cad;
		simbolo = simb;
	}

	public char getSimbolo() {
		return simbolo;
	}

	public String toString() {
		return cadena;
	}
}