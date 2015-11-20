package tp.pr5.comandos;

public class ComandoSalir implements Comando {
	private static final String ayuda = "SALIR: termina la aplicación.";

	public ComandoSalir() {
	}

	@Override
	public boolean analizar(String cad) {
		return (cad.equals("salir"));
	}

	@Override
	public void ejecutar() {
		// Cuando se ejecuta este comando, se cierra la aplicación
		System.exit(0);
	}

	@Override
	public String ayuda() {
		return ayuda;
	}
}
