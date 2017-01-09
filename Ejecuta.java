
public class Ejecuta {
	
	private Controlador controlador;
	
	public Ejecuta(){
		controlador = new Controlador();
		
	}
	public static void main(String[] args) {
		
		new Ejecuta();
		
		int a = 0;
		
		try {
			a = Multiplicacion.multiplicacion(2, 3);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(a);
	}
	
	
}
