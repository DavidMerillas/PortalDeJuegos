package tp.pr5;

import java.util.Scanner;

import tp.pr5.control.*;
import tp.pr5.logica.Partida;
import tp.pr5.swing.Ventana;
import tp.pr5.vistas.*;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

/**
 * Clase que contiene el punto de entrada a la aplicacion.
 */
public class Main {

	/**
	 * Método principal de la aplicación.
	 * 
	 * @param args
	 *            - Argumentos pasados a la aplicación.
	 * @throws ParseException
	 */
	public static void main(java.lang.String[] args) throws ParseException {

		FactoriaTipoJuego factoria = new FactoriaConecta4();

		boolean error = false;

		// Definir las opciones:
		Options options = new Options();

		options.addOption("g", "game", true,
				"Tipo de juego (c4, co, gr). Por defecto, c4.");
		options.getOption("g").setArgName("game");

		options.addOption("h", "help", false, "Muestra esta ayuda.");

		options.addOption("u", "ui", true,
				"Tipo de interfaz (console, window). Por defecto, console");
		options.getOption("u").setArgName("ui");

		options.addOption("x", "tamX", true,
				"Número de columnas del tablero (sólo para Gravity). Por defecto, 10.");
		options.getOption("x").setArgName("columnNumber");

		options.addOption("y", "tamY", true,
				"Número de filas del tablero (sólo para Gravity). Por defecto, 10.");
		options.getOption("y").setArgName("rowNumber");

		try {
			// Analizar los argumentos
			CommandLineParser parser = new PosixParser();
			CommandLine cmd = parser.parse(options, args);

			// Mostrar la ayuda:
			if (cmd.hasOption("help")) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp(Main.class.getName(), options, true);
				error = true;
			}

			// Elegir juego:
			else if (cmd.hasOption("game") || cmd.hasOption("ui")) {
				if (cmd.hasOption("game")) {
					String juego = cmd.getOptionValue("game");

					if (juego.equals("co")) {
						factoria = new FactoriaComplica();
					} else if (juego.equals("gr")) {
						int col = 10;
						int fil = 10;

						// Cambiar numero columnas:
						if (cmd.hasOption("tamX"))
							col = Integer.parseInt(cmd.getOptionValue("tamX"));

						// Cambiar numero filas:
						if (cmd.hasOption("tamY"))
							fil = Integer.parseInt(cmd.getOptionValue("tamY"));

						factoria = new FactoriaGravity(col, fil);
					} else if (juego.equals("rv")) {
						factoria = new FactoriaReversi();
					}

					if (cmd.getArgs().length > 0) {
						error = true;
						String s = "";
						for (String a : cmd.getArgs())
							s = s + a + " ";
						s = s.substring(0, s.length() - 1);
						throw new ParseException("Argumentos no entendidos: "
								+ s);
					}
				}
				// Tipo vista:
				if (cmd.hasOption("ui")) {
					String tipoVista = cmd.getOptionValue("ui");

					Partida partida = new Partida(factoria.creaReglas());

					// Caso consola:
					if (tipoVista.equals("console")) {

						Scanner sc = new Scanner(System.in);
						ControladorConsola control = new ControladorConsola(
								factoria, partida, sc);
						VistaConsola vista = new VistaConsola(control);

						vista.run();
						sc.close();
						error = true;
					}

					// Caso ventana:
					else if (tipoVista.equals("window")) {

						ControladorVentana control = new ControladorVentana(
								factoria, partida);
						new Ventana(control);
						error = true;
					}

					else {
						error = true;
						throw new ParseException("Juego incorrecto.");
					}
				}
			}

			if (cmd.getArgs().length > 0) {
				error = true;
				throw new ParseException(" Unreconized option:  ");
			}
		}

		catch (ParseException e) {
			error = true;
			System.err.println("Uso incorrecto: " + e.getMessage());
			System.err.println("Use -h|--help para más detalles.");
			System.exit(1);
		} catch (NumberFormatException e) {
			System.err.println("Uso incorrecto: Argumentos no entendidos: "
					+ e.getMessage());
			System.err.println("Use -h|--help para más detalles.");
		}
		if (!error) {
			// Caso por defecto:
			Partida p = new Partida(factoria.creaReglas());
			Scanner sc = new Scanner(System.in);
			ControladorConsola control = new ControladorConsola(factoria, p, sc);
			VistaConsola vista = new VistaConsola(control);
			vista.run();
			sc.close();
		}
	}
}
