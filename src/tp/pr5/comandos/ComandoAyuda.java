package tp.pr5.comandos;

import tp.pr5.control.GestorComandos;

public class ComandoAyuda implements Comando {

	// String que describe el nombre y formato de entrada del comando,
	// asi como una breve explicacion de su funcion:
	private static final String ayuda = "AYUDA: muestra esta ayuda.";

	@Override
	public boolean analizar(String cad) {
		return (cad.equals("ayuda"));
	}

	@Override
	/**
	 * Muestra por pantalla el listado de "ayudas" 
	 * correspondientes a cada comando del Gestor de comandos
	 */
	public void ejecutar() {
		System.out.println("Los comandos disponibles son:\n\n"
				+ GestorComandos.ayudasComandos());
	}

	@Override
	public String ayuda() {
		return ayuda;
	}
}