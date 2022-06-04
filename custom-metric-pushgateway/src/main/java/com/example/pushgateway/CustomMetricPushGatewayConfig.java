package com.example.pushgateway;

import io.prometheus.client.exporter.BasicAuthHttpConnectionFactory;
import io.prometheus.client.exporter.PushGateway;
import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomMetricPushGatewayConfig {

  @Bean
  public PushGateway pushGateway(@Value("${url}") String url, @Value("${user}") String userName, @Value("${pass}") String password) throws MalformedURLException {
    PushGateway pushGateway = new PushGateway(new URL(url));
    pushGateway.setConnectionFactory(new BasicAuthHttpConnectionFactory(userName, password));
    return pushGateway;
  }
}
