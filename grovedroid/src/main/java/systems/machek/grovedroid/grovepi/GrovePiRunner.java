package systems.machek.grovedroid.grovepi;

import java.io.IOException;

import org.iot.raspberry.grovepi.GrovePi;
import org.iot.raspberry.grovepi.pi4j.GrovePi4J;

import systems.machek.grovedroid.GroveDroidException;

public abstract class GrovePiRunner {
	
	protected GrovePi grovePi;
	
	public GrovePiRunner() throws GroveDroidException {
		init();

	}

	protected void init() throws GroveDroidException {
		
		try {
			grovePi = new GrovePi4J();
		} catch (IOException ioe) {
			throw new GroveDroidException(ioe);
		}
		
	}
	
	public abstract void run() throws GroveDroidException;
	
}
