package tp.pr5.control;

import java.util.ArrayList;
import java.util.List;

import tp.pr5.comandos.Comando;
import tp.pr5.comandos.ComandoAyuda;
import tp.pr5.comandos.ComandoDeshacer;
import tp.pr5.comandos.ComandoJugador;
import tp.pr5.comandos.ComandoJugar;
import tp.pr5.comandos.ComandoPoner;
import tp.pr5.comandos.ComandoReiniciar;
import tp.pr5.comandos.ComandoSalir;

public class GestorComandos {

	private static List<Comando> listaComandos = new ArrayList<>();
	private Comando c = null;

	public GestorComandos(ControladorConsola controlador) {
		listaComandos.add(new ComandoPoner(controlador));
		listaComandos.add(new ComandoDeshacer(controlador));
		listaComandos.add(new ComandoReiniciar(controlador));
		listaComandos.add(new ComandoJugar(controlador));
		listaComandos.add(new ComandoJugador(controlador));
		listaComandos.add(new ComandoSalir());
		listaComandos.add(new ComandoAyuda());
	}

	/**
	 * Recorre todos los comandos de la lista de comandos, ejecutando su
	 * correspondiente metodo "analizar"
	 * 
	 * @param cad
	 *            - String con el comando introducido por el usuario
	 * @return c - comando que ha devuelto "true" tras ejecutar su metodo
	 *         "analizar"
	 */
	public Comando parser(String cad) {
		boolean parar = false;
		c = null;

		for (int i = 0; i < listaComandos.size() && !parar; i++) {

			if (listaComandos.get(i).analizar(cad)) {
				c = listaComandos.get(i);
				parar = true;
			}
		}
		return c;
	}

	/**
	 * Construye una unica cadena que contiene todas las ayudas correspondientes
	 * a cada Comando de la lista de comandos
	 * 
	 * @return cadena - Todas las ayudas de cada comando
	 */
	public static String ayudasComandos() {
		StringBuilder cadena = new StringBuilder();
		for (Comando i : listaComandos)
			cadena.append(i.ayuda() + "\n");
		return cadena.toString();
	}
}
