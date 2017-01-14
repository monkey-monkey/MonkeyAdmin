import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JToggleButton;

public class Menu extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private ArrayList<JButton> actionBtn = new ArrayList<JButton>();
	private ArrayList<JToggleButton> levelBtn = new ArrayList<JToggleButton>();
	
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
		lblId.setFont(new Font("Cordia New", Font.PLAIN, 60));
		lblId.setBounds(70, 60, 288, 50);
		contentPane.add(lblId);
		
		JLabel lblWorksheetSet = new JLabel("Worksheet Set");
		lblWorksheetSet.setFont(new Font("Cordia New", Font.PLAIN, 45));
		lblWorksheetSet.setBounds(496, 60, 196, 50);
		contentPane.add(lblWorksheetSet);
		
		textField = new JTextField();
		textField.setFont(new Font("Cordia New", Font.PLAIN, 40));
		textField.setEditable(false);
		textField.setBounds(702, 63, 271, 50);
		contentPane.add(textField);
		textField.setColumns(10);
		
//		JToggleButton tglbtnMj = new JToggleButton("MJ");
//		tglbtnMj.setBackground(new Color(255, 204, 0));
//		tglbtnMj.setFont(new Font("Cordia New", Font.PLAIN, 80));
//		tglbtnMj.setBounds(100, 250, 114, 96);
//		contentPane.add(tglbtnMj);
		
		
		levelBtn.add(new JToggleButton("MK"));
		levelBtn.add(new JToggleButton("MJ"));
		levelBtn.add(new JToggleButton("MH"));
		levelBtn.add(new JToggleButton("MI"));
		levelBtn.add(new JToggleButton("PJ"));
		levelBtn.add(new JToggleButton("PH"));
		levelBtn.add(new JToggleButton("PI"));
		
		for (int i = 0; i < levelBtn.size(); i++) {
			levelBtn.get(i).setBackground(new Color(255, 204, 0));
			levelBtn.get(i).setFont(new Font("Cordia New", Font.PLAIN, 80));
			levelBtn.get(i).setBounds(100, 200 + (i * 110), 120, 90);
			contentPane.add(levelBtn.get(i));
		}
		
		
		/**
		 * Create object action Button
		 */
		actionBtn.add(new JButton("Print & VDO"));
		actionBtn.add(new JButton("Print"));
		actionBtn.add(new JButton("VDO"));
		
		/**
		 * Set attribute for action button
		 */
		for (int i = 0; i < actionBtn.size(); i++) {
			actionBtn.get(i).setFont(new Font("Cordia New", Font.PLAIN, 40));
			actionBtn.get(i).setBackground(Color.WHITE);
		}
		
		/**
		 * Set position of action button
		 */
		actionBtn.get(0).setBounds(1171, 60, 180, 50);
		actionBtn.get(1).setBounds(1451, 60, 151, 50);
		actionBtn.get(2).setBounds(1692, 60, 151, 50);
		
		
		/**
		 * Add action button to content pane
		 */
		for (int i = 0; i < actionBtn.size(); i++) {
			contentPane.add(actionBtn.get(i));
		}
		
		
		
	}
}
