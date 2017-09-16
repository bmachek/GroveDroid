package systems.machek.grovedroid;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import systems.machek.grovedroid.grovepi.tests.GrovePiTester;

public class GroveDroid {

	public static void main(String[] args) {

		CommandLineParser parser = new DefaultParser();
		Options options = new Options();
		
		options.addOption("t", "test", false, "Test GrovePi");
		options.addOption("h", "help", false, "Print usage");
		options.addOption("d", "debug", false, "Debug output");
		
		try {
			
			CommandLine cli = parser.parse(options, args);
			
			
			if (cli.hasOption("d")) {
				enableFullConsoleLogging("GrovePi");
				enableFullConsoleLogging("RaspberryPi");
				enableFullConsoleLogging("");
			} else {
				disableConsoleLogging("GrovePi");
				disableConsoleLogging("RaspberryPi");
				disableConsoleLogging("");
			}
			
			if (cli.hasOption("t")) {
				new GrovePiTester().run();
			} else if (cli.hasOption("h")) {
				printUsage(options);
			} else {
				printUsage(options);
			}

			
		} catch (ParseException pe) {
			pe.printStackTrace();
		} catch (GroveDroidException gde) {
			gde.printStackTrace();
		}
	}

	private static void printUsage(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp( "groveDroid", options );
	}

	private static void enableFullConsoleLogging(String logger) {
		Logger.getLogger(logger).setLevel(Level.ALL);
	}

	private static void disableConsoleLogging(String logger) {
		Logger.getLogger(logger).setLevel(Level.SEVERE);
	}
}
