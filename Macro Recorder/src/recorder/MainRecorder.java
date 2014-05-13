package recorder;

import org.jnativehook.GlobalScreen; 
import org.jnativehook.NativeHookException;

import gui.UI;

import java.awt.Frame;
import java.lang.String;

public class MainRecorder {	
	private static long time = 0;
	public static final int convertRate = 1000000;
	public static final String root = System.getProperty("user.dir") + "\\";
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
	    GlobalScreen.getInstance().addNativeKeyListener(new KeyRecorder(root + file));
	    GlobalScreen.getInstance().addNativeMouseListener(new MouseRecorder(root + file));
	    GlobalScreen.getInstance().addNativeMouseMotionListener(new MouseRecorder(root + file));
	    GlobalScreen.getInstance().addNativeMouseWheelListener(new ScrollRecorder(root + file));
	    
	    while (GlobalScreen.isNativeHookRegistered()) {
	    	
	    }
	    
	    UI.gui.resetIcon();
	    UI.gui.setState(Frame.NORMAL);
	}

}
