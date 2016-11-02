import java.io.IOException;
import java.util.ArrayList;

public class Main {
	
	private static ArrayList <String> listaClases;

	public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		listaClases = new ArrayList <String>();
		
		for(int i=0;i<2;i++){
			nuevaOperacion();
			usarOperacion();
		}
	}
	
	private static void usarOperacion() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Class c = Class.forName(listaClases.get(0));
		c.newInstance();
	}

	private static void nuevaOperacion() throws IOException{
		NuevaOperacion nueva = new NuevaOperacion();
		listaClases.add(nueva.getNombreClass());
	}
	

}
