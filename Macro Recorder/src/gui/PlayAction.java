package gui;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import player.MainPlayer;

public class PlayAction implements ActionListener {
	public JFileChooser browse;
	public JTextField loopBox;

	public PlayAction(JFileChooser browse, JTextField loopBox) {
		this.browse = browse;
		this.loopBox = loopBox;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (browse.getSelectedFile() != null) {
					try {
						MainPlayer.filepath = browse.getSelectedFile().getCanonicalPath();
					} catch (IOException iox) {
						System.err.println("Canonical path not found.");
						System.err.println(iox.getMessage());

						System.exit(1);
					}
				}

				if (MainPlayer.filepath != null) {
					UI.gui.setState(Frame.ICONIFIED);
					ImageIcon img = new ImageIcon(System.getProperty("user.dir") + "\\img\\playIcon.png");
					UI.gui.setIconImage(img.getImage());
					
					for (int i = 0; i < Integer.parseInt(loopBox.getText()); i++) {
						MainPlayer.play();
					}
				}
			}
		});
	}
}
