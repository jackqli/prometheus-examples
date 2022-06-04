package com.example.custom.metric;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyCounterHolder {
  private MeterRegistry meterRegistry;

  public Counter getCounter() {
    return counter;
  }

  public void setCounter(Counter counter) {
    this.counter = counter;
  }

  private Counter counter;

  @Autowired
  public MyCounterHolder(MeterRegistry meterRegistry) {
    this.meterRegistry = meterRegistry;
    this.counter = Counter
        .builder("error.send.message")
        .tag("env", "dev")
        .description("The number of errors sending message")
        .register(meterRegistry);
  }
}
