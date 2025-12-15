package com.acolyptos.insurance.config;

import javax.net.ssl.SSLContext;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.ClientTlsStrategyBuilder;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.TrustAllStrategy;
import org.apache.hc.core5.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** Spring Web Client configuration for calling a third-party API asynchronously. */
@Configuration
public class RestClientConfig {

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
  public RestClient insecureRestClient() throws Exception {

    // Create a "Trust All" SSL context.
    // Bypass trust store checks
    final SSLContext sslContext =
        SSLContexts.custom().loadTrustMaterial(new TrustAllStrategy()).build();

    // Configure SSL Factory to use the "Trust All" context.
    // Use NoopHostnameVerifier to bypass the hostname checks.
    final var tlsStrategy =
        ClientTlsStrategyBuilder.create()
            .setSslContext(sslContext)
            .setHostnameVerifier(NoopHostnameVerifier.INSTANCE)
            .buildClassic();

    final var connectionManager =
        PoolingHttpClientConnectionManagerBuilder.create()
            .setTlsSocketStrategy(tlsStrategy)
            .build();

    final CloseableHttpClient httpClient =
        HttpClients.custom().setConnectionManager(connectionManager).build();

    final ClientHttpRequestFactory requestFactory =
        new HttpComponentsClientHttpRequestFactory(httpClient);

    return RestClient.builder()
        .baseUrl(baseUrl)
        .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
        .requestFactory(requestFactory)
        .build();
  }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(final CorsRegistry registry) {
        registry
            .addMapping("/**")
            .allowedOrigins("*") // allow all origins
            .allowedMethods("*") // allow all HTTP methods
            .allowedHeaders("*"); // allow all headers
      }
    };
  }
}
