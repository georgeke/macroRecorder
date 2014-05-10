package gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.WindowConstants;

import javax.swing.JFrame;
import org.jnativehook.GlobalScreen;

public class Window extends JFrame implements WindowListener {

	private static final long serialVersionUID = 1L;

	public Window() {
		setTitle("Macro Recorder");
		setSize(385, 425);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addWindowListener(this);
	}
	
	@Override
	public void windowActivated(WindowEvent arg0) {
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		if (GlobalScreen.isNativeHookRegistered()) {
			GlobalScreen.unregisterNativeHook();
		}
        System.runFinalization();
        System.exit(0);
	}

	@Override
	public void windowClosing(WindowEvent arg0) {	
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
	}

	@Override
	public void windowOpened(WindowEvent arg0) {    
	}
}
