import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class ToolBar{
	private JToolBar toolBar;
	private JButton addButton;
	private JButton deleteButton;
	private addButtonPUAWindow addButtonObj = new addButtonPUAWindow();
	private static Storage storage = new Storage();
	private static MainBar mainBar = new MainBar();
	private static PlanBar planBar = new PlanBar();
	
	public ToolBar(){
		toolBarSettings();
		
	}
	
	private void toolBarSettings(){
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		//adding buttons
		addButton = new JButton("Add");
		toolBar.add(addButton);
		deleteButton = new JButton("Delete");
		toolBar.add(deleteButton);
		//adding handlers
		toolBarButtonsListener toolBarButtonsHandler = new toolBarButtonsListener();
		
		addButton.addActionListener(toolBarButtonsHandler);
		deleteButton.addActionListener(toolBarButtonsHandler);
	}
	
	public JToolBar getToolBar(){
		return toolBar;
	}
	
	
	//actionlistener for buttons and button actions in toolbar
	private class toolBarButtonsListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			
			switch((String) event.getActionCommand()){
				case "Add": addButtonObj.addButtonAction();
							break;
				case "Delete": 	if (mainBar.getMainBar().getSelectedIndex() >= 0){
									storage.deleteElement(mainBar.getMainBar().getSelectedIndex());
									mainBar.refresh();
									planBar.clear(); // narazie
								}
							break;
			}
		}
	}

	
	
}