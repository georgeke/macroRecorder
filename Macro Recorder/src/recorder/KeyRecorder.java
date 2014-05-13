package recorder;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.awt.event.KeyEvent;


public class KeyRecorder implements NativeKeyListener {
	private String filepath;
	private boolean skip = false;
	private boolean exit = false;
	
	public KeyRecorder(String filepath){
		this.filepath = filepath;
	}
	
	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
        try {
            long time = System.nanoTime();
            long prevTime = MainRecorder.getTime();
            MainRecorder.setTime(time);
            time = Math.abs(time - prevTime);
            int timeMs = (int) (time / MainRecorder.convertRate);
            
            BufferedWriter out = new BufferedWriter(new FileWriter(filepath, true));
            
            int keycode = e.getKeyCode();
            String key = NativeKeyEvent.getKeyText(keycode);
            boolean leave = false;
            
            if (key.equals("Shift")) {
            	keycode = 16;
            } else if (key.equals("Alt")) {
            	keycode = 18;
            } else if (key.equals("Escape")) {
            	skip = true;
            }
            
            if (!skip) {
	            for (int i = 0 ; i < 16*16*16*16 && !leave ; i++) {
	            	if (KeyEvent.getKeyText(i).equals(key)) {
	            		keycode = i;
	            		leave = true;
	            	}
	            }
	            
	            out.write("KeyPress " + keycode + " (" + key + ")");
	            out.newLine();
	            out.write("Wait " + timeMs);
	            out.newLine();
            }
	            
            out.close();        
	    }
	    catch (IOException iox) {
            System.err.println("Error writing.");
            System.err.println(iox.getMessage());

            System.exit(1);
	    }
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
        try {
            long time = System.nanoTime();
            long prevTime = MainRecorder.getTime();
            MainRecorder.setTime(time);
            time = Math.abs(time - prevTime);
            int timeMs = (int) (time / MainRecorder.convertRate);
            
            BufferedWriter out = new BufferedWriter(new FileWriter(filepath, true));
            
            int keycode = e.getKeyCode();
            String key = NativeKeyEvent.getKeyText(keycode);
            boolean leave = false;
            
            if (key.equals("Shift")) {
            	keycode = 16;
            } else if (key.equals("Alt")) {
            	keycode = 18;
            } else if (key.equals("Escape")) {
            	out.write("Exit");
            	out.newLine();
            	exit = true;
            }
            
            if (!exit) {
	            for (int i = 0 ; i < 16*16*16*16 && !leave ; i++) {
	            	if (KeyEvent.getKeyText(i).equals(key)) {
	            		keycode = i;
	            		leave = true;
	            	}
	            }
	            
	            out.write("KeyRelease " + keycode + " (" + key + ")");
	            out.newLine();
	            out.write("Wait " + timeMs);
	            out.newLine();
        	} else {
        		GlobalScreen.unregisterNativeHook();
        	}
            
            out.close();
	    }
	    catch (IOException iox) {
            System.err.println("Error writing.");
            System.err.println(iox.getMessage());

            System.exit(1);
	    }
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
	}

}
