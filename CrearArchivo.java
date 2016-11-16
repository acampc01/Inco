import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
public class CrearArchivo {

	private String nombre;
	private int numArgumentos;

	/**
	 * Constructor de la clase
	 * @param nombre
	 * @throws IOException
	 */
	public CrearArchivo(String nombre) throws IOException{
		createFile(nombre);
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
		if(archivo.exists() && (!(this.nombre.equals("Repetir")) && !(this.nombre.equals("Suma"))) ) {
			List<String> lines = Files.readAllLines(Paths.get(archivo.getPath()), Charset.defaultCharset());

			int cont = 0;
			for (int i = 0; i < lines.size(); i++) {
				String arr[] = lines.get(i).split(" ", 2);
				String firstWord = arr[0];				
				if(firstWord.equals("\tprivate")){
					cont++;
				}
			}
			
			this.numArgumentos = cont;

		} else {
			bw = new BufferedWriter(new FileWriter(archivo));

			switch(cadena){

			case "Suma":

				this.numArgumentos = 2;

				bw.write("public class " + cadena +  "{\n\n");

				bw.write("\tpublic static int suma(int x, int y){\n\t\treturn x + y;\n\t}\n\n");

				bw.write("}");
				bw.close();

				JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
				int result = compiler.run(null,null,null,"./src/"+cadena+".java");

				File copiar = new File("./src/"+cadena+".class");
				copiar.renameTo(new File("./bin/"+cadena+".class"));
				break;

			case "Repetir":

				this.numArgumentos = 2;

				bw.write("import java.lang.reflect.InvocationTargetException;\n\n\npublic class " + cadena +  "{\n\n");

				bw.write("\tpublic static int repetir(String nombre, int numeroVeces) throws ClassNotFoundException, InstantiationException, IllegalAccessException {\n" +
						"\t\tif(nombre.equals(\"Repetir\")){\n\t\t\tSystem.out.println(\"No se puede repetir la operacion Repetir\");\nreturn -1;\n\t\t}else{\n\t\t\treturn repetirOperacion(nombre, numeroVeces);\n\t\t}\n\t}\n\n"+
						"\tpublic static int repetirOperacion(String nombre, int numeroVeces) throws ClassNotFoundException, InstantiationException, IllegalAccessException {\n\n\t\tint salida = 0;\n\t\tfor(int i=0;i<numeroVeces;i++){ Class c = Class.forName(nombre); try { salida = salida + (int) c.getMethods()[0].invoke(c,2,2); } catch (IllegalArgumentException | InvocationTargetException | SecurityException e) { e.printStackTrace(); } } \n\t\treturn salida;\n\n\t}");

				bw.write("}");
				bw.close();

				JavaCompiler compiler2 = ToolProvider.getSystemJavaCompiler();
				int result2 = compiler2.run(null,null,null,"./src/"+cadena+".java");

				File copiar2 = new File("./src/"+cadena+".class");
				copiar2.renameTo(new File("./bin/"+cadena+".class"));
				break;

			default: 

				this.numArgumentos = 3;

				bw.write("public class " + cadena +  "{\n\n");

				bw.write("\tprivate int x;\n\tprivate int y;\n\tprivate int z;\n\n\tpublic "+ cadena + "(int x){\n\n\t\tthis.x = x;\n\n\t}\n\n");

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

	public int getNumArgumentos(){
		return this.numArgumentos;
	}
}
