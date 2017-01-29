/*
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
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

@SuppressWarnings("serial")
public class Menu extends JFrame {

    private JTextField textField;
    private ArrayList<JButton> actionBtn = new ArrayList<>();
    private ArrayList<JToggleButton> subjectBtn = new ArrayList<>();
    private ArrayList<JToggleButton> levelBtn = new ArrayList<>();
    private ArrayList<JToggleButton> subLevelBtn = new ArrayList<>();
    private ArrayList<JButton> numberPadBtn = new ArrayList<>();
    private ArrayList<JToggleButton> subSheetBtn = new ArrayList<>();
    private ArrayList<JToggleButton> sheetSetBtn = new ArrayList<>();
    private ArrayList<String> listAvailableLevel, listAvailableSubLevel, listAvailableLevelNumber;
    private Character subSheetChar;
    private String sheetNum = "00", currentSheetCode = "";
    private static Menu frame;

    /*
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                frame = new Menu("159991");
//                frame = new Menu(args[0]);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /*
     * Create the frame.
     */
    @SuppressWarnings("deprecation")
    private Menu(String id) throws Exception {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1920, 1080);
        JPanel contentPane = new JPanel();
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
        textField.setFont(new Font("Cordia New", Font.PLAIN, 150));
        textField.setEditable(false);
        textField.setBounds(625, 33, 1200, 120);
        contentPane.add(textField);
        textField.setColumns(10);

		
		/*
         * Create object action button
		 */
        actionBtn.add(new JButton("VDO"));
        actionBtn.add(new JButton("Print"));
        actionBtn.add(new JButton("Print & VDO"));

		/*
         * Set position of action button
		 */
        actionBtn.get(0).setBounds(1650, 400, 220, 120);
        actionBtn.get(1).setBounds(1650, 890, 200, 100);
        actionBtn.get(2).setBounds(1650, 200, 220, 120);

		/*
         * Add action to action button
		 */

        /*
         * VDO button
         */
        actionBtn.get(0).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DecodeSubjectName dbPath = new DecodeSubjectName(textField.getText());
                if (isSubSheetBtnIsSelected()) {
                    copy(dbPath + textField.getText() + "VDO.mp4", Index.VDO_LOCATION + id + "\\" + textField.getText() + "VDO.mp4");
                } else {
                    copy(dbPath + "VDO.mp4", Index.VDO_LOCATION + id + "\\" + textField.getText() + "VDO.mp4");
                }
                clear();
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });

        /*
         * Print button
         */
        actionBtn.get(1).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DecodeSubjectName dbPath = new DecodeSubjectName(currentSheetCode + sheetNum);
                String levelLabel = null;
                for (JToggleButton aSheetSetBtn : sheetSetBtn) {
                    if (aSheetSetBtn.isSelected()) levelLabel = aSheetSetBtn.getLabel();
                }
                System.out.println(dbPath);
                if (isSubSheetBtnIsSelected()) {
                    print(dbPath + textField.getText() + levelLabel + ".pdf");
                } else {
                    print(dbPath + levelLabel + ".pdf");
                }
                clear();
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });

        /*
         * Print and VDO button
         */
        actionBtn.get(2).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DecodeSubjectName dbPath = new DecodeSubjectName(textField.getText());
                System.out.println("From:" + dbPath + "VDO.mp4");
                System.out.println("To:" + Index.VDO_LOCATION + id + "\\" + textField.getText() + "VDO.mp4");
                if (isSubSheetBtnIsSelected()) {
                    copy(dbPath + textField.getText() + "VDO.mp4", Index.VDO_LOCATION + id + "\\" + textField.getText() + "VDO.mp4");
                } else {
                    copy(dbPath + "VDO.mp4", Index.VDO_LOCATION + id + "\\" + textField.getText() + "VDO.mp4");
                }
                System.out.println(dbPath);
                if (isSubSheetBtnIsSelected()) {
                    print(dbPath + textField.getText() + "FULL.pdf");
                } else {
                    print(dbPath + "FULL.pdf");
                }
                clear();
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });

        /*
          Set attribute for action button and add to content pane
         */
        for (JButton anActionBtn : actionBtn) {
            anActionBtn.setFont(new Font("Cordia New", Font.PLAIN, 50));
            anActionBtn.setBackground(Color.WHITE);
            anActionBtn.setEnabled(false);
            contentPane.add(anActionBtn);
        }

		/*
         * Create object subject button
		 */
        subjectBtn.add(new JToggleButton("MK"));
        subjectBtn.add(new JToggleButton("MJ"));
        subjectBtn.add(new JToggleButton("MH"));
//		subjectBtn.add(new JToggleButton("MI"));
        subjectBtn.add(new JToggleButton("PJ"));
        subjectBtn.add(new JToggleButton("PH"));
//		subjectBtn.add(new JToggleButton("PI"));

		/*
         * Set attribute, position, event listener to level button
		 */
        for (int i = 0; i < subjectBtn.size(); i++) {
            subjectBtn.get(i).setBackground((subjectBtn.get(i).getLabel().charAt(0) == 'M') ? new Color(239, 163, 21) : new Color(208, 137, 244));
            subjectBtn.get(i).setFont(new Font("Cordia New", Font.PLAIN, 80));
            subjectBtn.get(i).setBounds(70, 200 + (i * 110), 120, 90);
            final int temp = i;
            subjectBtn.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (int j = 0; j < subjectBtn.size(); j++) {
                        if (temp == j) continue;
                        subjectBtn.get(j).setSelected(false);
                    }
                    clearLevelBtn();
                    clearSubLevelButton();
                    clearSubSheetButton();
                    clearSheetSetBtn();
                    FileUtil getList = new FileUtil(Index.DB_LOCATION + ((subjectBtn.get(temp).getLabel().charAt(0) == 'M') ? "MATH_DB" : "PHYSICS_DB") + "\\" + subjectBtn.get(temp).getLabel() + "\\");
                    try {
                        listAvailableLevel = getList.getListNameFromFolder();
                    } catch (FileNotFoundException ignored) {
                    }
                    if (!subjectBtn.get(temp).isSelected()) {
                        clearLevelBtn();
                        clearSubLevelButton();
                        clearSubSheetButton();
                        clearSheetSetBtn();
                        clearActionBtn();
                    }
                    setText();

                }
            });
            contentPane.add(subjectBtn.get(i));
        }

		/*
         * Create object level button
		 */
        for (int i = 0; i < 23; i++) {
            levelBtn.add(new JToggleButton(Character.toString((char) (65 + i))));
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
        levelBtn.add(new JToggleButton("MK"));
        levelBtn.add(new JToggleButton("MS"));
        levelBtn.add(new JToggleButton("MD"));

		/*
         * Set attribute, position, event listener to level button
		 */
        int index = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                try {
                    levelBtn.get(index).setBackground(Color.WHITE);
                    levelBtn.get(index).setFont(new Font("Cordia New", Font.PLAIN, 70));
                    levelBtn.get(index).setBounds(240 + (i * 160), 200 + (j * 90), 150, 80);
                    levelBtn.get(index).setEnabled(false);
                    final int temp = index;
                    levelBtn.get(index).addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            for (int k = 0; k < levelBtn.size(); k++) {
                                if (temp == k) continue;
                                levelBtn.get(k).setSelected(false);
                            }
                            clearSubLevelButton();
                            clearSubSheetButton();
                            clearSheetSetBtn();
                            if (levelBtn.get(temp).isSelected()) {
                                setText();
                                DecodeSubjectName path = new DecodeSubjectName(textField.getText() + "B00");
                                FileUtil folder = new FileUtil(path.getFullName());
                                listAvailableSubLevel = folder.getSubListNameFromFolder();
                            } else {
                                clearSubLevelButton();
                                clearSubSheetButton();
                                clearSheetSetBtn();
                                clearActionBtn();
                            }
                            setText();
                        }
                    });
                    contentPane.add(levelBtn.get(index));
                    index++;
                } catch (Exception ignored) {
                }
            }
        }

		/*
          Create object sub level button
		 */
        subLevelBtn.add(new JToggleButton("B"));
        subLevelBtn.add(new JToggleButton("I"));
        subLevelBtn.add(new JToggleButton("E"));
        subLevelBtn.add(new JToggleButton("P"));
        subLevelBtn.add(new JToggleButton("A"));
        subLevelBtn.add(new JToggleButton("T"));

		
		/*
          Set attribute, position, event listener to sub level button
		 */

        for (int i = 0; i < subLevelBtn.size(); i++) {
            subLevelBtn.get(i).setBackground(Color.WHITE);
            subLevelBtn.get(i).setFont(new Font("Cordia New", Font.PLAIN, 70));
            subLevelBtn.get(i).setBounds(1080, 200 + (i * 110), 100, 80);
            subLevelBtn.get(i).setEnabled(false);
            final int temp = i;
            subLevelBtn.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (int j = 0; j < subLevelBtn.size(); j++) {
                        if (j == temp) continue;
                        subLevelBtn.get(j).setSelected(false);
                    }
                    subSheetChar = subLevelBtn.get(temp).getLabel().charAt(0);
                    setText();
                }
            });
            contentPane.add(subLevelBtn.get(i));
        }

		/*
         * Create object number pad button
		 */
        numberPadBtn.add(new JButton("7"));
        numberPadBtn.add(new JButton("4"));
        numberPadBtn.add(new JButton("1"));
        numberPadBtn.add(new JButton("C"));
        numberPadBtn.add(new JButton("8"));
        numberPadBtn.add(new JButton("5"));
        numberPadBtn.add(new JButton("2"));
        numberPadBtn.add(new JButton("0"));
        numberPadBtn.add(new JButton("9"));
        numberPadBtn.add(new JButton("6"));
        numberPadBtn.add(new JButton("3"));

		/*
         * Set attribute, position, event listener to sub level button
		 */
        index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                try {
                    numberPadBtn.get(index).setBackground(Color.WHITE);
                    numberPadBtn.get(index).setFont(new Font("Cordia New", Font.PLAIN, 70));
                    numberPadBtn.get(index).setBounds(1250 + (i * 110), 200 + (j * 110), 100, 100);
                    final int temp = index;
                    numberPadBtn.get(index).addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (numberPadBtn.get(temp).getLabel().equals("C")) sheetNum = "00";
                            else if (sheetNum.length() < 2) sheetNum += numberPadBtn.get(temp).getLabel();
                            else sheetNum = sheetNum.substring(1) + numberPadBtn.get(temp).getLabel();
                            setText();
                            checkWorksheetNumber();
                            setEnableOfSubSheetSet();
                        }
                    });
                    contentPane.add(numberPadBtn.get(index));
                } catch (Exception ignored) {
                }
                index++;
            }
        }
		
		/*
		 * Create object sub sheet button
		 */
        for (int i = 0; i < 3; i++) {
            subSheetBtn.add(new JToggleButton(Character.toString((char) (i + 97))));
        }

		/*
		 * Set attribute, position, event listener to sub sheet button
		 */
        for (int i = 0; i < subSheetBtn.size(); i++) {
            subSheetBtn.get(i).setBackground(Color.WHITE);
            subSheetBtn.get(i).setFont(new Font("Cordia New", Font.PLAIN, 70));
            subSheetBtn.get(i).setBounds(1250 + (i * 110), 700, 100, 100);
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

		/*
		 * Create object sheet set button
		 */
        sheetSetBtn.add(new JToggleButton("VDO"));
        sheetSetBtn.add(new JToggleButton("SKILL"));
        sheetSetBtn.add(new JToggleButton("HW"));
        sheetSetBtn.add(new JToggleButton("TEST"));

		/*
		 * Set attribute, position, event listener to sheet set button
		 */
        for (int i = 0; i < sheetSetBtn.size(); i++) {
            sheetSetBtn.get(i).setBackground(Color.WHITE);
            sheetSetBtn.get(i).setFont(new Font("Cordia New", Font.PLAIN, 50));
            sheetSetBtn.get(i).setBounds(990 + (150 * i), 920, 130, 50);
            final int temp = i;
            sheetSetBtn.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (int j = 0; j < sheetSetBtn.size(); j++) {
                        if (j == temp) continue;
                        sheetSetBtn.get(j).setSelected(false);
                    }
                    setText();
                    if (sheetSetBtn.get(temp).isSelected()) {
                        actionBtn.get(0).setBackground(Color.WHITE);
                        actionBtn.get(1).setBackground(new Color(119, 234, 173));
                        actionBtn.get(2).setBackground(Color.WHITE);
                        actionBtn.get(0).setEnabled(false);
                        actionBtn.get(1).setEnabled(true);
                        actionBtn.get(2).setEnabled(false);
                    } else {
                        actionBtn.get(0).setBackground(new Color(119, 234, 173));
                        actionBtn.get(1).setBackground(Color.WHITE);
                        actionBtn.get(2).setBackground(new Color(119, 234, 173));
                        actionBtn.get(0).setEnabled(true);
                        actionBtn.get(1).setEnabled(false);
                        actionBtn.get(2).setEnabled(true);
                    }
                }
            });

            contentPane.add(sheetSetBtn.get(i));
        }
    }

    /*
     * Set text to text label
     */
    @SuppressWarnings("deprecation")
    private void setText() {
        String temp = "";
        for (JToggleButton aSubjectBtn : subjectBtn) {
            if (aSubjectBtn.isSelected()) temp += aSubjectBtn.getLabel();
        }
        temp += "-";
        for (JToggleButton aLevelBtn : levelBtn) {
            if (aLevelBtn.isSelected()) temp += aLevelBtn.getLabel();
        }
        setLevelColor();
        for (JToggleButton aSubLevelBtn : subLevelBtn) {
            if (aSubLevelBtn.isSelected()) temp += aSubLevelBtn.getLabel();
        }
        setSubLevelColor();
        currentSheetCode = temp;
        if (!sheetNum.equals("00")) {
            temp += sheetNum;
        }
        for (JToggleButton aSubSheetBtn : subSheetBtn) {
            if (aSubSheetBtn.isSelected()) temp += aSubSheetBtn.getLabel();
        }
        for (JToggleButton aSheetSetBtn : sheetSetBtn) {
            if (aSheetSetBtn.isSelected()) temp += aSheetSetBtn.getLabel();
        }
        textField.setText(temp);
    }

    /*
     * Set color of all button
     */
    @SuppressWarnings("deprecation")
    private void setLevelColor() {
        for (JToggleButton aLevelBtn : levelBtn) {
            aLevelBtn.setBackground(Color.WHITE);
            aLevelBtn.setEnabled(false);
            try {
                if (listAvailableLevel.contains(aLevelBtn.getLabel())) {
                    aLevelBtn.setBackground(new Color(119, 234, 173));
                    aLevelBtn.setEnabled(true);
                }
            } catch (Exception ignored) {
            }
        }
    }

    @SuppressWarnings("deprecation")
    private void setSubLevelColor() {
        for (JToggleButton aSubLevelBtn : subLevelBtn) {
            aSubLevelBtn.setBackground(Color.WHITE);
            aSubLevelBtn.setEnabled(false);
            try {
                if (listAvailableSubLevel.contains(aSubLevelBtn.getLabel())) {
                    aSubLevelBtn.setBackground(new Color(119, 234, 173));
                    aSubLevelBtn.setEnabled(true);
                }
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * Call copy method in file util
     *
     * @param oriPath original video path
     * @param desPath destination to copy
     */
    private void copy(String oriPath, String desPath) {
        System.out.println("Menu.copy() -> oriPath:" + oriPath + " desPath: " + desPath);
        FileUtil file = new FileUtil(oriPath);
        file.copy(desPath);
    }

    /**
     * The method that call print() from printUtil
     *
     * @param path path of the file to be print
     */
    private void print(String path) {
        PrintUtil printer = new PrintUtil(path);
        printer.print();
    }

    /*
     * Clear subject button
     */
    private void clearSubjectBtn() {
        for (JToggleButton aSubjectBtn : subjectBtn) {
            aSubjectBtn.setSelected(false);
            aSubjectBtn.setEnabled(false);
            aSubjectBtn.setBackground(Color.WHITE);
        }
    }

    /*
     * Clear level button
     */
    private void clearLevelBtn() {
        listAvailableLevel = null;
        for (JToggleButton aLevelBtn : levelBtn) {
            aLevelBtn.setSelected(false);
            aLevelBtn.setEnabled(false);
            aLevelBtn.setBackground(Color.WHITE);
        }
    }

    /*
     * Clear sub level button
     */
    private void clearSubLevelButton() {
        listAvailableSubLevel = null;
        for (JToggleButton aSubLevelBtn : subLevelBtn) {
            aSubLevelBtn.setSelected(false);
            aSubLevelBtn.setEnabled(false);
            aSubLevelBtn.setBackground(Color.WHITE);
        }
    }

    /*
     * Clear sub sheet button
     */
    private void clearSubSheetButton() {
        for (JToggleButton aSubSheetBtn : subSheetBtn) {
            aSubSheetBtn.setSelected(false);
            aSubSheetBtn.setEnabled(false);
            aSubSheetBtn.setBackground(Color.WHITE);
        }
    }

    /*
     * Clear sheet set button
     */
    private void clearSheetSetBtn() {
        for (JToggleButton aSheetSetBtn : sheetSetBtn) {
            aSheetSetBtn.setSelected(false);
            aSheetSetBtn.setEnabled(false);
            aSheetSetBtn.setBackground(Color.WHITE);
        }
    }

    /*
     * Clear action button color
     */
    private void clearActionBtn() {
        for (JButton anActionBtn : actionBtn) {
            anActionBtn.setEnabled(false);
            anActionBtn.setBackground(Color.WHITE);
        }
        sheetNum = "00";
        subSheetChar = null;
    }

    /*
     * Clear color of all button
     */
    private void clearButton() {
        clearSubjectBtn();
        clearLevelBtn();
        clearSubLevelButton();
        clearSubSheetButton();
        clearSheetSetBtn();
    }

    /*
     * Clear all selection on screen
     */
    private void clear() {
        clearButton();
        listAvailableLevel = null;
        listAvailableSubLevel = null;
        listAvailableLevelNumber = null;
        sheetNum = "00";
        currentSheetCode = null;
        subSheetChar = null;
        setText();
        setLevelColor();
    }

    /*
     * Check condition for printing and copying video
     */
    private void checkWorksheetNumber() {
        DecodeSubjectName path = new DecodeSubjectName(currentSheetCode + "00");
        FileUtil folder = new FileUtil(path.getFullName());
        listAvailableLevelNumber = folder.getNumberListFromFolder(subSheetChar);
        if (listAvailableLevelNumber.contains(sheetNum)) {
            actionBtn.get(0).setEnabled(true);
            actionBtn.get(1).setEnabled(false);
            actionBtn.get(2).setEnabled(true);
            actionBtn.get(0).setBackground(new Color(119, 234, 173));
            actionBtn.get(1).setBackground(Color.WHITE);
            actionBtn.get(2).setBackground(new Color(119, 234, 173));
            for (JToggleButton aSheetSetBtn : sheetSetBtn) {
                aSheetSetBtn.setEnabled(true);
                aSheetSetBtn.setBackground(new Color(119, 234, 173));
            }
        } else {
            actionBtn.get(0).setEnabled(false);
            actionBtn.get(2).setEnabled(false);
            actionBtn.get(0).setBackground(Color.WHITE);
            actionBtn.get(2).setBackground(Color.WHITE);
        }
    }

    private void setEnableOfSubSheetSet(){

    }

    /**
     * Check if the sub sheet button is selected
     *
     * @return boolean status of sub sheet set button
     */
    private boolean isSubSheetBtnIsSelected() {
        for (JToggleButton aSubSheetBtn : subSheetBtn) {
            if (aSubSheetBtn.isSelected()) return true;
        }
        return false;
    }
}
