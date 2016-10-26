	private void salvarJuego( ){
	     if(nombreArchivoAbierto!=""){
	         //Tengo que escribir el fichero de texto con el formato correcto
	         FileWriter fichero = null;
	         PrintWriter pw = null;
	         try
	         {
	             fichero = new FileWriter(nombreArchivoAbierto);
	             pw = new PrintWriter(fichero);
	             //pw.print(oJuego.getTablero().length);
	             pw.print(dimFila);
	             pw.print(" ");
	             //pw.println(oJuego.getTablero()[0].length);
	             pw.println(dimCol);
	             pw.println(numObj);
	             
	             int contador=0;
	             //Si me da error cambiar casillasVEditar por casillaTablero y casillaTablero[0]
	             for (int i = 0; i < casillasVEditar.length; i++) {
	                 for (int j = 0; j < casillasVEditar[0].length; j++) {
	                	 if(casillasVEditar[i][j].getBackground().equals(Color.GREEN)){
	                         contador++;
	                         pw.print(i+1);
	                         pw.print(" ");
	                         pw.print(j+1);
	                         if (contador<numObj)
	                             pw.print(" ");
	                         else
	                             pw.println();
	                     }

	                 }
	             }
	             //escribo las posiciones de las bolas
	             contador=0;
	             for (int i = 0; i < casillasVEditar.length; i++) {
	                 for (int j = 0; j < casillasVEditar[0].length; j++) {
	                     if (casillasVEditar[i][j].getText().equals ("b")){
	                         contador++;
	                         pw.print(i+1);
	                         pw.print(" ");
	                         pw.print(j+1);
	                         if (contador<numObj)
	                             pw.print(" ");

	                     }

	                 }
	             }
	          } catch (Exception e) {
	            // e.printStackTrace();
	         } finally {
	            try {
	            // Utilizamos finally para asegurarnos que se cierra el fichero.
	            if (null != fichero)
	               fichero.close();
	            } catch (Exception e2) {
	               //e2.printStackTrace();
	            }
	         }
	     }
	     else{
	         salvarComo();
	     }

	    }
	
	private void salvarComo() {
        //tendria que saber si se ha cargado o creado un criptojuego
		//(existe oCriptoJuego??, oCriptoJuego!=null)
        boolean ficheroExiste=false;
        //if(hayJuegoCargadoVR==false && hayJuegoNuevoVR==false){
        if (hayJuegoCargadoVR==false)
        {
            String mensajeSalvar="NO puedes salvar ningun juego porque no existe";
            JOptionPane.showMessageDialog(null, mensajeSalvar,"NO SE PUEDE SALVAR",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        //Crear una superficie grafica para elegir el nombre del fichero a usar para guardar el juego
        final JFileChooser fc = new JFileChooser();

        //Mostrar la superficie grafica
        int returnVal = fc.showSaveDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            //Guardamos el nombre del archivo en el objeto del criptojuego
            //oJuego.setNombreArchivo(file.getAbsolutePath());//leer la ruta completa del archivo
            nombreArchivoAbierto=file.getAbsolutePath();
        } else {
            return;
        }

        //compruebo si existe el nombre del fichero
        //File f = new File(oJuego.getNombreArchivo());
        File f = new File(nombreArchivoAbierto);

        if (f.exists())
        if(ficheroExiste)
        {
            int respuestaBoton = JOptionPane.showConfirmDialog (null,"El fichero existe."
            		+ "\n\nSI->Cierra el fichero actual sin guardarlo."
            		+ "\n\nNO->Lo guarda en su ubucacion (sobreescribe)\n\n");

            if(respuestaBoton == JOptionPane.YES_OPTION)
            {
                //CANCELAR
                return;
            }
            else ///Nunca entra aqui, cambiar el JOptionPane.showConfirmDialog
            {
              // Salvar juego
                salvarJuego();
            }
        }
        //en el caso de que el nombre del fichero no exista, lo creo
        salvarJuego();

        }
