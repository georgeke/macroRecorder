package gui;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import recorder.MainRecorder;

public class RecordAction implements ActionListener {
	public JTextField file;
	public JFileChooser browse;
	
	public RecordAction(JTextField file, JFileChooser browse) {
		this.file = file;
		this.browse = browse;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {		
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	String fileStr = file.getText();
            	
            	// Ignoring backslashes causing IO Exception.
            	fileStr = fileStr.replace("\\", "");
            	
            	MainRecorder.file = fileStr;
            	
            	// Saving file to current browsing directory instead of just program root.
            	try {	
                	MainRecorder.root = browse.getCurrentDirectory().getCanonicalPath() + "\\";
            	} catch (IOException iox) {
            		System.err.println("Canonical path not found.");
            		System.err.println(iox.getMessage());
            		
            		System.exit(1);
            	}
            	
            	System.out.println(MainRecorder.file);
            	
            	if (MainRecorder.file != null) {
                	UI.gui.setState(Frame.ICONIFIED);
                	ImageIcon img = new ImageIcon(System.getProperty("user.dir") + "\\img\\recordIcon.png");
            		UI.gui.setIconImage(img.getImage());
            		MainRecorder.record();
            	}
            }
        });		
	}
	
}