package com.acolyptos.insurance.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** DOT-ENV configuration. */
@Configuration
public class DotenvConfig {

  /** Loads the dotenv variables. */
  @Bean
  public Dotenv dotenv() {
    return Dotenv.configure().ignoreIfMissing().load();
  }
}
