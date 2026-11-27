package com.choigames.app.config;

import java.time.Clock;
import java.time.ZoneId;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClockConfig {

  /**
   * タイムゾーンの定義
   * 
   */
  @Bean
  public Clock clock() {
    return Clock.system(ZoneId.of("Asia/Tokyo"));
  }

}
