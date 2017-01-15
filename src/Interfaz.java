import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.DefaultComboBoxModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Interfaz extends JFrame {

	Controlador controller;
	private JComboBox<String> comboBox;

	private String parametro1; //Primer parámetro de la acción a a crear
	private String parametro2; //Segundo parámetro de la acción a crear
	private String nombreClase; //Nombre de la clase/operación que se va a crear
	private String nombreAccion; //Nombre de la acción que se va a usar
	Object seleccion;
	private Boolean condicion = false;
	private int valorCondicion;

	private String nombreAccionARepetir; //Nombre de la acción que se va a repetir

	String[] Operaciones;
	private JTextField txtNombreClase;

	public Interfaz(Controlador c){

		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		controller = c;
		inicializarComponentes();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

	}

	private void inicializarComponentes() {

		setSize(493,268);

		getContentPane().setBackground(new Color(0,170,227));

		Operaciones = new String[controller.getListaMetodos().size()];

		for(int i=0;i<controller.getListaMetodos().size();i++){
			Operaciones[i] = controller.getListaMetodos().get(i);
		}

		DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<String>(Operaciones);
		getContentPane().setLayout(null);

		JButton btnCrear = new JButton("CREAR");
		btnCrear.setBounds(399, 120, 89, 23);
		btnCrear.setBackground(Color.ORANGE);
		btnCrear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCrear.setForeground(Color.WHITE);
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					//El usuario escoge utilizar la acción Repetir
					if(comboBox.getSelectedItem().equals("Repetir")){
						
						Object[] poss = controller.getListaMetodos().toArray();
						Object[] posibilidades = new Object[poss.length-1]; 
						int cont = 0;
						for (int i = 0; i < poss.length; i++) {
							if(!poss[i].equals("Repetir")){
								posibilidades[cont] = poss[i];
								cont++;
							}
						}

						nombreAccionARepetir = (String)JOptionPane.showInputDialog(
								null,
								"¿Qué acción quieres repetir?\n",
								"Elegir acción",
								JOptionPane.PLAIN_MESSAGE,
								null,
								posibilidades,
								posibilidades[0]);


						if(comprobarAccion(nombreAccionARepetir)){

							seleccion = JOptionPane.showInputDialog(
									null,
									"¿Cuántas veces repetir la acción?",
									"Repetir",
									JOptionPane.QUESTION_MESSAGE,
									null,  
									new Object[] { "N veces", "Condición"}, 
									"N veces");

							parametro1 = "A";
							parametro2 = "N";

							if(seleccion.equals("Condición")){

								condicion = true;
								seleccion = JOptionPane.showInputDialog(
										null,
										"¿Qué condición establecer?",
										"Condición de repetición",
										JOptionPane.QUESTION_MESSAGE,
										null,  
										new Object[] { "A = B", "A <= B", "A >= B","A < B", "A > B"}, 
										"A = B");

								valorCondicion = obtenerValorCondicion(seleccion);
							}

						}else{
							return;
						}

						//El usuario escoge la acción Suma
					}else if(comboBox.getSelectedItem().equals("Suma")){
						
						Object[] primerParametro = {"A","-A","Operacion"};
						String aux1 =(String)JOptionPane.showInputDialog(
								null,
								"Elige el primer parámetro\n",
								"Primer parámetro",
								JOptionPane.PLAIN_MESSAGE,
								null,
								primerParametro,
								primerParametro[0]);

						//PARTE NUEVA PARA OPERACIONES COMO ARGUMENTO
						if(aux1.equals("Operacion")){
							Object[] poss = controller.getListaMetodos().toArray();
							Object[] posibilidades = new Object[poss.length-1]; 
							int cont = 0;
							for (int i = 0; i < poss.length; i++) {
								if(!poss[i].equals("Repetir")){
									posibilidades[cont] = poss[i];
									cont++;
								}
							}
							
							
							String llamadaMetodo = (String)JOptionPane.showInputDialog(
									null,
									"¿Qué operacion quieres usar como argumento?\n",
									"Elegir argumento",
									JOptionPane.PLAIN_MESSAGE,
									null,
									posibilidades,
									posibilidades[0]);

							aux1 = (""+llamadaMetodo.charAt(0)).toUpperCase() + llamadaMetodo.substring(1).toLowerCase() + "." + llamadaMetodo.toLowerCase()+"(a,b)";
							parametro1 = aux1;
						}else{
							parametro1 = aux1.toLowerCase();
						}
						
						Object[] segundoParametro = {"B","-B", "Operacion"};

						String aux2 = (String)JOptionPane.showInputDialog(
								null,
								"Elige el segundo parámetro\n",
								"Segundo parámetro",
								JOptionPane.PLAIN_MESSAGE,
								null,
								segundoParametro,
								segundoParametro[0]);

						//PARTE NUEVA PARA OPERACIONES COMO ARGUMENTO
						if(aux2.equals("Operacion")){
							Object[] poss = controller.getListaMetodos().toArray();
							Object[] posibilidades = new Object[poss.length-1]; 
							int cont = 0;
							for (int i = 0; i < poss.length; i++) {
								if(!poss[i].equals("Repetir")){
									posibilidades[cont] = poss[i];
									cont++;
								}
							}
							
							String llamadaMetodo = (String)JOptionPane.showInputDialog(
									null,
									"¿Qué operacion quieres usar como argumento?\n",
									"Elegir argumento",
									JOptionPane.PLAIN_MESSAGE,
									null,
									posibilidades,
									posibilidades[0]);

							aux2 =  (""+llamadaMetodo.charAt(0)).toUpperCase() + llamadaMetodo.substring(1).toLowerCase() + "." + llamadaMetodo.toLowerCase()+"(a,b)";
							parametro2 = aux2;
						}else{
							parametro2 = aux2.toLowerCase();
						}

					}else{
						//SINO ES NI REPETIR NI SUMA
						Object[] primerParametro = {"A","-A","Operacion"};
						String aux1 =(String)JOptionPane.showInputDialog(
								null,
								"Elige el primer parámetro\n",
								"Primer parámetro",
								JOptionPane.PLAIN_MESSAGE,
								null,
								primerParametro,
								primerParametro[0]);

						if(aux1.equals("Operacion")){
							Object[] poss = controller.getListaMetodos().toArray();
							Object[] posibilidades = new Object[poss.length-1]; 
							int cont = 0;
							for (int i = 0; i < poss.length; i++) {
								if(!poss[i].equals("Repetir")){
									posibilidades[cont] = poss[i];
									cont++;
								}
							}
							
							String llamadaMetodo = (String)JOptionPane.showInputDialog(
									null,
									"¿Qué operacion quieres usar como argumento?\n",
									"Elegir argumento",
									JOptionPane.PLAIN_MESSAGE,
									null,
									posibilidades,
									posibilidades[0]);

							aux1 = (""+llamadaMetodo.charAt(0)).toUpperCase() + llamadaMetodo.substring(1).toLowerCase() + "." + llamadaMetodo.toLowerCase()+"(a,b)";
							parametro1 = aux1;
						}else{
							parametro1 = aux1.toLowerCase();
						}
						
						Object[] segundoParametro = {"B","-B", "Operacion"};

						String aux2 = (String)JOptionPane.showInputDialog(
								null,
								"Elige el segundo parámetro\n",
								"Segundo parámetro",
								JOptionPane.PLAIN_MESSAGE,
								null,
								segundoParametro,
								segundoParametro[0]);

						if(aux2.equals("Operacion")){
							Object[] poss = controller.getListaMetodos().toArray();
							Object[] posibilidades = new Object[poss.length-1]; 
							int cont = 0;
							for (int i = 0; i < poss.length; i++) {
								if(!poss[i].equals("Repetir")){
									posibilidades[cont] = poss[i];
									cont++;
								}
							}
							
							String llamadaMetodo = (String)JOptionPane.showInputDialog(
									null,
									"¿Qué operacion quieres usar como argumento?\n",
									"Elegir argumento",
									JOptionPane.PLAIN_MESSAGE,
									null,
									posibilidades,
									posibilidades[0]);

							aux2 =  (""+llamadaMetodo.charAt(0)).toUpperCase() + llamadaMetodo.substring(1).toLowerCase() + "." + llamadaMetodo.toLowerCase()+"(a,b)";
							parametro2 = aux2;
						}else{
							parametro2 = aux2.toLowerCase();
						}


					}

					setNombreClase(txtNombreClase.getText().toLowerCase());
					setNombreAccion(comboBox.getSelectedItem().toString());
					comboBox.addItem((""+nombreClase.charAt(0)).toUpperCase() + nombreClase.substring(1).toLowerCase());
					
					try {
						controller.CrearArchivo(nombreClase);
						JOptionPane.showMessageDialog(null,
								"Operación creada correctamente",
								"",
								JOptionPane.PLAIN_MESSAGE);
					} catch (IOException e1) {
						System.out.println("Error al crear clase.");
					}

					
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Accion cancelada.", "Accion cancelada", JOptionPane.INFORMATION_MESSAGE);
				}
			}

			private int obtenerValorCondicion(Object seleccion) {

				if(seleccion.equals("A = B")){
					return 1;
				}else if(seleccion.equals("A <= B")){
					return 2;
				}else if(seleccion.equals("A >= B")){
					return 3;
				}else if(seleccion.equals("A < B")){
					return 4;
				}else{
					return 5;
				}
			}

		});
		getContentPane().add(btnCrear);

		txtNombreClase = new JTextField();
		txtNombreClase.setBounds(48, 122, 89, 20);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNombre.setBounds(56, 125, 46, 14);
		getContentPane().add(lblNombre);

		txtNombreClase.addCaretListener(e->{
			if(lblNombre.getText().equals("Nombre"))
				lblNombre.setText("");

		});

		txtNombreClase.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(lblNombre.getText().equals("Nombre"))
					lblNombre.setText("");
			}
		});


		txtNombreClase.setHorizontalAlignment(SwingConstants.LEFT);
		txtNombreClase.setBorder(new EmptyBorder(3, 3, 3, 3));
		getContentPane().add(txtNombreClase);
		txtNombreClase.setColumns(10);
		comboBox = new JComboBox<String>(comboModel);
		comboBox.setBounds(170, 148, 202, 27);
		comboBox.setForeground(Color.DARK_GRAY);
		comboBox.setFont(new Font("Roboto", Font.PLAIN, 15));

		comboBox.addActionListener(e -> {
			rellenarComboBox();
		});
		getContentPane().add(comboBox);

		JLabel fondo = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/fondo.png")).getImage();
		fondo.setIcon(new ImageIcon(img));
		fondo.setBounds(0, 0, 500, 272);
		getContentPane().add(fondo);

	}
	
	private void rellenarComboBox(){
		Operaciones = new String[controller.getListaMetodos().size()];

		for(int i=0;i<controller.getListaMetodos().size();i++){
			Operaciones[i] = controller.getListaMetodos().get(i);
		}
	}


	private boolean comprobarAccion(String accion) {

		if(accion.equals("Repetir")){
			JOptionPane.showMessageDialog(null,
					"No se puede repetir la acción Repetir",
					"Error",
					JOptionPane.ERROR_MESSAGE);
			return false;

		}else
			return true;
	}

	public String getNombreAccion() {
		return nombreAccion;
	}

	public void setNombreAccion(String nombreAccion) {
		this.nombreAccion = nombreAccion;
	}

	public String getParametro1() {
		return parametro1;
	}

	public void setParametro1(String parametro1) {
		this.parametro1 = parametro1;
	}

	public String getParametro2() {
		return parametro2;
	}

	public void setParametro2(String parametro2) {
		this.parametro2 = parametro2;
	}

	public String getNombreClase() {
		return nombreClase;
	}

	public void setNombreClase(String nombreClase) {
		this.nombreClase = nombreClase;
	}

	public String getNombreAccionARepetir() {
		return nombreAccionARepetir;
	}

	public void setNombreAccionARepetir(String nombreAccionARepetir) {
		this.nombreAccionARepetir = nombreAccionARepetir;
	}

	public Boolean getCondicion() {
		return condicion;
	}


	public int getValorCondicion() {
		return valorCondicion;
	}
}
