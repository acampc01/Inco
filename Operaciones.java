
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Operaciones{

	private static ArrayList <CrearArchivo> listaClases;

	/**
	 * Constructor de la clase
	 * @param nombre
	 * @throws Exception 
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Operaciones() throws Exception{
		listaClases = new ArrayList <CrearArchivo>();

		nuevaOperacion("Suma");
		nuevaOperacion("Repetir");

		File[]	archivosDisponibles = new File("./src/").listFiles();
		for (int i = 0; i < archivosDisponibles.length; i++) {

			String archivo = archivosDisponibles[i].getName().toString();
			String aux = "";

			for (int j = 0; j < archivo.length(); j++) {
				if(archivo.charAt(j)!='.'){
					aux = aux + archivo.charAt(j);
				}else{
					break;
				}
			}
			
			if(!aux.equals("Interfaz")){
				if(!aux.equals("CrearArchivo")){
					if(!aux.equals("Operaciones")){
						System.out.println(aux);
						if(!aux.equals("Suma")){
							if(!aux.equals("Repetir")){
								CrearArchivo nuevo = new CrearArchivo(aux);
								listaClases.add(nuevo);
							}
						}
					}
				}
			}
			
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
		Class c = Class.forName(listaClases.get(1).getNombreClass());
		return (int)c.getMethods()[0].invoke(c,"Suma",2);
	}

	/**
	 * Crea una operacion nueva.
	 * @param nombre
	 * @throws IOException
	 */
	public static void nuevaOperacion(String nombre) throws IOException{
		CrearArchivo nueva = new CrearArchivo(nombre);
		listaClases.add(nueva);
	}

	public static ArrayList <CrearArchivo> getListaMetodos(){
		return listaClases;
	}
	
	public static int getNumArgumentos(String nombreClase){
		for (int i = 0; i < listaClases.size(); i++) {	
			if(listaClases.get(i).getNombreClass().equals(nombreClase)){
				return listaClases.get(i).getNumArgumentos();
			}	
		}
		return -1;
	}

}
