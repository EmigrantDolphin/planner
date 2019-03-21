import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class MainBar{
	private static JList<String> mainBar;
	private static DefaultListModel<String> mainListModel;
	private static boolean loaded = false;
	
	private Storage storage = new Storage();
	private InfoBar infoBar = new InfoBar();
	private PlanBar planBar = new PlanBar();
	
	public MainBar(){
		if (!loaded)
		mainBarSettings();
	}
	
	private void mainBarSettings(){
		mainListModel = new DefaultListModel<String>();
		mainBar = new JList<String>(mainListModel);
		mainBar.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		mainBar.setLayoutOrientation(JList.VERTICAL);
		mainListSelectionListener MLSLHandler = new mainListSelectionListener();
		mainBar.addListSelectionListener(MLSLHandler);
		refresh();
		loaded = true;
	}
	
	public JList<String> getMainBar(){
		return mainBar;
	}
	
	public void refresh(){
		mainListModel.clear();
		for (String mainElement : storage.getMainArray()){
			mainListModel.addElement(mainElement);
		}
	}
	// things to do on selection
	private class mainListSelectionListener implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent e){
			if (mainBar.getSelectedIndex() >= 0){
				infoBar.setInfoBarText(storage.getInfoElement(mainBar.getSelectedIndex())); //change to index parameter and do searching and putting text in info class
				planBar.setPlanBarText(mainBar.getSelectedIndex());
			}
			else
				infoBar.setInfoBarText(null);
		}
	}
	
}