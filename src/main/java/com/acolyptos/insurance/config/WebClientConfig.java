package com.acolyptos.insurance.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

/** Spring Web Client configuration for calling a third-party API asynchronously. */
@Configuration
public class WebClientConfig {

  @Value("${api.thirdparty.uri}")
  private String baseUrl;

  @Value("${api.thirdparty.connection-timeout}")
  private int connectionTimeountMillis;

  @Value("${api.thirdparty.response-timeout-seconds}")
  private int responseTimeoutSeconds;

  /**
   * Builds a {@code WebClient} connection to the third-part API.
   *
   * @return A {@link WebClient} once the connection is built.
   */
  @Bean
  public WebClient insecureWebClient() {
    SslContext sslContext;

    try {
      sslContext =
          SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
    } catch (Exception e) {
      throw new IllegalStateException("Failed to create insecure SSL context.", e);
    }

    HttpClient httpClient =
        HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectionTimeountMillis)
            .responseTimeout(Duration.ofSeconds(responseTimeoutSeconds))
            .secure(sslContextSpec -> sslContextSpec.sslContext(sslContext));

    return WebClient.builder()
        .clientConnector(new ReactorClientHttpConnector(httpClient))
        .baseUrl(baseUrl)
        .build();
  }
}
