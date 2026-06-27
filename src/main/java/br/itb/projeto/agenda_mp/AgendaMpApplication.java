package br.itb.projeto.agenda_mp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AgendaMpApplication {

    public static void main(String[] args) {

        System.out.println("========== APP INICIANDO ==========");

        SpringApplication.run(AgendaMpApplication.class, args);
    }
}