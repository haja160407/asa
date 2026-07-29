package com.school.luc.endpoint;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.school.luc.number.NumberParser;

@Configuration
public class ParserConf {
  @Bean
  public NumberParser numberParser() {
    return new NumberParser();
  }
}
