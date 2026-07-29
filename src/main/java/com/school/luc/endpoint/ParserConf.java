package com.school.luc.endpoint;

import com.school.luc.number.NumberParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParserConf {
  @Bean
  public NumberParser numberParser() {
    return new NumberParser();
  }
}
