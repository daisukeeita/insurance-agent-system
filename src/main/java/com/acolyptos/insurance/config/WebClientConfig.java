package com.acolyptos.insurance.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/** Spring Web Client configuration for calling a third-party API asynchronously. */
@Configuration
public class WebClientConfig {

  @Value("${api.thirdparty.uri}")
  private String baseUrl;

  @Value("${api.thirdparty.connect-timeout}")
  private int connectionTimeountMillis;

  @Value("${api.thirdparty.read-timeount-seconds}")
  private int readTimeountSeconds;

  /**
   * Builds a {@code WebClient} connection to the third-part API.
   *
   * @return A {@link WebClient} once the connection is built.
   */
  @Bean
  public WebClient thirdPartyWebClient() {
    return WebClient.builder().baseUrl(baseUrl).build();
  }
}
