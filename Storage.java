import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.text.*;

public class Storage {
	private static ArrayList<String> mainList = new ArrayList<String>();
	private static ArrayList<String> planList = new ArrayList<String>();
	private static ArrayList<String> infoList = new ArrayList<String>();
	private static boolean loaded = false;
	private Scanner data;
	private PrintWriter write;
	private String dataDir = "Data\\data.txt";
	
	public Storage () {
		if (!loaded)
			loadData();
	}
	//loads data from .txt to ArrayList
	private void loadData(){
		try{
			data = new Scanner(new File(dataDir));
			
			while(data.hasNextLine()){
				mainList.add(data.nextLine());
				planList.add(data.nextLine());
				infoList.add(data.nextLine());
			}
			
			data.close();
		}catch(Exception e){
			System.out.println("No file "+ dataDir);
		}
		
		loaded = true;
	}
	//saves data from ArrayList to .txt by rewriting everything
	public void saveData(){
		try{
			write = new PrintWriter(dataDir,"UTF-8");
			
			for (int i = 0; i<mainList.size();i++){
				write.println(mainList.get(i));
				write.println(planList.get(i));
				write.println(infoList.get(i));
			}
			
			write.close();
		}catch(Exception e){}
	}
	//adds element to ArrayList, (then can be saved to .txt with saveData(), mb make it so it save instantly)
	public void addElement(String mainString, String planString, String infoString){
		mainList.add(mainString);
		planList.add(planString);
		infoList.add(infoString);
	}
	// deletes element from ArralyList and saves immediately
	public void deleteElement(int index){
		mainList.remove(index);
		planList.remove(index);
		infoList.remove(index);
		saveData();
	}
	//returns an element from ArrayList as a String
	public String getMainElement(int it){
		return mainList.get(it);
	}
	public String getPlanElement(int it){ //splits into lines and sends out.
			String splitPlanElement;
			String temp4planElement = planList.get(it);
			String[] parts = temp4planElement.split("//");
			splitPlanElement = (parts[0]+"\n"+parts[1]+"\n"+parts[2]+"\n"+parts[3]);//there is a constant format creator in addButtonPUAWindow class in selectedDateToString() method. mb try checking if parts[4] exists etc later.
		return splitPlanElement;
	}
	public String getInfoElement(int it){
		return infoList.get(it);
	}
	
	public Date getPlanEndDate(int it){
		String endDateString;
		Date endDate = new Date();
		SimpleDateFormat endDateFormatter = new SimpleDateFormat("MMMMM dd, yyyy HH:mm");
		
		String splitPlanElement;
		String temp4planElement = planList.get(it);
		String[] parts = temp4planElement.split("//");
		splitPlanElement = (parts[0]+"\n"+parts[1]+"\n"+parts[2]+"\n"+parts[3]); // i don't like this, later gonna think how to make it more comprehensible for others, make it into methods or smthn
		endDateString = (parts[2]+" "+parts[3]); 
		
		try{
			endDate = endDateFormatter.parse(endDateString);
		}catch(Exception e){
			System.out.println("Impossible: cuz selectedDateToString method in addButtonPUAWindow class is constant. Or you changed it.");
		}
		
		return endDate;
	}
	
	//returns ArrayList of main window, so you can use it in enhanced for loop to cycle through String elements
	public ArrayList<String> getMainArray(){
		return mainList;
	}
	
	
	
	
}