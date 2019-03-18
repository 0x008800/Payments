package com.oxoo88oo.doProvodki;

import com.oxoo88oo.doProvodki.service.DoProvodki;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class Main {
  public static void main(String[] args) {
  //  SpringApplication.run(Main.class);
    new DoProvodki().doProvodki();
  }
}
