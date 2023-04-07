package Model;

import java.util.ArrayList;

public class Grades {
	private float Grade;
	private float Weight;
	private String Assignment_Name;
	private float Total;
	
	public void Calculate_Total(ArrayList<String>  Assignment_Name, ArrayList<Float>  Grade, ArrayList<Float>  Weight)
	{
		Total = 0;
		Float total_Weight =(float) 0;
		
		for (int i = 0; i < Assignment_Name.size(); i++) {
			Total = Total +(Grade.get(i)*Weight.get(i));
			total_Weight = total_Weight + Weight.get(i);
		}
		Total = Total/total_Weight;
	}
	
	public float getTotal() {
		return Total;
	}

}