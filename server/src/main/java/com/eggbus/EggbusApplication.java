package com.eggbus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.eggbus.mapper")
@SpringBootApplication
public class EggbusApplication {
  public static void main(String[] args) {
    SpringApplication.run(EggbusApplication.class, args);
  }
}

