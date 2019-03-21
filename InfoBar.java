import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class InfoBar{
	private static JTextArea infoBar;
	private static boolean loaded = false;
	
	public InfoBar(){
		if (!loaded)
		infoBarSettings();
	}
	
	private void infoBarSettings(){
		infoBar = new JTextArea(5,1);
		infoBar.setEditable(false);
		infoBar.setLineWrap(true);
		infoBar.setWrapStyleWord(true);
		loaded = true;
	}
	
	public JTextArea getInfoBar(){
		return infoBar;
	}
	
	public void setInfoBarText(String text){
		infoBar.setText(text);
	}
	
}