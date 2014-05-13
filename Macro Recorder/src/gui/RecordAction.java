package gui;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import recorder.MainRecorder;

public class RecordAction implements ActionListener {
	public JTextField file;
	
	public RecordAction(JTextField file) {
		this.file = file;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {		
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	MainRecorder.file = file.getText();
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