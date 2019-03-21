import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.time.*;
import java.text.*;
import java.util.concurrent.TimeUnit;

public class PlanBar{
	private static JPanel planBar;
	private JTextField planBarText;
	private static JTextArea planTab1;
	private static JTextArea planTab2;
	private static JTextArea planTab3;
	private static String planTab1Text;
	
	private static boolean loaded = false;
	private int planBarWidth = 15;
	private int planTabHeight = 5;
	
	//private static long timeLeftLong; 
	
	private Storage storage = new Storage();
	
	public PlanBar(){
		if (!loaded)
			planBarSettings();
		
	}
	
	private void planBarSettings(){
		planBar = new JPanel();
		planBar.setLayout(new BoxLayout(planBar,BoxLayout.Y_AXIS));
		
		planBarText = new JTextField("Plan Tab",planBarWidth);
		planBarText.setHorizontalAlignment(JTextField.CENTER);
		planBarText.setEditable(false);
		planBar.add(planBarText);
		
		planTab1 = new JTextArea(planTabHeight,planBarWidth);
		planTab2 = new JTextArea(planTabHeight,planBarWidth);
		planTab3 = new JTextArea(planTabHeight,planBarWidth);
		planTab1.setEditable(false);
		planTab2.setEditable(false);
		planTab3.setEditable(false);
		planTab1.setLineWrap(true);
		planTab2.setLineWrap(true);
		planTab3.setLineWrap(true);
		planBar.add(planTab1);
		planBar.add(planTab2);
		planBar.add(planTab3);
		
		loaded = true;
	}
	
	public void setPlanBarText(int index){ //later add : if hasSubMain, set text of all of em. now only one
		getPlanBarText(index);
	}
	
	private static Date endDate;
	private static String textString;
	private static boolean allowed= false;
	
	public void getPlanBarText(int index){ //this is used in this class and in TimeLoop class
		textString = storage.getPlanElement(index);
		endDate = storage.getPlanEndDate(index);
		allowed = true;
	}
	
	public void refreshPlanBar(){
		if (allowed){
			Date currentDate = new Date();
			long timeLeftLong;
			long minutesLeft;
			long hoursLeft;
			long daysLeft;
			String timeLeftString;
			
			timeLeftLong = endDate.getTime() - currentDate.getTime();
			minutesLeft = TimeUnit.MILLISECONDS.toMinutes(timeLeftLong) % 60;
			hoursLeft = TimeUnit.MILLISECONDS.toHours(timeLeftLong) % 24;
			daysLeft = TimeUnit.MILLISECONDS.toHours(timeLeftLong) / 24;
			
			timeLeftString = ("Countdown: "+daysLeft+", "+hoursLeft+":"+minutesLeft);
			
			planTab1.setText(textString+"\n"+timeLeftString);
		}
	}
	
	public void clear(){
		planTab1.setText("");
		allowed = false;
	}
	
	public JPanel getPlanBar(){
		return planBar;
	}
	
	
	
}