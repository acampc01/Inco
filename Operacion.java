import java.io.IOException;
import java.util.ArrayList;

public class Operacion{

	private static ArrayList <String> listaClases;

	public Operacion(String nombre) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		listaClases = new ArrayList <String>();
		nuevaOperacion(nombre);
	}

	public static void usarOperacion() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Class c = Class.forName(listaClases.get(0));
		c.newInstance();
	}

	private static void nuevaOperacion(String nombre) throws IOException{
		NuevaOperacion nueva = new NuevaOperacion(nombre);
		listaClases.add(nueva.getNombreClass());
	}


}
