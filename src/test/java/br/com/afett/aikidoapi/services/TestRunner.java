package br.com.afett.aikidoapi.services;

import org.junit.platform.suite.api.ConfigurationParameter; 
import org.junit.platform.suite.api.SelectClasspathResource; 
import org.junit.platform.suite.api.Suite;

import io.cucumber.core.options.Constants;

@Suite
@SelectClasspathResource("promotion_application.feature") 
@ConfigurationParameter(
		key = Constants.PLUGIN_PUBLISH_QUIET_PROPERTY_NAME, value = "true") 
//@ConfigurationParameter(
//key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "@book or @author") 
//@ConfigurationParameter(
//		key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "@R1")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME
					, value = "pretty"
							+ ", html:target/report/cucumber.html"
							+ ", json:target/report/cucumber.json")
public class TestRunner {

}
