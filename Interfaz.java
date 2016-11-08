import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;


public class Interfaz {

	private JFrame frame;
	private JTextField textFieldNombre;
	private JTextField txtNumEntradas;
	private JTextField textFieldNumEntradas;
	private JTextField txtCuerpoFuncion;
	
	private Operaciones op = new Operaciones();

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
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(268, 28, 148, 30);
		frame.getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		txtNumEntradas = new JTextField();
		txtNumEntradas.setEditable(false);
		txtNumEntradas.setText("NumEntradas");
		txtNumEntradas.setBounds(25, 198, 114, 19);
		frame.getContentPane().add(txtNumEntradas);
		txtNumEntradas.setColumns(10);
		
		textFieldNumEntradas = new JTextField();
		textFieldNumEntradas.setBounds(164, 198, 39, 19);
		frame.getContentPane().add(textFieldNumEntradas);
		textFieldNumEntradas.setColumns(10);
		
		txtCuerpoFuncion = new JTextField();
		txtCuerpoFuncion.setEditable(false);
		txtCuerpoFuncion.setText("Cuerpo Funcion");
		txtCuerpoFuncion.setBounds(25, 222, 114, 19);
		frame.getContentPane().add(txtCuerpoFuncion);
		txtCuerpoFuncion.setColumns(10);
		
		JButton btnCrearcrea = new JButton("New Operation");
		btnCrearcrea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				try {
					Operaciones.nuevaOperacion(textFieldNombre.getText());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCrearcrea.setBounds(12, 12, 148, 25);
		frame.getContentPane().add(btnCrearcrea);
		
		JButton btnUtilizarNueva = new JButton("Use Operation");
		btnUtilizarNueva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Operaciones.usarOperacion();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnUtilizarNueva.setBounds(12, 49, 148, 25);
		frame.getContentPane().add(btnUtilizarNueva);
	}
}
