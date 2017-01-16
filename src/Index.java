/**
Copyright [2017] [Chutipon]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Index extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	public static final String VDO_LOCATION = "\\\\192.168.1.150\\vdo\\";
	public static final String DB_LOCATION = "\\\\192.168.1.150\\database\\";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index frame = new Index();
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
	public Index() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Scan Student ID");
		lblNewLabel.setFont(new Font("Cordia New", Font.PLAIN, 90));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(654, 321, 558, 104);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Cordia New", Font.PLAIN, 50));
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyChar() == '\n') {
					String idInput[] = {textField.getText()};
					textField.setText("");
					Menu.main(idInput);
				}
			}
		});
		textField.setBounds(514, 495, 839, 95);
		contentPane.add(textField);
		textField.setColumns(10);
	}
	
	public void run(String id) {
		if (!isValid(id)) {
			JOptionPane.showMessageDialog(null, "Wrong student ID", "Error: ID input not found", JOptionPane.INFORMATION_MESSAGE);
			textField.setText("");
			return;
		}
		this.setVisible(false);
		String[] input = {id};
		Menu.main(input);
		this.setVisible(true);
	}
	
	public boolean isValid(String id) {
		if (id.length() == 6 && (id.charAt(id.length() - 1) == '1' || id.charAt(id.length() - 1) == '2') && isInDB(id)) {
			return true;
		}
		return false;
	}
	
	public boolean isInDB(String id) {
		try {
			File folder = new File(VDO_LOCATION + id);
			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
				} else if (listOfFiles[i].isDirectory()) {
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
