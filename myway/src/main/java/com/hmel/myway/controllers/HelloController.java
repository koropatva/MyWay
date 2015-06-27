package com.hmel.myway.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Burkovskiy Alexander
 */

@RestController
public class HelloController {
  
  @RequestMapping
  public String index(){
    return "Greetings from Spring Boot!";
  }

}
