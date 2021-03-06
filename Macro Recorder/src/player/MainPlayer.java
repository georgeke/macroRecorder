package player;
import gui.UI;

import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.AWTException;
import java.awt.Frame;
import java.awt.Robot;

import javax.swing.ImageIcon;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class MainPlayer {	
	public static boolean exit = false;
	public static boolean pause = false;
	public static String filepath = null;
	
	public static void play() throws IllegalArgumentException {		
		try {			
			BufferedReader in = new BufferedReader(new FileReader(filepath));
			Robot player = new Robot();
			
	        GlobalScreen.registerNativeHook();
	        KeyListener exitListen = new KeyListener();
			GlobalScreen.getInstance().addNativeKeyListener(exitListen);

			ImageIcon img = new ImageIcon(System.getProperty("user.dir") + "\\img\\playIcon.png");
    		UI.gui.setIconImage(img.getImage());
			
			String input = in.readLine();
			int x;
			int y;
			int button;
			int scroll;
			int keycode;
			int time;
			String[] split; 
			
			//player.setAutoWaitForIdle(true);
			while(input != null && !MainPlayer.exit) {
				split = input.split(" ");
				System.err.println("[" + input + "]");
				if (split[0].equals("Move")) {				
					x = Integer.parseInt(split[1]);
					y = Integer.parseInt(split[2]);
					player.mouseMove(x, y);
				} else if (split[0].equals("MousePress")) {
					button = Integer.parseInt(split[1]);
					player.mousePress(InputEvent.getMaskForButton(button));
				} else if (split[0].equals("MouseRelease")) {
					button = Integer.parseInt(split[1]);
					player.mouseRelease(InputEvent.getMaskForButton(button));
				} else if (split[0].equals("Scroll")) {
					scroll = Integer.parseInt(split[1]);
					player.mouseWheel(scroll);
				} else if (split[0].equals("KeyPress")) {
					keycode = Integer.parseInt(split[1]);
					player.keyPress(keycode);
				} else if (split[0].equals("KeyRelease")) {
					keycode = Integer.parseInt(split[1]);
					player.keyRelease(keycode);
				} else if (split[0].equals("Wait")) {
					time = Integer.parseInt(split[1]);
					// delay has a max value of 60000, so we need to split it up.
					while(time > 60000) {
						time -= 60000;
						player.delay(60000);
					}
					// Im paranoid
					player.delay(Math.abs(time));
				} else if (split[0].equals("Exit")) {
					MainPlayer.exit = true;
				}
				input = in.readLine();
			}
			
			UI.gui.resetIcon();
			UI.gui.setState(Frame.NORMAL);
			
			GlobalScreen.getInstance().removeNativeKeyListener(exitListen);
			GlobalScreen.unregisterNativeHook();
			MainPlayer.exit = false;			
			in.close();
		} catch (IOException iox) {
            System.err.println("Cannot read from " + filepath + ".");
            System.err.println(iox.getMessage());

            System.exit(1);
		} catch (AWTException awtx) {
			System.err.println("The platform configuration does not allow low-level input control.");
			System.err.println(awtx.getMessage());
			
			System.exit(1);			
		} catch (NativeHookException nhx) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(nhx.getMessage());

            System.exit(1);
		}
	}

}
