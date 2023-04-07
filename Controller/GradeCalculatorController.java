package Controller;

import View.CalculatorUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Grades;

public class GradeCalculatorController{
	private CalculatorUI Calculator_View;
	private Grades Grades_Model;
	
	public GradeCalculatorController(CalculatorUI Calculator_View){
		this.Calculator_View = Calculator_View;
		this.Grades_Model = new Grades();
		
		this.Calculator_View.addGradeListener(new addListener());
		this.Calculator_View.DeleteGradeListener(new DeleteListener());
		this.Calculator_View.CalculateGradeListener(new CalculateListener());
		
	}
	
	class addListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			CalculatorUI.AddGrade();
		}
	}
	
	class DeleteListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			CalculatorUI.DeleteGrade();
		}
		
	}
	
	class CalculateListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Grades_Model.Calculate_Total(CalculatorUI.getAssignmentName(), CalculatorUI.getGrade(), CalculatorUI.getWeight());
			Float Total = Grades_Model.getTotal();
			CalculatorUI.setGrade(Total);
		}
	}
	
}
