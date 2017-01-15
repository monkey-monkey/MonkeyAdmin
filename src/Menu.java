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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Menu extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private ArrayList<JButton> actionBtn = new ArrayList<JButton>();
	private ArrayList<JToggleButton> subjectBtn = new ArrayList<JToggleButton>();
	private ArrayList<JToggleButton> levelBtn = new ArrayList<JToggleButton>();
	private ArrayList<JToggleButton> subLevelBtn = new ArrayList<JToggleButton>();
	private ArrayList<JButton> numpadBtn = new ArrayList<JButton>();
	private ArrayList<JToggleButton> subSheetBtn = new ArrayList<JToggleButton>();
	private ArrayList<JToggleButton> sheetSetBtn = new ArrayList<JToggleButton>();
	private String sheetNum = "";
	
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
	@SuppressWarnings("deprecation")
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
		lblWorksheetSet.setFont(new Font("Cordia New", Font.PLAIN, 55));
		lblWorksheetSet.setBounds(368, 61, 247, 50);
		contentPane.add(lblWorksheetSet);
		
		textField = new JTextField();
		textField.setFont(new Font("Cordia New", Font.PLAIN, 85));
		textField.setEditable(false);
		textField.setBounds(625, 33, 477, 80);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		/**
		 * Create object action button
		 */
		actionBtn.add(new JButton("Print & VDO"));
		actionBtn.add(new JButton("Print"));
		actionBtn.add(new JButton("VDO"));
		
		/**
		 * Set position of action button
		 */
		actionBtn.get(0).setBounds(1171, 60, 180, 50);
		actionBtn.get(1).setBounds(1451, 60, 151, 50);
		actionBtn.get(2).setBounds(1692, 60, 151, 50);
		
		/**
		 * Set attribute for action button and add to content pane
		 */
		for (int i = 0; i < actionBtn.size(); i++) {
			actionBtn.get(i).setFont(new Font("Cordia New", Font.PLAIN, 40));
			actionBtn.get(i).setBackground(Color.WHITE);
			contentPane.add(actionBtn.get(i));
		}
		
		/**
		 * Create object subject button
		 */
		subjectBtn.add(new JToggleButton("MK"));
		subjectBtn.add(new JToggleButton("MJ"));
		subjectBtn.add(new JToggleButton("MH"));
		subjectBtn.add(new JToggleButton("MI"));
		subjectBtn.add(new JToggleButton("PJ"));
		subjectBtn.add(new JToggleButton("PH"));
		subjectBtn.add(new JToggleButton("PI"));
		
		/**
		 * Set attribute, position, event listener to level button
		 */
		for (int i = 0; i < subjectBtn.size(); i++) {
			subjectBtn.get(i).setBackground((subjectBtn.get(i).getLabel().charAt(0) == 'M') ? new Color(239, 163, 21) : new Color(208, 137, 244));
			subjectBtn.get(i).setFont(new Font("Cordia New", Font.PLAIN, 80));
			subjectBtn.get(i).setBounds(100, 200 + (i * 110), 120, 90);
			final int temp = i;
			subjectBtn.get(i).addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					for (int j = 0; j < subjectBtn.size(); j++) {
						if (temp == j) continue;
						subjectBtn.get(j).setSelected(false);
						setText();
					}
				}
			});
			contentPane.add(subjectBtn.get(i));
		}
		
		/**
		 * Create object level button
		 */
		for (int i = 0; i < 26; i++) {
			levelBtn.add(new JToggleButton(Character.toString((char)(65 + i))));
		}
		levelBtn.add(new JToggleButton("XC"));
		levelBtn.add(new JToggleButton("XCA"));
		levelBtn.add(new JToggleButton("XCN"));
		levelBtn.add(new JToggleButton("XEL"));
		levelBtn.add(new JToggleButton("XF"));
		levelBtn.add(new JToggleButton("XG"));
		levelBtn.add(new JToggleButton("XL"));
		levelBtn.add(new JToggleButton("XM"));
		levelBtn.add(new JToggleButton("XN"));
		levelBtn.add(new JToggleButton("XP"));
		levelBtn.add(new JToggleButton("XR"));
		levelBtn.add(new JToggleButton("XS"));
		levelBtn.add(new JToggleButton("XSS"));
		levelBtn.add(new JToggleButton("XST"));
		levelBtn.add(new JToggleButton("XT"));
		levelBtn.add(new JToggleButton("XV"));
		
		/**
		 * Set attribute, position, event listener to level button
		 */
		int index = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 9; j++) {
				try {
					levelBtn.get(index).setBackground(Color.WHITE);
					levelBtn.get(index).setFont(new Font("Cordia New", Font.PLAIN, 70));
					levelBtn.get(index).setBounds(300 + (i * 160), 200 + (j * 90), 150, 80);
					final int temp = index;
					levelBtn.get(index).addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							for (int k = 0; k < levelBtn.size(); k++) {
								if (temp == k) continue;
								levelBtn.get(k).setSelected(false);
							}
							setText();
						}
					});
					contentPane.add(levelBtn.get(index));
					index++;
				} catch (Exception e) {
				}
			}
		}
		
		/**
		 * Create object sub level button
		 */
		subLevelBtn.add(new JToggleButton("B"));
		subLevelBtn.add(new JToggleButton("I"));
		subLevelBtn.add(new JToggleButton("E"));
		subLevelBtn.add(new JToggleButton("P"));
		subLevelBtn.add(new JToggleButton("A"));
		subLevelBtn.add(new JToggleButton("T"));
		
		
		/**
		 * Set attribute, position, event listener to sub level button
		 */
		
		for (int i = 0; i < subLevelBtn.size(); i++) {
			subLevelBtn.get(i).setBackground(Color.WHITE);
			subLevelBtn.get(i).setFont(new Font("Cordia New", Font.PLAIN, 70));
			subLevelBtn.get(i).setBounds(1150, 200 + (i * 110), 100, 80);
			final int temp = i;
			subLevelBtn.get(i).addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					for (int j = 0; j < subLevelBtn.size(); j++) {
						if (j == temp) continue;
						subLevelBtn.get(j).setSelected(false);
					}
					setText();
				}
			});
			contentPane.add(subLevelBtn.get(i));
		}
		
		/**
		 * Create object number pad button
		 */
		numpadBtn.add(new JButton("7"));
		numpadBtn.add(new JButton("4"));
		numpadBtn.add(new JButton("1"));
		numpadBtn.add(new JButton("C"));
		numpadBtn.add(new JButton("8"));
		numpadBtn.add(new JButton("5"));
		numpadBtn.add(new JButton("2"));
		numpadBtn.add(new JButton("0"));
		numpadBtn.add(new JButton("9"));
		numpadBtn.add(new JButton("6"));
		numpadBtn.add(new JButton("3"));
		
		/**
		 * Set attribute, position, event listener to sub level button
		 */
		index = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				try {
					numpadBtn.get(index).setBackground(Color.WHITE);
					numpadBtn.get(index).setFont(new Font("Cordia New", Font.PLAIN, 70));
					numpadBtn.get(index).setBounds(1400 + (i * 110), 200 + (j * 110), 100, 100);
					final int temp = index;
					numpadBtn.get(index).addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if (numpadBtn.get(temp).getLabel().equals("C")) {
								sheetNum = "";
							} else {
								if (sheetNum.length() < 2) {
									sheetNum += numpadBtn.get(temp).getLabel();
								}else {
									sheetNum = sheetNum.substring(1) + numpadBtn.get(temp).getLabel();
								}
							}
							setText();
						}
					});
					contentPane.add(numpadBtn.get(index));
					index++;
				} catch (Exception e) {
				}
			}
		}
		
		/**
		 * Create object sub sheet button
		 */
		for (int i = 0; i < 3; i++) {
			subSheetBtn.add(new JToggleButton(Character.toString((char)(i + 97))));
		}
		
		/**
		 * Set attribute, position, event listener to sub sheet button
		 */
		for (int i = 0; i < subSheetBtn.size(); i++) {
			subSheetBtn.get(i).setBackground(Color.WHITE);
			subSheetBtn.get(i).setFont(new Font("Cordia New", Font.PLAIN, 70));
			subSheetBtn.get(i).setBounds(1400 + (i * 110), 700, 100, 100);
			final int temp = i;
			subSheetBtn.get(i).addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					for (int j = 0; j < subSheetBtn.size(); j++) {
						if (j == temp) continue;
						subSheetBtn.get(j).setSelected(false);
					}
					setText();
				}
			});
			contentPane.add(subSheetBtn.get(i));
		}
		
		/**
		 * Create object sheet set button
		 */
		sheetSetBtn.add(new JToggleButton("VDO"));
		sheetSetBtn.add(new JToggleButton("SKILL"));
		sheetSetBtn.add(new JToggleButton("HW"));
		sheetSetBtn.add(new JToggleButton("TEST"));
		
		/**
		 * Set attribute, position, event listener to sheet set button
		 */
		for (int i = 0; i < sheetSetBtn.size(); i++) {
			sheetSetBtn.get(i).setBackground(Color.WHITE);
			sheetSetBtn.get(i).setFont(new Font("Cordia New", Font.PLAIN, 60));
			sheetSetBtn.get(i).setBounds(1100 + (200 * i), 880, 180, 80);
			final int temp = i;
			sheetSetBtn.get(i).addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					for (int j = 0; j < sheetSetBtn.size(); j++) {
						if (j == temp) continue;
						sheetSetBtn.get(j).setSelected(false);
					}
					setText();
				}
			});
			contentPane.add(sheetSetBtn.get(i));
		}
	}
	
	@SuppressWarnings("deprecation")
	private void setText() {
		String temp = "";
		for (int i = 0; i < subjectBtn.size(); i++) {
			if (subjectBtn.get(i).isSelected()) temp += subjectBtn.get(i).getLabel();
		}
		temp += "-";
		for (int i = 0; i < levelBtn.size(); i++) {
			if (levelBtn.get(i).isSelected()) temp += levelBtn.get(i).getLabel();
		}
		for (int i = 0; i < subLevelBtn.size(); i++) {
			if (subLevelBtn.get(i).isSelected()) temp += subLevelBtn.get(i).getLabel();
		}
		temp += sheetNum;
		for (int i = 0; i < subSheetBtn.size(); i++) {
			if (subSheetBtn.get(i).isSelected()) temp += subSheetBtn.get(i).getLabel();
		}
		for (int i = 0; i < sheetSetBtn.size(); i++) {
			if (sheetSetBtn.get(i).isSelected()) temp += sheetSetBtn.get(i).getLabel();
		}
		textField.setText(temp);
	}
}
