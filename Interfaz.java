
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JComboBox;


public class Interfaz {

	private JFrame frame;
	private JTextField textFieldNombre;
	private JTextField txtNumEntradas;
	private JTextField textFieldSalidaMetodos;
	private JComboBox <String> listaMetodosUtilizables;
	
	private Operaciones op;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public Interfaz() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	private void initialize() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
		try {
			op = new Operaciones();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(266, 12, 148, 205);
		frame.getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		txtNumEntradas = new JTextField();
		txtNumEntradas.setBounds(25, 198, 114, 19);
		txtNumEntradas.setEditable(false);
		txtNumEntradas.setText("Salida:");
		frame.getContentPane().add(txtNumEntradas);
		txtNumEntradas.setColumns(10);
		
		textFieldSalidaMetodos = new JTextField();
		textFieldSalidaMetodos.setBounds(164, 198, 39, 19);
		textFieldSalidaMetodos.setEditable(false);
		frame.getContentPane().add(textFieldSalidaMetodos);
		textFieldSalidaMetodos.setColumns(10);
		
		
		
		String[] labels = new String[Operaciones.getListaMetodos().size()];
		
		for(int i=0;i<Operaciones.getListaMetodos().size();i++){
			labels[i] = Operaciones.getListaMetodos().get(i);
		}
		
		listaMetodosUtilizables = new JComboBox<String>(labels);
		listaMetodosUtilizables.setBounds(25, 122, 135, 24);
		frame.getContentPane().add(listaMetodosUtilizables);
		
		
		JButton btnCrearcrea = new JButton("New Operation");
		btnCrearcrea.setBounds(12, 12, 148, 25);
		btnCrearcrea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				try {
					String input = textFieldNombre.getText().substring(0, 1).toUpperCase() + textFieldNombre.getText().substring(1).toLowerCase();
					
					Operaciones.nuevaOperacion(input);
					listaMetodosUtilizables.addItem(input);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnCrearcrea);
		
		JButton btnUtilizarNueva = new JButton("Use Operation");
		btnUtilizarNueva.setBounds(12, 49, 148, 25);
		btnUtilizarNueva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int salida = Operaciones.usarOperacion();
					textFieldSalidaMetodos.setText("" + salida);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					e1.printStackTrace();
				} catch (SecurityException e1) {
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnUtilizarNueva);
	}
}
