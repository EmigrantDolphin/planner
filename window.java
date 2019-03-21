import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.time.*;

public class window extends JFrame {
	
	private MainBar mainBar = new MainBar();
	private PlanBar planBar = new PlanBar();
	private InfoBar infoBar = new InfoBar();
	private ToolBar toolBar = new ToolBar();
	
	
	public window() {
		super("Quest Log");
		setMainBar();
		setPlanBar();
		setInfoBar();
		setToolBar();
		
		windowSettings();
		
		startLoop();
	}
	
	private void windowSettings(){
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setSize(900,600);
		super.setResizable(false);		
		super.setVisible(true);
		
	}

	private void setMainBar(){
		add(new JScrollPane(mainBar.getMainBar()),BorderLayout.CENTER);		
	}	
	private void setPlanBar(){
		add(new JScrollPane(planBar.getPlanBar()),BorderLayout.EAST);
	}
	private void setInfoBar(){
		add(new JScrollPane(infoBar.getInfoBar()),BorderLayout.SOUTH);
	}
	private void setToolBar(){
		add(toolBar.getToolBar(),BorderLayout.NORTH);
	}
	
	private void startLoop(){
		
		while (true){
			try{
				Thread.sleep(1000);
			}catch(Exception e){}
			
			planBar.refreshPlanBar();
		}
		
	}
	
	
	
	
	
}