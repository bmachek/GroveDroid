package systems.machek.grovedroid;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import systems.machek.grovedroid.grovepi.GrovePiRunner;
import systems.machek.grovedroid.grovepi.tests.GrovePiTester;

public class GroveDroid {

	public static void main(String[] args) {

		CommandLineParser parser = new DefaultParser();
		Options options = new Options();

		options.addOption("t", "test", false, "Test GrovePi");
		options.addOption("h", "help", false, "Print usage");
		options.addOption("d", "debug", false, "Debug output");
		options.addOption("v", "version", false, "Version");

		try {

			CommandLine cli = parser.parse(options, args);

			/* Options */
			if (cli.hasOption("d")) {
				enableFullConsoleLogging("GrovePi");
				enableFullConsoleLogging("RaspberryPi");
				enableFullConsoleLogging("");
			} else {
				disableConsoleLogging("GrovePi");
				disableConsoleLogging("RaspberryPi");
				disableConsoleLogging("");
			}

			GrovePiRunner runner = null;
			/* Commands */
			if (cli.hasOption("t")) {
				runner = new GrovePiTester();
			} else if (cli.hasOption("v")) {
				printVersion();
			} else if (cli.hasOption("h")) {
				printUsage(options);
			} else {
				printUsage(options);
			}

			if (runner != null) {
				runner.run();
				runner.stop();
			}

		} catch (ParseException pe) {
			pe.printStackTrace();
		} catch (GroveDroidException gde) {
			gde.printStackTrace();
		}
	}

	private static void printUsage(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("groveDroid", options);
	}

	private static void printVersion() {
		Properties version = new Properties();
		try {
			version.load(ClassLoader.getSystemResourceAsStream("version.properties"));
			System.out.println("Version: " + version.getProperty("version"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void enableFullConsoleLogging(String logger) {
		Logger.getLogger(logger).setLevel(Level.FINER);
	}

	private static void disableConsoleLogging(String logger) {
		Logger.getLogger(logger).setLevel(Level.SEVERE);
	}
}
