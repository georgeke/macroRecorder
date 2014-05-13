package player;

import java.awt.Frame;

import gui.UI;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class KeyListener implements NativeKeyListener {
	
	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {	
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		String key = NativeKeyEvent.getKeyText(e.getKeyCode());
		if (key.equals("Escape")) {
			UI.gui.setState(Frame.NORMAL);
			MainPlayer.exit = true;
		} else if (key.equals("Space")) {
			System.out.println("PASEDPASEPASDPASPEASPEPSAEPSASAPEPAEPEPADPADPEPAPDPDE ");
			synchronized (MainPlayer.class) {
			    if (MainPlayer.pause) {
			    	synchronized (MainPlayer.class) {
			    		MainPlayer.class.notify(); 
			    	}
			    	MainPlayer.pause = !MainPlayer.pause;
			    } else {
					try {
						System.out.println("PASEDPASEPASDPASPEASPEPSAEPSASAPEPAEPEPADPADPEPAPDPDE ");
						MainPlayer.class.wait();
					} catch (InterruptedException ix) {
						System.err.print("Thread interrupted while paused.");
						System.err.print(ix.getMessage());
						
						System.exit(1);
					}
					MainPlayer.pause = !MainPlayer.pause;
			    }
			}
		}	
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {		
	}
	
}