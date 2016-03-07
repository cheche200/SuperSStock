package com.supersimplestock.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages="com.supersimplestock.model, com.supersimplestock.service, com.supersimplestock.data")
public class SuperSimpleContext {

}
