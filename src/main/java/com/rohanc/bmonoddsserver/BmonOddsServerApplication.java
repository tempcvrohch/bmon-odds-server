/* (C)2024 */
package com.rohanc.bmonoddsserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication()
@EnableJpaAuditing
public class BmonOddsServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(BmonOddsServerApplication.class, args);
  }
}
