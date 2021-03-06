package com.checkmarx.configprovider.apis;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "pretty", "html:target/cucumber" },
        glue = { "com.checkmarx.configprovider.apis" },
        features = "classpath:cucumber/configprovider/apis.feature",
        tags = "not @Skip"
    )

public class ConfigProviderAPIsTest {
}