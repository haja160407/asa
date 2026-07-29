package com.school.luc.endpoint;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.school.luc.number.NumberConverter;

@Configuration
public class ConverterConf {
  @Bean
  public NumberConverter numberConverter() {
    return new NumberConverter();
  }
}
