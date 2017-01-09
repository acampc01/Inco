import java.util.ArrayList;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Controlador {
	
	private Interfaz crearOperacion;
	private ArrayList<String> listaMetodos;
	
	public Controlador(){
		
		listaMetodos = new ArrayList<String>();
		
		cargarOperaciones();
		
		crearOperacion = new Interfaz(this);
		
	}

	private void cargarOperaciones() {
		List<String> operaciones = new ArrayList<String>();
		
		File[] files = new File("./src").listFiles();
		for(File file : files){
			
			String name = file.getName();
			int pos = name.lastIndexOf(".");
			if(pos > 0)
				name = name.substring(0,pos);
			
			if(!name.equals("Ejecuta") && !name.equals("Controlador") && !name.equals("Interfaz"))
				operaciones.add(name);
		}
		listaMetodos.addAll(operaciones);
	}
	
	public List<String> getListaMetodos() {
		return listaMetodos;
	}

	public void setListaMetodos(ArrayList<String> listaMetodos) {
		this.listaMetodos = listaMetodos;
	}

	public void CrearArchivo(String nombre) throws IOException{
		
		listaMetodos.add(nombre);
		createFile(nombre);
	}
	
	private void createFile(String nombre) throws IOException{
		
		String cadena = nombre;
		String primeraLetra = "" + cadena.charAt(0);
		primeraLetra = primeraLetra.toUpperCase();
		String resto = cadena.substring(1);
		resto = resto.toLowerCase();
		cadena = primeraLetra + resto;


		String nombreArchivo = "./src/" + cadena +".java";
		File archivo = new File(nombreArchivo);
		
		BufferedWriter bw;
		if(!archivo.exists()) {
			
			bw = new BufferedWriter(new FileWriter(archivo));
			bw.write("public class " + cadena +  "{\n\n");
			
			String doble = crearOperacion.getNombreAccion().toLowerCase();
			if(crearOperacion.getNombreAccion().equals("Repetir")){
				
				if(crearOperacion.getCondicion()){
					
					bw.write("\tpublic static int "+nombre+"(int "+crearOperacion.getParametro1().toLowerCase()+" , int "+crearOperacion.getParametro2().toLowerCase()+")throws ClassNotFoundException, InstantiationException, IllegalAccessException{\n");
					if(crearOperacion.getValorCondicion() == 1){
						bw.write("\t return "+crearOperacion.getNombreAccion()+"."+doble+"(\""+crearOperacion.getNombreAccionARepetir()+"\", "+"new IgualQue()"+", "+crearOperacion.getParametro1().toLowerCase()+", "+crearOperacion.getParametro2().toLowerCase()+");\n");
					}else if(crearOperacion.getValorCondicion() == 2){
						bw.write("\t return "+crearOperacion.getNombreAccion()+"."+doble+"(\""+crearOperacion.getNombreAccionARepetir()+"\", "+"new MenorOIgualQue()"+", "+crearOperacion.getParametro1().toLowerCase()+", "+crearOperacion.getParametro2().toLowerCase()+");\n");
					}else if(crearOperacion.getValorCondicion() == 3){
						bw.write("\t return "+crearOperacion.getNombreAccion()+"."+doble+"(\""+crearOperacion.getNombreAccionARepetir()+"\", "+"new MayorOIgualQue()"+", "+crearOperacion.getParametro1().toLowerCase()+", "+crearOperacion.getParametro2().toLowerCase()+");\n");
					}else if(crearOperacion.getValorCondicion() == 4){
						bw.write("\t return "+crearOperacion.getNombreAccion()+"."+doble+"(\""+crearOperacion.getNombreAccionARepetir()+"\", "+"new MenorQue()"+", "+crearOperacion.getParametro1().toLowerCase()+", "+crearOperacion.getParametro2().toLowerCase()+");\n");
					}else if(crearOperacion.getValorCondicion() == 5){
						bw.write("\t return "+crearOperacion.getNombreAccion()+"."+doble+"(\""+crearOperacion.getNombreAccionARepetir()+"\", "+"new MayorQue()"+", "+crearOperacion.getParametro1().toLowerCase()+", "+crearOperacion.getParametro2().toLowerCase()+");\n");
					}
					
					bw.write("\t}\n");
					bw.write("\n}");
					bw.close();
					
					
					
				}else{
					
					bw.write("\tpublic static int "+nombre+"(int "+crearOperacion.getParametro1().toLowerCase()+" , int "+crearOperacion.getParametro2().toLowerCase()+")throws ClassNotFoundException, InstantiationException, IllegalAccessException{\n");
					bw.write("\t return "+crearOperacion.getNombreAccion()+"."+doble+"(\""+crearOperacion.getNombreAccionARepetir()+"\", "+crearOperacion.getParametro1().toLowerCase()+", "+crearOperacion.getParametro2().toLowerCase()+");\n");
					bw.write("\t}\n");
					bw.write("\n}");
					bw.close();
				}
				
				JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
				int result = compiler.run(null,null,null,"./src/"+cadena+".java");
				
				File copiar = new File("./src/"+cadena+".class");
				copiar.renameTo(new File("./bin/"+cadena+".class"));
				
			}else{
				
				bw.write("\tpublic static int "+nombre+"(int a, int b){\n");
				bw.write("\t\t return "+crearOperacion.getNombreAccion()+"."+doble+"("+crearOperacion.getParametro1().toLowerCase()+","+crearOperacion.getParametro2().toLowerCase()+");\n");       
				bw.write("\t}\n");
				bw.write("\n}");
				bw.close();
				
				
				JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
				int result = compiler.run(null,null,null,"./src/"+cadena+".java");
				
				File copiar = new File("./src/"+cadena+".class");
				copiar.renameTo(new File("./bin/"+cadena+".class"));
				
			}
			
			
		}	

	}

	
}
