package systems.machek.grovedroid;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import systems.machek.grovedroid.grovepi.tests.GrovePiTester;

public class GroveDroid {

	public static void main(String[] args) {

		CommandLineParser parser = new DefaultParser();
		Options options = new Options();
		
		options.addOption("t", "test", false, "Test GrovePi");
		try {
			CommandLine cli = parser.parse(options, args);
			if (cli.hasOption("t")) {
				new GrovePiTester().run();
				
			}
		} catch (org.apache.commons.cli.ParseException pe) {
			pe.printStackTrace();
		} catch (GroveDroidException gde) {
			gde.printStackTrace();
		}
	}
}
