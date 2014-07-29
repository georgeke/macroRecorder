package recorder;

import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class MouseRecorder implements NativeMouseInputListener {
	
	private String filepath;
	private static boolean mousePressed = false;
	
	public MouseRecorder(String filepath){
		this.filepath = filepath;
	}
	
	@Override
	public void nativeMouseClicked(NativeMouseEvent e) {
	}

	@Override
	public void nativeMousePressed(NativeMouseEvent e) {
		MouseRecorder.mousePressed = true;
        try {
            long time = System.nanoTime();
            long prevTime = MainRecorder.getTime();
            MainRecorder.setTime(time);
            time = Math.abs(time - prevTime);
            int timeMs = (int) (time / MainRecorder.convertRate);
            
            BufferedWriter out = new BufferedWriter(new FileWriter(filepath, true));
                        
            int button = e.getButton();            
            
            if (button==2) {
            	button = 3;
            } else if (button==3) {
            	button = 2;
            }
            
            out.write("MousePress " + button);
            out.newLine();
            out.write("Wait " + timeMs);
            out.newLine();
            out.close();
	    }
	    catch (IOException iox) {
            System.err.println("Error writing.");
            System.err.println(iox.getMessage());

       		try {
    			BufferedWriter out = new BufferedWriter(new FileWriter("a.txt", true));
    			
    			out.write("KeyRecorder error writing");
    			out.newLine();
    			out.close();
    		} catch (IOException idox) {
    			
    		}
            
            System.exit(1);
	    }
	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent e) {
		MouseRecorder.mousePressed = false;
        try {
            long time = System.nanoTime();
            long prevTime = MainRecorder.getTime();
            MainRecorder.setTime(time);
            time = Math.abs(time - prevTime);
            int timeMs = (int) (time / MainRecorder.convertRate);
            
            BufferedWriter out = new BufferedWriter(new FileWriter(filepath, true));
                        
            int button = e.getButton();            
            
            if (button==2) {
            	button = 3;
            } else if (button==3) {
            	button = 2;
            }
            
            out.write("MouseRelease " + button);
            out.newLine();
            out.write("Wait " + timeMs);
            out.newLine();
            out.close();
	    }
	    catch (IOException iox) {
            System.err.println("Error writing.");
            System.err.println(iox.getMessage());

       		try {
    			BufferedWriter out = new BufferedWriter(new FileWriter("a.txt", true));
    			
    			out.write("KeyRecorder error writing");
    			out.newLine();
    			out.close();
    		} catch (IOException idox) {
    			
    		}
            
            System.exit(1);
	    }
	}

	@Override
	public void nativeMouseDragged(NativeMouseEvent e) {
		// When mouse is pressed, nativeMouseMoved does not trigger. But this function does.
		if(MouseRecorder.mousePressed) {
			nativeMouseMoved(e);
		}
	}

	@Override
	public void nativeMouseMoved(NativeMouseEvent e) {
        try {
            long time = System.nanoTime();
            long prevTime = MainRecorder.getTime();
            MainRecorder.setTime(time);
            time = Math.abs(time - prevTime);
            int timeMs = (int) (time / MainRecorder.convertRate);
            
            BufferedWriter out = new BufferedWriter(new FileWriter(filepath, true));
            
            int x = e.getX();
            int y = e.getY();
            out.write("Move " + x + " " + y);
            out.newLine();
            out.write("Wait " + timeMs);
            out.newLine();
            out.close();
	    }
	    catch (IOException iox) {
            System.err.println("Error writing.");
            System.err.println(iox.getMessage());

       		try {
    			BufferedWriter out = new BufferedWriter(new FileWriter("a.txt", true));
    			
    			out.write("KeyRecorder error writing");
    			out.newLine();
    			out.close();
    		} catch (IOException idox) {
    			
    		}
            
            System.exit(1);
	    }	
	}

}
