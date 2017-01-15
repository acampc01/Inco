import java.lang.reflect.InvocationTargetException;
public class Repetir {

	public static int repetir(String nombre, int parametro, int numeroVeces) throws ClassNotFoundException, InstantiationException, IllegalAccessException {	
			return repetirOperacion(nombre, parametro, numeroVeces);
	}
	
	public static int repetir(String nombre, Condicion condicion, int parametro1, int parametro2) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		condicion.a = parametro1;
		condicion.b = parametro2;
		condicion.operacion = nombre;
		
		return condicion.repetir();
	}
	
	
	public static int repetirOperacion(String nombre, int parametro, int numeroVeces) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		int salida = 0;
		Class<?> c = Class.forName(nombre);
		if(nombre.equals("Suma")){
			for(int i=0;i<numeroVeces;i++){
				try {
					salida =  (int) c.getMethods()[0].invoke(c,salida,parametro);
				}catch(IllegalArgumentException | InvocationTargetException | SecurityException e){
					e.printStackTrace();
				}
			}
		}else{
			salida = parametro;
			for(int i=0;i<numeroVeces-1;i++){
				try {
					salida = (int) c.getMethods()[0].invoke(c,salida,parametro);
				} catch (IllegalArgumentException | InvocationTargetException | SecurityException e) {
					e.printStackTrace();
				}
			}
		}
		return salida;
	}
}

abstract class Condicion{
	
	int a;
	int b;
	String operacion;
	
	public abstract int repetir() throws ClassNotFoundException;
}

class IgualQue extends Condicion{

	@Override
	public int repetir() throws ClassNotFoundException {
		
		int salida = 0;
		Class<?> c = Class.forName(operacion);
		
		while(a == b){
			try {
				a =  (int) c.getMethods()[0].invoke(c,a,b);
				salida++;
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| SecurityException e) {
				e.printStackTrace();
			}
		}
		return salida;
	}
	
}
class MayorQue extends Condicion{

	@Override
	public int repetir() throws ClassNotFoundException {
		
		int salida = 0;
		Class<?> c = Class.forName(operacion);
		
		while(a > b){
			try {
				a =  (int) c.getMethods()[0].invoke(c,a,b);
				salida++;
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| SecurityException e) {
				e.printStackTrace();
			}
		}
		return salida;
	}
	
}

class MenorQue extends Condicion{

	@Override
	public int repetir() throws ClassNotFoundException {
		
		int salida = 0;
		Class<?> c = Class.forName(operacion);
		
		while(a < b){
			try {
				a =  (int) c.getMethods()[0].invoke(c,a,b);
				salida++;
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| SecurityException e) {
				e.printStackTrace();
			}
		}
		return salida;
	}
	
}

class MayorOIgualQue extends Condicion{

	@Override
	public int repetir() throws ClassNotFoundException {
		
		int salida = 0;
		Class<?> c = Class.forName(operacion);
		
		while(a >= b){
			try {
				a =  (int) c.getMethods()[0].invoke(c,a,b);
				salida++;
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| SecurityException e) {
				e.printStackTrace();
			}
		}
		return salida;
	}
	
}

class MenorOIgualQue extends Condicion{

	@Override
	public int repetir() throws ClassNotFoundException {
		
		int salida = 0;
		Class<?> c = Class.forName(operacion);
		
		while(a <= b){
			try {
				a =  (int) c.getMethods()[0].invoke(c,a,b);
				salida++;
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| SecurityException e) {
				e.printStackTrace();
			}
		}
		return salida;
	}
	
}