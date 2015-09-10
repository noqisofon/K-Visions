package org.kvisions.ui;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;

import org.kvisions.launcher.Launcher;
import org.kvisions.midi.KorgNanoControl2;
import org.kvisions.midi.MidiControlChangedListener;
import org.kvisions.midi.MidiHandler;

public class Display extends JPanel{
	protected Display(){
		setModeChangeHandlers();
		addComponentListener(new DisplayShownAdapter());
	}
	
	protected class DisplayShownAdapter extends ComponentAdapter{
		@Override
		public void componentShown(ComponentEvent e){
			shown();
		}
		
		@Override
		public void componentHidden(ComponentEvent e){
			hidden();
		}
	}
	
	protected void shown(){
		MidiHandler.clearMidiControlChangedListeners();	
		setModeChangeHandlers();
		requestFocus();
	}
	
	protected void hidden(){
	}

	protected void setModeChangeHandlers(){
		MidiHandler.setMidiControlChangedListener(KorgNanoControl2.BUTTON_FORWARD, new ChangeToSchottkyListener());
		MidiHandler.setMidiControlChangedListener(KorgNanoControl2.BUTTON_STOP, new ChangeToOPTListener());
		MidiHandler.setMidiControlChangedListener(KorgNanoControl2.BUTTON_BACKWARD, new ChangeToParabolicListener());
	}

	private class ChangeToSchottkyListener implements MidiControlChangedListener{
		@Override
		public void changed(int controlPort, float value) {
			if(value == 127)
				Launcher.changeDisplayMode(DisplayMode.SCHOTTKY);
		}
	}

	private class ChangeToOPTListener implements MidiControlChangedListener{
		@Override
		public void changed(int controlPort, float value) {
			if(value == 127)
				Launcher.changeDisplayMode(DisplayMode.OPT);
		}
	}

	private class ChangeToParabolicListener implements MidiControlChangedListener{
		@Override
		public void changed(int controlPort, float value) {
			if(value == 127)
				Launcher.changeDisplayMode(DisplayMode.PARABOLIC);
		}
	}
}
