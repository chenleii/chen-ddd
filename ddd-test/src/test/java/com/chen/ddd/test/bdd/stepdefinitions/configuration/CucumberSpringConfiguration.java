package com.chen.ddd.test.bdd.stepdefinitions.configuration;

import com.chen.ddd.test.TestApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chen
 * @since 2020/11/15 2:14.
 */
@CucumberContextConfiguration
@SpringBootTest(classes = TestApplication.class)
@Transactional
@AutoConfigureMockMvc
public class CucumberSpringConfiguration {


}
