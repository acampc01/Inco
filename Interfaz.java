import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JComboBox;
import javax.swing.JTextPane;

public class Interfaz {

	private JFrame frame;
	private JTextField txtNumEntradas;
	private JTextField textFieldSalidaMetodos;
	private JTextPane txtEntrada;
	private JComboBox<String> listaMetodosUtilizables;
	private String[] operacionesDisponibles;
	private Operaciones op;
	private JTextField txtArgumentos;
	private JTextField txtNumArgumentos;

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

		txtEntrada = new JTextPane();
		txtEntrada.setBounds(248, 12, 184, 205);
		frame.getContentPane().add(txtEntrada);

		txtNumEntradas = new JTextField();
		txtNumEntradas.setBounds(258, 236, 114, 24);
		txtNumEntradas.setEditable(false);
		txtNumEntradas.setText("Salida:");
		frame.getContentPane().add(txtNumEntradas);
		txtNumEntradas.setColumns(10);

		textFieldSalidaMetodos = new JTextField();
		textFieldSalidaMetodos.setBounds(384, 236, 39, 24);
		textFieldSalidaMetodos.setEditable(false);
		frame.getContentPane().add(textFieldSalidaMetodos);
		textFieldSalidaMetodos.setColumns(10);

		txtArgumentos = new JTextField();
		txtArgumentos.setEditable(false);
		txtArgumentos.setText("Argumentos:");
		txtArgumentos.setBounds(12, 135, 114, 24);
		frame.getContentPane().add(txtArgumentos);
		txtArgumentos.setColumns(10);

		txtNumArgumentos = new JTextField();
		txtNumArgumentos.setEditable(false);
		txtNumArgumentos.setColumns(10);
		txtNumArgumentos.setBounds(138, 135, 39, 24);
		frame.getContentPane().add(txtNumArgumentos);

		operacionesDisponibles = new String[Operaciones.getListaMetodos().size()];

		for(int i=0;i<Operaciones.getListaMetodos().size();i++){
			operacionesDisponibles[i] = Operaciones.getListaMetodos().get(i).getNombreClass();
		}

		listaMetodosUtilizables = new JComboBox<String>(operacionesDisponibles);
		listaMetodosUtilizables.setBounds(12, 99, 114, 24);

		listaMetodosUtilizables.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				operacionesDisponibles = new String[Operaciones.getListaMetodos().size()];
				
				for(int i=0;i<Operaciones.getListaMetodos().size();i++){
					operacionesDisponibles[i] = Operaciones.getListaMetodos().get(i).getNombreClass();
				}
				
				for(int i = 0;i < operacionesDisponibles.length;i++){
					if(listaMetodosUtilizables.getSelectedItem().equals(operacionesDisponibles[i])){
						txtNumArgumentos.setText("" + Operaciones.getListaMetodos().get(i).getNumArgumentos());
					}
				}
			}
		});
		
		txtNumArgumentos.setText("" + Operaciones.getListaMetodos().get(listaMetodosUtilizables.getSelectedIndex()).getNumArgumentos());

		frame.getContentPane().add(listaMetodosUtilizables);


		JButton btnCrear = new JButton("New");
		btnCrear.setBounds(12, 12, 114, 25);
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				try {
					String input = "Aux"; //txtEntrada.getText().toString();
					Operaciones.nuevaOperacion(input);
					
					boolean existe = false;
					for(int i = 0 ; i < listaMetodosUtilizables.getItemCount(); i++){
						if(listaMetodosUtilizables.getItemAt(i).equals(input)){
							existe = true;
						}
					}
					
					if(existe == false) listaMetodosUtilizables.addItem(input);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnCrear);

		JButton btnUtilizar = new JButton("Use");
		btnUtilizar.setBounds(12, 49, 114, 25);
		btnUtilizar.addActionListener(new ActionListener() {
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
		frame.getContentPane().add(btnUtilizar);
	}
}
