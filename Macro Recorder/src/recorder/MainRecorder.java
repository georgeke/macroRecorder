package recorder;

import org.jnativehook.GlobalScreen; 
import org.jnativehook.NativeHookException;

import gui.UI;

import java.awt.Frame;
import java.lang.String;

public class MainRecorder {	
	private static long time = 0;
	public static final int convertRate = 1000000;
	public static String root = System.getProperty("user.dir") + "\\";
	public static String file = null;
	
	public static void setTime(long time) {
		MainRecorder.time = time;
	}
	
	public static long getTime() {
		return MainRecorder.time;
	}

	public static void record() {	
		try {
            GlobalScreen.registerNativeHook();
	    }
	    catch (NativeHookException nhx) {
	            System.err.println("There was a problem registering the native hook.");
	            System.err.println(nhx.getMessage());
	
	            System.exit(1);
	    }
		System.out.println(root);
		
		time = System.nanoTime();
		KeyRecorder keyListener = new KeyRecorder(root + file);
		MouseRecorder mouseListener = new MouseRecorder(root + file);
		MouseRecorder mouseMotionListener = new MouseRecorder(root + file);
		ScrollRecorder mouseWheelListener = new ScrollRecorder(root + file);
		
	    GlobalScreen.getInstance().addNativeKeyListener(keyListener);
	    GlobalScreen.getInstance().addNativeMouseListener(mouseListener);
	    GlobalScreen.getInstance().addNativeMouseMotionListener(mouseMotionListener);
	    GlobalScreen.getInstance().addNativeMouseWheelListener(mouseWheelListener);
	    
	    while (GlobalScreen.isNativeHookRegistered()) {
	    	
	    }
	    
	    // Need to remove the listeners that were added...
	    GlobalScreen.getInstance().removeNativeKeyListener(keyListener);
	    GlobalScreen.getInstance().removeNativeMouseListener(mouseListener);
	    GlobalScreen.getInstance().removeNativeMouseMotionListener(mouseMotionListener);
	    GlobalScreen.getInstance().removeNativeMouseWheelListener(mouseWheelListener);
	    
	    UI.gui.resetIcon();
	    UI.gui.setState(Frame.NORMAL);
	}

}