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
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Menu extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private ImageIcon nonSelect = new ImageIcon("ic_radio_button_off_black_36dp.png");
	private ImageIcon select = new ImageIcon("ic_radio_button_on_black_36dp.png");
	private JRadioButton rdbtnFull, rdbtnSkill, rdbtnTest, rdbtnVdo, rdbtnHw, rdbtnMk, rdbtnMj, rdbtnMh, rdbtnPj, rdbtnPh;
	

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
		
		rdbtnFull = new JRadioButton("FULL");
		rdbtnFull.setFont(new Font("Cordia New", Font.PLAIN, 35));
		rdbtnSkill = new JRadioButton("SKILL");
		rdbtnSkill.setFont(new Font("Cordia New", Font.PLAIN, 35));
		rdbtnTest = new JRadioButton("TEST");
		rdbtnTest.setFont(new Font("Cordia New", Font.PLAIN, 35));
		rdbtnVdo = new JRadioButton("VDO");
		rdbtnVdo.setFont(new Font("Cordia New", Font.PLAIN, 35));
		rdbtnHw = new JRadioButton("HW");
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
				setText();
			}
		});
		rdbtnFull.setBounds(759, 59, 123, 38);
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
				setText();
			}
		});
		rdbtnSkill.setBounds(758, 205, 109, 41);
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
				setText();
			}
		});
		rdbtnTest.setBounds(759, 274, 123, 41);
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
				setText();
			}
		});
		rdbtnVdo.setBounds(759, 124, 146, 41);
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
				setText();
			}
		});
		rdbtnHw.setBounds(759, 335, 139, 41);
		contentPane.add(rdbtnHw);
		
		rdbtnMk = new JRadioButton("MK");
		rdbtnMk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});
		rdbtnMk.setFont(new Font("Dialog", Font.PLAIN, 35));
		rdbtnMk.setBounds(366, 63, 123, 38);
		contentPane.add(rdbtnMk);
		
		rdbtnMj = new JRadioButton("MJ");
		rdbtnMj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});
		rdbtnMj.setFont(new Font("Dialog", Font.PLAIN, 35));
		rdbtnMj.setBounds(366, 141, 123, 38);
		contentPane.add(rdbtnMj);
		
		rdbtnMh = new JRadioButton("MH");
		rdbtnMh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});
		rdbtnMh.setFont(new Font("Dialog", Font.PLAIN, 35));
		rdbtnMh.setBounds(366, 222, 123, 38);
		contentPane.add(rdbtnMh);
		
		rdbtnPj = new JRadioButton("PJ");
		rdbtnPj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});
		rdbtnPj.setFont(new Font("Dialog", Font.PLAIN, 35));
		rdbtnPj.setBounds(366, 291, 123, 38);
		contentPane.add(rdbtnPj);
		
		rdbtnPh = new JRadioButton("PH");
		rdbtnPh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});
		rdbtnPh.setFont(new Font("Dialog", Font.PLAIN, 35));
		rdbtnPh.setBounds(366, 352, 123, 38);
		contentPane.add(rdbtnPh);
		
	}
	
	public void setText() {
		String text = "";
		if (rdbtnFull.isSelected()) {
			text += "FULL";
		}
		if (rdbtnSkill.isSelected()) {
			text += "SKILL";
		}
		if (rdbtnTest.isSelected()) {
			text += "TEST";
		}
		if (rdbtnVdo.isSelected()) {
			text += "VDO";
		}
		if (rdbtnHw.isSelected()) {
			text += "HW";
		}
		textField.setText(text);
	}
}
