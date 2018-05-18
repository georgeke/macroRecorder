package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class UI {
	public static Window gui = null;
	
	public static void run() {
	    SwingUtilities.invokeLater(new Runnable() {
	    	public void run() {
	    		gui = new Window();
	            gui.setVisible(true);

	    		JPanel actions = new JPanel();
	    		GridBagLayout gridbag = new GridBagLayout();
	    		GridBagConstraints c = new GridBagConstraints();
	    		
	    		JButton record = new JButton("Record");
	    		JButton play = new JButton("Play");
	    		JTextField filename = new JTextField("out.txt", 60);
	    		JFileChooser browse = new JFileChooser(System.getProperty("user.dir"));
	    		
	    		JTextField numberBox = new JTextField("1", 60);
	    		filename.setFont(new Font("Courier New", Font.PLAIN, 12));
	    		
	    		browse.setDragEnabled(true);
	    		browse.setDialogTitle("Choose input file");
	    		browse.setControlButtonsAreShown(false);
	    		play.addActionListener(new PlayAction(browse, numberBox));
	    		record.addActionListener(new RecordAction(filename, browse));
	    		
	    	    actions.setLayout(gridbag);

	    	    c.weightx = 1;
	    	    c.fill = GridBagConstraints.HORIZONTAL;
	    	    c.gridx = 1;  
	    	    c.gridy = 0;
	    	    actions.add(record, c);
	    	    c.gridy = 1;
	    	    actions.add(filename, c);
	    	    c.gridy = 2;
	    	    actions.add(browse, c);
	    	    c.gridy = 3;
	    	    actions.add(numberBox, c);
	    	    c.gridy = 4;
	    	    actions.add(play, c);
	    	    gui.setSize(600, 500);
	    	    gui.add(actions);
	        }
	    });	
	}
}
