import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;


public class Interfaz {

	private JFrame frame;
	private JTextField textField;
	private JTextField txtResultado;
	private JTextField textField_1;
	private Operacion op;

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
		
		textField = new JTextField();
		textField.setBounds(268, 28, 148, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		txtResultado = new JTextField();
		txtResultado.setEditable(false);
		txtResultado.setText("Resultado:");
		txtResultado.setBounds(25, 222, 114, 19);
		frame.getContentPane().add(txtResultado);
		txtResultado.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(164, 222, 252, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnCrearcrea = new JButton("New Operation");
		btnCrearcrea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				try {
					op = new Operacion(textField.getText());
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
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
					Operacion.usarOperacion();
					textField_1.setText("Hecho. Comprobar consola.");
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
