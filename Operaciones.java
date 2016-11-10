
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Operaciones{

	private static ArrayList <String> listaClases;

	/**
	 * Constructor de la clase
	 * @param nombre
	 * @throws Exception 
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Operaciones() throws Exception{
		listaClases = new ArrayList <String>();
		
		nuevaOperacion("Suma");
		nuevaOperacion("Repetir");
		
		File[]	archivosDisponibles = new File("./src/").listFiles();
		for (int i = 0; i < archivosDisponibles.length; i++) {
			String aux = archivosDisponibles[i].getName().toString();
			aux = aux.split(".")[0];
			System.out.println(aux);
		}
		
	}

	/**
	 * Usa la operacion seleccionada del array de operaciones
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public static int usarOperacion() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException{
		Class c = Class.forName(listaClases.get(0));
		return (int)c.getMethods()[0].invoke(c,2,2);
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
	
	public static ArrayList <String> getListaMetodos(){
		return listaClases;
	}

}
