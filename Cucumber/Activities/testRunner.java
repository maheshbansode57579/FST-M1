package testRunner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import io.cucumber.junit.platform.engine.Constants;


	@Suite // To make the class a Test Suite
	@IncludeEngines("cucumber") // Convert the suite from JUnit to Cucumber
	@SelectClasspathResource("features") // Give the folder name where the .feature file are located
	@ConfigurationParameter( // Property mentions where the Step Definitions are located
	    key = Constants.GLUE_PROPERTY_NAME,
	    value = "stepDefinitions")
	@ConfigurationParameter( // Property to control/choose the scenario/feature to run
	    key = Constants.FILTER_TAGS_PROPERTY_NAME,
	    value = "@activity6")
	@ConfigurationParameter(
	    key = Constants.PLUGIN_PROPERTY_NAME,
	    value = "pretty, html:Reports/HTML_Report.html, junit:Reports/XML_Report.xml, json:Reports/JSON_Report.json"
	)
	@ConfigurationParameter(
	    key = Constants.PLUGIN_PUBLISH_ENABLED_PROPERTY_NAME,
	    value = "true"
	)
	
public class testRunner {
}