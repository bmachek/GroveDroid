package systems.machek.grovedroid.grovepi.tests;

import java.io.IOException;

import org.iot.raspberry.grovepi.GroveDigitalOut;

import systems.machek.grovedroid.GroveDroidException;
import systems.machek.grovedroid.grovepi.GrovePiRunner;

public class GrovePiTester extends GrovePiRunner {

	public GrovePiTester() throws GroveDroidException {
		super();
	}
	
	
	public void run() throws GroveDroidException {
		
		try {
			GroveDigitalOut d8 = new GroveDigitalOut(grovePi, 8);
			
			while (true) {
				d8.set(true);
				Thread.sleep(1000);
				d8.set(false);
				Thread.sleep(1000);
			}
			
		} catch (IOException | InterruptedException ioe) {
			ioe.printStackTrace();
			throw new GroveDroidException(ioe);
		}
		
	}
}
