package player;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class KeyListener implements NativeKeyListener {
	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
	}

	@Override
	public void nativeKeyReleased(final NativeKeyEvent e) {
		String key = NativeKeyEvent.getKeyText(e.getKeyCode());

		if (key.equals("Escape")) {
			MainPlayer.exit = true;  
		}
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {		
	}
	
}