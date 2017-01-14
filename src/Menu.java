import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class Menu extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu("Hello");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu(String id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("ID: " + id);
		lblId.setFont(new Font("Cordia New", Font.PLAIN, 50));
		lblId.setBounds(70, 60, 245, 50);
		contentPane.add(lblId);
		
		JLabel lblWorksheetSet = new JLabel("Worksheet Set");
		lblWorksheetSet.setFont(new Font("Cordia New", Font.PLAIN, 35));
		lblWorksheetSet.setBounds(529, 60, 163, 50);
		contentPane.add(lblWorksheetSet);
		
		textField = new JTextField();
		textField.setFont(new Font("Cordia New", Font.PLAIN, 40));
		textField.setEditable(false);
		textField.setBounds(734, 60, 271, 50);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnVdo = new JButton("Print & VDO");
		btnVdo.setBackground(Color.WHITE);
		btnVdo.setFont(new Font("Cordia New", Font.PLAIN, 40));
		btnVdo.setBounds(1171, 60, 180, 50);
		contentPane.add(btnVdo);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setBackground(Color.WHITE);
		btnPrint.setFont(new Font("Cordia New", Font.PLAIN, 40));
		btnPrint.setBounds(1451, 60, 151, 50);
		contentPane.add(btnPrint);
		
		JButton button_1 = new JButton("VDO");
		button_1.setBackground(Color.WHITE);
		button_1.setFont(new Font("Cordia New", Font.PLAIN, 40));
		button_1.setBounds(1692, 60, 151, 50);
		contentPane.add(button_1);
	}
}
