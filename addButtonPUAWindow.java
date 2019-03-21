import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.time.*;

public class addButtonPUAWindow{
	private JTextField generalPlanAction;
	private JTextArea planDescriptionArea;
	private JScrollPane planDescription;
	private JLabel GPAL;
	private	JLabel PDL;
	private JLabel TL;
	private JComboBox<String> Year;
	private JComboBox<String> Month;
	private JComboBox<String> Day;
	private JComboBox<String> Hour;
	private JComboBox<String> Minute;
	private JPanel addButtonPopUp;
	private Storage storage = new Storage();
	private MainBar mainBar = new MainBar();
	
	public addButtonPUAWindow(){
		
		setLayout(); //setting layout for addbutton pupup
		
	}
	
	
	public void addButtonAction(){
		
		int result = JOptionPane.showConfirmDialog(null,addButtonPopUp,"Add",JOptionPane.OK_CANCEL_OPTION);
		//adds everything to storage, then refreshes mainBar
		if (!generalPlanAction.getText().isEmpty())
			if (result == JOptionPane.OK_OPTION){		
				storage.addElement(generalPlanAction.getText(),selectedDateToString(),planDescriptionArea.getText());
				storage.saveData();	
				mainBar.refresh();
			}
		generalPlanAction.setText(null);
		planDescriptionArea.setText(null);
		
	}
	
	private void setLayout (){
		GPAL = new JLabel("Generalized plan action:");
		PDL = new JLabel("Description of the plan:");
		TL = new JLabel("Deadline time:");
		generalPlanAction = new JTextField(20);
		planDescriptionArea = new JTextArea(4,20);
		planDescription = new JScrollPane(planDescriptionArea);
		
		planDescriptionArea.setLineWrap(true);
		planDescriptionArea.setWrapStyleWord(true);
		
		Year = new JComboBox<>(setYears());
		Month = new JComboBox<>(setMonths());
		Day = new JComboBox<>(setDays());
		Hour = new JComboBox<>(setHours());
		Minute = new JComboBox<>(setMinutes());
		Year.setBackground(Color.WHITE);
		Month.setBackground(Color.WHITE);
		Day.setBackground(Color.WHITE);
		Hour.setBackground(Color.WHITE);
		Minute.setBackground(Color.WHITE);
		
		addButtonPopUp = new JPanel();
		GroupLayout layout = new GroupLayout(addButtonPopUp);
		addButtonPopUp.setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
					.addComponent(GPAL)
					.addComponent(PDL)
					.addComponent(TL))
				.addGroup(layout.createParallelGroup()
					.addComponent(generalPlanAction)
					.addComponent(planDescription)
					.addComponent(Year))
				.addComponent(Month)
				.addComponent(Day)
				.addComponent(Hour)
				.addComponent(Minute)
		);
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
					.addComponent(GPAL)
					.addComponent(generalPlanAction))
				.addGroup(layout.createParallelGroup()	
					.addComponent(PDL)
					.addComponent(planDescription))
				.addGroup(layout.createParallelGroup()
					.addComponent(TL)
					.addComponent(Year)
					.addComponent(Month)
					.addComponent(Day)
					.addComponent(Hour)
					.addComponent(Minute))
		);
	}
	private String[] setYears(){
		String[] years = {"2017","2018","2019","2020","2021","2022"};
		return years;
	}
	private String[] setMonths(){
		String[] months = {"January","February","March","April","May",
							"June","July","August","September",
							"October","November","December"};
		return months;
	}
	private String[] setDays(){
		String[] days = new String[31] ;
		for (int i=1;i<=31;i++)
			days[i-1]=Integer.toString(i);
		return days;
	}
	private String[] setHours(){
		String[] hours = new String[24];
		for(int i=0;i<24;i++)
			if (i>9)
				hours[i]=Integer.toString(i);
			else
				hours[i] = new StringBuilder("0"+Integer.toString(i)).toString();
		return hours;
	}
	private String[] setMinutes(){
		String[] minutes = new String[60];
		for (int i = 0; i<60; i++)
			if (i>9)
				minutes[i]=Integer.toString(i);
			else
				minutes[i] = new StringBuilder("0"+Integer.toString(i)).toString();
		return minutes;
	}
	
	private String selectedDateToString (){
		String endData;
		StringBuilder sb = new StringBuilder();
		sb.append("Action: "+generalPlanAction.getText()+
							"//Complete by://"+Month.getSelectedItem()+" "+Day.getSelectedItem()+", "+Year.getSelectedItem()+ //MMMMM dd, yyyy on parts[2]
							"//"+Hour.getSelectedItem()+":"+Minute.getSelectedItem()); // HH:mm on parts[3] in planBar class
		endData = sb.toString();
		return endData;
	}
	
}