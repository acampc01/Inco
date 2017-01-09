import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class Interfaz extends JFrame {
	
	Controlador controller;
	private JTextField txtEntrada;
	private JTextField txtEntrada_1;
	private JTextField txtResultado;
	private JTextField textField;
	private JTextField txtA;
	private JTextField txtB;
	private JTextField txtC;
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
	private JLabel lblNombre;
	
	public Interfaz(Controlador c){
		
		controller = c;
		
		inicializarComponentes();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		
		
	}
	
	private void inicializarComponentes() {
		
		setSize(500,272);
		
		JLabel lblCrearOperacin = new JLabel("CREAR OPERACIÓN");
		lblCrearOperacin.setBounds(172, 22, 120, 29);
		lblCrearOperacin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCrearOperacin.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		Operaciones = new String[controller.getListaMetodos().size()];

		for(int i=0;i<controller.getListaMetodos().size();i++){
			Operaciones[i] = controller.getListaMetodos().get(i);
		}
		
		DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<String>(Operaciones);
		comboBox = new JComboBox<String>(comboModel);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Operaciones = new String[controller.getListaMetodos().size()];

				for(int i=0;i<controller.getListaMetodos().size();i++){
					Operaciones[i] = controller.getListaMetodos().get(i);
				}
				
				
			}

			
		});
		comboBox.setBounds(20, 170, 102, 20);
		
		JLabel lblAccionesDispoibles = new JLabel("Acciones Disponibles");
		lblAccionesDispoibles.setBounds(20, 130, 173, 29);
		
		txtEntrada = new JTextField();
		txtEntrada.setBounds(143, 71, 75, 20);
		txtEntrada.setEditable(false);
		txtEntrada.setText("ENTRADA 1");
		txtEntrada.setColumns(10);
		
		txtEntrada_1 = new JTextField();
		txtEntrada_1.setBounds(228, 71, 76, 20);
		txtEntrada_1.setText("ENTRADA 2");
		txtEntrada_1.setEditable(false);
		txtEntrada_1.setColumns(10);
		
		txtResultado = new JTextField();
		txtResultado.setBounds(350, 71, 86, 20);
		txtResultado.setText("RESULTADO");
		txtResultado.setEditable(false);
		txtResultado.setColumns(10);
		
		textField = new JTextField();
		textField.setBounds(314, 71, 26, 20);
		textField.setText("  =");
		textField.setEditable(false);
		textField.setColumns(10);
		
		txtA = new JTextField();
		txtA.setBounds(162, 109, 21, 20);
		txtA.setText(" A");
		txtA.setEditable(false);
		txtA.setColumns(10);
		
		txtB = new JTextField();
		txtB.setBounds(240, 109, 21, 20);
		txtB.setText(" B");
		txtB.setEditable(false);
		txtB.setColumns(10);
		
		txtC = new JTextField();
		txtC.setBounds(355, 109, 21, 20);
		txtC.setText(" C");
		txtC.setEditable(false);
		txtC.setColumns(10);
		getContentPane().setLayout(null);
		getContentPane().add(lblCrearOperacin);
		getContentPane().add(lblAccionesDispoibles);
		getContentPane().add(txtEntrada);
		getContentPane().add(txtEntrada_1);
		getContentPane().add(textField);
		getContentPane().add(txtResultado);
		getContentPane().add(txtA);
		getContentPane().add(txtB);
		getContentPane().add(txtC);
		getContentPane().add(comboBox);
		
		txtNombreClase = new JTextField();
		txtNombreClase.setBounds(20, 71, 100, 20);
		getContentPane().add(txtNombreClase);
		txtNombreClase.setColumns(10);
		
		lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(20, 46, 75, 14);
		getContentPane().add(lblNombre);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//El usuario escoge utilizar la acción Repetir
				if(comboBox.getSelectedItem().equals("Repetir")){
					
					Object[] posibilidades = controller.getListaMetodos().toArray();
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
					
					Object[] primerParametro = {"A","-A"};
					parametro1 = (String)JOptionPane.showInputDialog(
					                    null,
					                    "Elige el primer parámetro\n",
					                    "Primer parámetro",
					                    JOptionPane.PLAIN_MESSAGE,
					                    null,
					                    primerParametro,
					                    primerParametro[0]);
					
					Object[] segundoParametro = {"B","-B"};
					parametro2 = (String)JOptionPane.showInputDialog(
					                    null,
					                    "Elige el segundo parámetro\n",
					                    "Segundo parámetro",
					                    JOptionPane.PLAIN_MESSAGE,
					                    null,
					                    segundoParametro,
					                    segundoParametro[0]);
					
				}
				
				setNombreClase(txtNombreClase.getText().toLowerCase());
				setNombreAccion(comboBox.getSelectedItem().toString());
				try {
					controller.CrearArchivo(nombreClase);
				} catch (IOException e1) {
					e1.printStackTrace();
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
		btnCrear.setBounds(347, 169, 89, 23);
		getContentPane().add(btnCrear);
		
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
