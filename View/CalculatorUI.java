package View;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;

import java.util.ArrayList;

public class CalculatorUI{

	public static JPanel frame;
	public static JTextField Assignment_textField;
	public static JTextField Grade_textField;
	public static JTextField Weight_textField;
	public static JTable Table_Grade;
	public static JButton Add_btn;
	public static JButton Reset_btn;
	public static JButton Delete_btn;
	public static JButton Calculate_btn;
	public static JLabel AverageGradeValue_Label;
	
	/**
	 * Create the application.
	 */
	public CalculatorUI() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		frame = new JPanel();
		frame.setBounds(100, 100, 754, 560);
		frame.setLayout(null);
		frame.setBackground(new Color(255, 233, 225));
		
		JLabel GradeCalculatorLabel = new JLabel("Grade Calculator");
		GradeCalculatorLabel.setFont(new Font("Arial", Font.BOLD, 20));
		GradeCalculatorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GradeCalculatorLabel.setBounds(10, 11, 718, 30);
		frame.add(GradeCalculatorLabel);
		
		JPanel Information_panel = new JPanel();
		Information_panel.setBorder(new TitledBorder(null, "Add Grade", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Information_panel.setBounds(10, 52, 300, 458);
		frame.add(Information_panel);
		Information_panel.setLayout(null);
		
		
		JLabel Assignment_Label = new JLabel("Assignment");
		Assignment_Label.setBounds(10, 49, 82, 18);
		Assignment_Label.setHorizontalAlignment(SwingConstants.LEFT);
		Assignment_Label.setFont(new Font("Arial", Font.BOLD, 15));
		Information_panel.add(Assignment_Label);
		Information_panel.setBackground(new Color(255, 233, 225));
		
		Assignment_textField = new JTextField();
		Assignment_textField.setFont(new Font("Arial", Font.PLAIN, 11));
		Assignment_textField.setBounds(10, 78, 150, 20);
		Assignment_textField.setColumns(10);
		Information_panel.add(Assignment_textField);
		
		JLabel Grade_Label = new JLabel("Grade(%)");
		Grade_Label.setBounds(10, 109, 67, 18);
		Grade_Label.setHorizontalAlignment(SwingConstants.LEFT);
		Grade_Label.setFont(new Font("Arial", Font.BOLD, 15));
		Information_panel.add(Grade_Label);
		
		Grade_textField = new JTextField();
		Grade_textField.setToolTipText("");
		Grade_textField.setFont(new Font("Arial", Font.PLAIN, 11));
		Grade_textField.setBounds(10, 138, 150, 20);
		Grade_textField.setColumns(10);
		Information_panel.add(Grade_textField);
		
		JLabel Weight_Label = new JLabel("Weight");
		Weight_Label.setBounds(10, 169, 49, 18);
		Weight_Label.setHorizontalAlignment(SwingConstants.LEFT);
		Weight_Label.setFont(new Font("Arial", Font.BOLD, 15));
		Information_panel.add(Weight_Label);
		
		Weight_textField = new JTextField();
		Weight_textField.setFont(new Font("Arial", Font.PLAIN, 11));
		Weight_textField.setBounds(10, 198, 150, 20);
		Weight_textField.setColumns(10);
		Information_panel.add(Weight_textField);
		
		Add_btn = new JButton("Add");
		Add_btn.setBounds(10, 397, 130, 40);
		Information_panel.add(Add_btn);
		
		Reset_btn = new JButton("Reset");
		Reset_btn.setBounds(160, 397, 130, 40);
		Information_panel.add(Reset_btn);
		
		Delete_btn = new JButton("Delete");
		Delete_btn.setBounds(587, 274, 130, 23);
		frame.add(Delete_btn);
		
		Calculate_btn = new JButton("Calculate");
		Calculate_btn.setBounds(320, 274, 130, 23);
		frame.add(Calculate_btn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(320, 62, 397, 201);
		frame.add(scrollPane);
		
		Table_Grade = new JTable();
		scrollPane.setViewportView(Table_Grade);
		Table_Grade.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Assignment", "Grade(%)", "Weight"
			}
		));
		Table_Grade.setBackground(Color.orange);
		
		JLabel AverageGrade_Label = new JLabel("Average Grade:");
		AverageGrade_Label.setFont(new Font("Arial", Font.BOLD, 15));
		AverageGrade_Label.setBounds(320, 318, 130, 18);
		frame.add(AverageGrade_Label);
		
		AverageGradeValue_Label = new JLabel("45%");
		AverageGradeValue_Label.setHorizontalAlignment(SwingConstants.CENTER);
		AverageGradeValue_Label.setFont(new Font("Arial", Font.BOLD, 48));
		AverageGradeValue_Label.setBounds(345, 364, 372, 86);
		frame.add(AverageGradeValue_Label);
		AverageGradeValue_Label.setVisible(false);
	}


	public static void AddGrade() {
		String Assignment = CalculatorUI.Assignment_textField.getText();
		String Grade = CalculatorUI.Grade_textField.getText();
		String Weight = CalculatorUI.Weight_textField.getText();
		String LetterOrSymbol = ".*[a-zA-Z\\W&&[^.]].*";
		
		if(Assignment.isEmpty() || Weight.isEmpty()) {
			JOptionPane.showMessageDialog(null,"Please Enter Assignment Name and Weight","Try Again", JOptionPane.ERROR_MESSAGE);
		}
		else if(Grade.matches(LetterOrSymbol) || Weight.matches(LetterOrSymbol) || Weight.equals(".") || Weight.equals("0")){
			JOptionPane.showMessageDialog(null,"Please Enter Correct Grade and Weight","Try Again", JOptionPane.ERROR_MESSAGE);
		}
		else {
			if(Grade.isEmpty() || Grade.equals(".")){
				Grade ="0";
			}
			DefaultTableModel model = (DefaultTableModel) CalculatorUI.Table_Grade.getModel();
			model.addRow(new Object[]{Assignment, Grade, Weight});
			CalculatorUI.Assignment_textField.setText("");
			CalculatorUI.Grade_textField.setText("");
			CalculatorUI.Weight_textField.setText("");
		}
	}
	
	public static void DeleteGrade() {
		int row = Table_Grade.getSelectedRow();
		
		if (row < 0){
			JOptionPane.showMessageDialog(null,"No row is selected! Please select one row","Select Row", JOptionPane.ERROR_MESSAGE);
		} else {
			DefaultTableModel model = (DefaultTableModel) Table_Grade.getModel();
			model.removeRow(row);
		}
	}
	
	public void addGradeListener(ActionListener listenForAdd_btn) {
		Add_btn.addActionListener(listenForAdd_btn);
	}
	
	public void ResetGradeListener(ActionListener listenForReset_btn) {
		Reset_btn.addActionListener(listenForReset_btn);
	}
	
	public void DeleteGradeListener(ActionListener listenForDelete_btn) {
		Delete_btn.addActionListener(listenForDelete_btn);
	}
	
	public void CalculateGradeListener(ActionListener listenForCalculate_btn) {
		Calculate_btn.addActionListener(listenForCalculate_btn);
	}
	
	public static ArrayList<String> getAssignmentName(){
		int numRows = Table_Grade.getRowCount();
		ArrayList<String> assignmentName= new ArrayList<String>();

		for (int i = 0; i < numRows; i++) {
		        Object value = Table_Grade.getValueAt(i, 0);
		        assignmentName.add((String)value);
		    }
		
		System.out.println(assignmentName + "\t");
		return assignmentName;
	}
	
	public static ArrayList<Float> getGrade(){
		int numRows = Table_Grade.getRowCount();
		ArrayList<Float> Grades = new ArrayList<Float>();

		for (int i = 0; i < numRows; i++) {
		        Object value = Table_Grade.getValueAt(i, 1);
		        Grades.add(Float.parseFloat((String)value));
		    }
		System.out.println(Grades + "\t");
		return Grades;
	}
	
	public static ArrayList<Float> getWeight(){
		int numRows = Table_Grade.getRowCount();
		ArrayList<Float> Weight= new ArrayList<Float>();

		for (int i = 0; i < numRows; i++) {
		        Object value = Table_Grade.getValueAt(i, 2);
		        Weight.add(Float.parseFloat((String)value));
		    }
		System.out.println(Weight + "\t");
		return Weight;
	}
	
	public static void setGrade(Float total){
		AverageGradeValue_Label.setText(total+"%");
		AverageGradeValue_Label.setVisible(true);
	}
}