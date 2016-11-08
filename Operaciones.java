import java.io.IOException;
import java.util.ArrayList;

public class Operaciones{

	private static ArrayList <String> listaClases;

	/**
	 * Constructor de la clase
	 * @param nombre
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Operaciones() throws IOException{
		listaClases = new ArrayList <String>();
		nuevaOperacion("Suma");
		nuevaOperacion("Repetir");
	}

	/**
	 * Usa la operacion seleccionada del array de operaciones
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static void usarOperacion() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Class c = Class.forName(listaClases.get(0));
		c.newInstance();
	}

	/**
	 * Crea una operacion nueva.
	 * @param nombre
	 * @throws IOException
	 */
	public static void nuevaOperacion(String nombre) throws IOException{
		CrearArchivo nueva = new CrearArchivo(nombre);
		listaClases.add(nueva.getNombreClass());
	}


}
