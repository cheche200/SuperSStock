package com.supersimplestock.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class JUnit4ClassRunner extends SpringJUnit4ClassRunner {

	  static {
	    try {
	        Properties props = new Properties();
			props.load(new FileInputStream("resources/log4j.properties"));
			PropertyConfigurator.configure(props);
	    }
	    catch( FileNotFoundException ex ) {
	      System.err.println( "Cannot Initialize log4j" );
	    } catch (IOException e) {
			e.printStackTrace();
		}
	  }

	  public JUnit4ClassRunner( Class<?> clazz ) throws InitializationError {
	    super( clazz );
	  }
	}
