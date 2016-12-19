/**
Copyright [2016] [Chutipon]

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

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private ImageIcon nonSelect = new ImageIcon("ic_radio_button_off_black_36dp.png");
	private ImageIcon select = new ImageIcon("ic_radio_button_on_black_36dp.png");
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu(args[0]);
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
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 919, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWorksheetSet = new JLabel("Worksheet Set");
		lblWorksheetSet.setFont(new Font("Cordia New", Font.PLAIN, 30));
		lblWorksheetSet.setHorizontalAlignment(SwingConstants.LEFT);
		lblWorksheetSet.setBounds(52, 109, 171, 35);
		contentPane.add(lblWorksheetSet);
		
		textField = new JTextField();
		textField.setBounds(52, 179, 287, 67);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblId = new JLabel("ID: " + id);
		lblId.setHorizontalAlignment(SwingConstants.LEFT);
		lblId.setFont(new Font("Cordia New", Font.PLAIN, 45));
		lblId.setBounds(52, 43, 257, 41);
		contentPane.add(lblId);
		
		JRadioButton rdbtnFull = new JRadioButton("FULL");
		rdbtnFull.setFont(new Font("Cordia New", Font.PLAIN, 35));
		JRadioButton rdbtnSkill = new JRadioButton("SKILL");
		rdbtnSkill.setFont(new Font("Cordia New", Font.PLAIN, 35));
		JRadioButton rdbtnTest = new JRadioButton("TEST");
		rdbtnTest.setFont(new Font("Cordia New", Font.PLAIN, 35));
		JRadioButton rdbtnVdo = new JRadioButton("VDO");
		rdbtnVdo.setFont(new Font("Cordia New", Font.PLAIN, 35));
		JRadioButton rdbtnHw = new JRadioButton("HW");
		rdbtnHw.setFont(new Font("Cordia New", Font.PLAIN, 35));
		
		/**
		 * Set Init icon
		 */
		rdbtnFull.setIcon(nonSelect);
		rdbtnSkill.setIcon(nonSelect);
		rdbtnTest.setIcon(nonSelect);
		rdbtnVdo.setIcon(nonSelect);
		rdbtnHw.setIcon(nonSelect);
		
		
		/**
		 * RadioButton "FULL"
		 */
		rdbtnFull.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				rdbtnFull.setIcon((rdbtnFull.isSelected()) ? select : nonSelect);
				rdbtnSkill.setIcon(nonSelect);
				rdbtnTest.setIcon(nonSelect);
				rdbtnVdo.setIcon(nonSelect);
				rdbtnHw.setIcon(nonSelect);
				rdbtnSkill.setSelected(false);
				rdbtnTest.setSelected(false);
				rdbtnVdo.setSelected(false);
				rdbtnHw.setSelected(false);
			}
		});
		rdbtnFull.setBounds(437, 113, 123, 38);
		contentPane.add(rdbtnFull);
		
		
		/**
		 * RadioButton "SKILL"
		 */
		rdbtnSkill.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				rdbtnFull.setIcon(nonSelect);
				rdbtnSkill.setIcon((rdbtnSkill.isSelected()) ? select : nonSelect);
				rdbtnTest.setIcon(nonSelect);
				rdbtnVdo.setIcon(nonSelect);
				rdbtnHw.setIcon(nonSelect);
				rdbtnFull.setSelected(false);
				rdbtnTest.setSelected(false);
				rdbtnVdo.setSelected(false);
				rdbtnHw.setSelected(false);
			}
		});
		rdbtnSkill.setBounds(574, 112, 109, 41);
		contentPane.add(rdbtnSkill);
		
		
		/**
		 * RadioButton "TEST"
		 */
		rdbtnTest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				rdbtnFull.setIcon(nonSelect);
				rdbtnSkill.setIcon(nonSelect);
				rdbtnTest.setIcon((rdbtnTest.isSelected()) ? select : nonSelect);
				rdbtnVdo.setIcon(nonSelect);
				rdbtnHw.setIcon(nonSelect);
				rdbtnFull.setSelected(false);
				rdbtnSkill.setSelected(false);
				rdbtnVdo.setSelected(false);
				rdbtnHw.setSelected(false);
			}
		});
		rdbtnTest.setBounds(707, 121, 123, 41);
		contentPane.add(rdbtnTest);
		
		
		/**
		 * RadioButton "VDO"
		 */
		rdbtnVdo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				rdbtnFull.setIcon(nonSelect);
				rdbtnSkill.setIcon(nonSelect);
				rdbtnTest.setIcon(nonSelect);
				rdbtnVdo.setIcon((rdbtnVdo.isSelected()) ? select : nonSelect);
				rdbtnHw.setIcon(nonSelect);
				rdbtnFull.setSelected(false);
				rdbtnSkill.setSelected(false);
				rdbtnTest.setSelected(false);
				rdbtnHw.setSelected(false);
			}
		});
		rdbtnVdo.setBounds(414, 273, 146, 41);
		contentPane.add(rdbtnVdo);
		
		
		/**
		 * RadioButton "HW"
		 */
		rdbtnHw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				rdbtnFull.setIcon(nonSelect);
				rdbtnSkill.setIcon(nonSelect);
				rdbtnTest.setIcon(nonSelect);
				rdbtnVdo.setIcon(nonSelect);
				rdbtnHw.setIcon((rdbtnHw.isSelected()) ? select : nonSelect);
				rdbtnFull.setSelected(false);
				rdbtnSkill.setSelected(false);
				rdbtnTest.setSelected(false);
				rdbtnVdo.setSelected(false);
			}
		});
		rdbtnHw.setBounds(671, 273, 139, 41);
		contentPane.add(rdbtnHw);
	}
}
