package tp.pr5.comandos;

import tp.pr5.logica.MovimientoInvalido;

public interface Comando {

	/**
	 * Hace una comparacion entre cadenas string. Se usa para parsear los
	 * comandos desde el Gestor de comandos Cada Comando compara el argumento
	 * con una cadena distinta propia
	 * 
	 * @param cad
	 * @return true si el argumento es igual a la cadena propia de cada Comando
	 */
	public boolean analizar(String cad);

	/**
	 * Metodo que lleva a cabo las acciones que se esperan de cada uno de los
	 * Comandos
	 * 
	 * @throws MovimientoInvalido
	 */
	public void ejecutar() throws MovimientoInvalido;

	/**
	 * Metodo que devuelve el atributo estático "ayuda" de cada metodo
	 * 
	 * @return String que explica brevemente el nombre y funcion de cada Comando
	 */
	public String ayuda();
}
