package com.example.pushgateway;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.PushGateway;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomMetricPushGateway implements CommandLineRunner {
  private static final CollectorRegistry registry = new CollectorRegistry();

  @Autowired
  PushGateway pushgateway;

  public static void main(String[] args) {
    SpringApplication.run(CustomMetricPushGateway.class, args);
  }

  @Override
  public void run(String... args) throws IOException {
    Gauge guage = Gauge.build("my_custom_metric", "This is my custom metric.")
        .labelNames("env").create().register(registry);
    guage.labels("dev").set(23.12);

    pushgateway.pushAdd(registry, "my_job");
  }
}