import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class NuevaOperacion {

	private String nombre;
	private static Scanner sc = new Scanner(System.in);

	public NuevaOperacion() throws IOException{
		createFile();
		System.out.println("Operacion creada.");
	}

	private void createFile() throws IOException{

		System.out.print("Introduce un nombre para la operacion: ");
		String cadena = sc.nextLine();
		String primeraLetra = "" + cadena.charAt(0);
		primeraLetra = primeraLetra.toUpperCase();
		String resto = cadena.substring(1);
		cadena = primeraLetra + resto;
		
		this.nombre = cadena;

		String nombreArchivo = "./src/" + cadena +".java";
		File archivo = new File(nombreArchivo);

		BufferedWriter bw;
		if(archivo.exists()) {
			System.out.println("El archivo ya existe.");
			createFile();
		} else {
			bw = new BufferedWriter(new FileWriter(archivo));
			bw.write("public class " + cadena +  "{\n\tprivate int x;\n\tprivate int y;\n\n\tpublic " + cadena +"(){\n\n \t\tSystem.out.println("+"\"Hello\""+");\n\n\t}\n}\n");
			bw.close();
			
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			int result = compiler.run(null,null,null,"./src/"+cadena+".java");
			
			File copiar = new File("./src/"+cadena+".class");
			copiar.renameTo(new File("./bin/"+cadena+".class"));
		}
	}

	public String getNombreClass(){
		return this.nombre;
	}
}
