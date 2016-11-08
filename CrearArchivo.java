import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class CrearArchivo {

	private String nombre;

	/**
	 * Constructor de la clase
	 * @param nombre
	 * @throws IOException
	 */
	public CrearArchivo(String nombre) throws IOException{
		createFile(nombre);
		System.out.println("Operacion creada.");
	}

	/**
	 * Crea un archivo con el nombre introducido por parametros.
	 * @param nombre
	 * @throws IOException
	 */
	private void createFile(String nombre) throws IOException{
		String cadena = nombre;
		String primeraLetra = "" + cadena.charAt(0);
		primeraLetra = primeraLetra.toUpperCase();
		String resto = cadena.substring(1);
		resto = resto.toLowerCase();
		cadena = primeraLetra + resto;

		this.nombre = cadena;

		String nombreArchivo = "./src/" + cadena +".java";
		File archivo = new File(nombreArchivo);

		BufferedWriter bw;
		if(archivo.exists()) {
			System.out.println("El archivo ya existe.");
		} else {
			bw = new BufferedWriter(new FileWriter(archivo));

			switch(cadena){
			
			case "Suma": bw.write("public class " + cadena +  "{\n\n");

			bw.write("\tprivate int x;\n\tprivate int y;\n\n\tpublic "+ cadena + "(int x, int y){\n\n\t\tthis.x = x;\n\t\tthis.y = y;\n\t\tsuma();\n\n\t}\n\n" +
			"\tprivate int suma() {\n\n\t\treturn this.x+this.y;\n\n\t}\n\n");

			bw.write("}");
			bw.close();

			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			int result = compiler.run(null,null,null,"./src/"+cadena+".java");

			File copiar = new File("./src/"+cadena+".class");
			copiar.renameTo(new File("./bin/"+cadena+".class"));
			break;

			case "Repetir": bw.write("public class " + cadena +  "{\n\n");

			bw.write("\tpublic "+ cadena + "(String nombre, int numeroVeces) throws ClassNotFoundException, InstantiationException, IllegalAccessException {\n" +
			"\t\tif(nombre.equals(\"Repetir\")){\n\t\t\tSystem.out.println(\"No se puede repetir la operacion Repetir\");\n\t\t}else{\n\t\t\trepetirOperacion(nombre, numeroVeces);\n\t\t}\n\t}\n\n"+
			"\tpublic void repetirOperacion(String nombre, int numeroVeces) throws ClassNotFoundException, InstantiationException, IllegalAccessException {\n\n\t\tfor(int i=0;i<numeroVeces;i++){Class c = Class.forName(nombre);c.newInstance();}\n"+
			"\n\t}\n");

			bw.write("}");
			bw.close();

			JavaCompiler compiler2 = ToolProvider.getSystemJavaCompiler();
			int result2 = compiler2.run(null,null,null,"./src/"+cadena+".java");

			File copiar2 = new File("./src/"+cadena+".class");
			copiar2.renameTo(new File("./bin/"+cadena+".class"));
			break;

			default: bw.write("public class " + cadena +  "{\n\n");

			bw.write("\tprivate int x;\n\n\tpublic "+ cadena + "(int x){\n\n\t\tthis.x = x;\n\n\t}\n\n");

			bw.write("}");
			bw.close();

			JavaCompiler compiler3 = ToolProvider.getSystemJavaCompiler();
			int result3 = compiler3.run(null,null,null,"./src/"+cadena+".java");

			File copiar3 = new File("./src/"+cadena+".class");
			copiar3.renameTo(new File("./bin/"+cadena+".class"));

			}
		}
	}

	public String getNombreClass(){
		return this.nombre;
	}
}
