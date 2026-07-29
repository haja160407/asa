package com.school.luc.endpoint;

import com.school.luc.number.NumberConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConverterConf {
  @Bean
  public NumberConverter numberConverter() {
    return new NumberConverter();
  }
}
