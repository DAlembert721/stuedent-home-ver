package com.acme.studenthome;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:features"},
        glue = {"com.acme.studenthome"},
        plugin = {"pretty", "json:target/cucumber.json"}
)
public class RunCucumberTest {
}
