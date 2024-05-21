/**
 * A Spring Boot alkalmazás beleprsi pontja,
 * amely inicializalja és indítja el az alkalmazást.
 */
package com.konyvesbolt.konyvek_beadando;
import com.konyvesbolt.konyvek_beadando.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class KonyvekBeadandoApplication {
 @Autowired
 private BookRepository bookRepository;
 /**
  * Entry point of the application.
  *
  * @param args Command line arguments.
  */
 public static void main(final String[] args) {
  SpringApplication.run(KonyvekBeadandoApplication.class, args);
 }
 /**
  * A command line runner bean.
  * @return The CommandLineRunner instance.
  * A parancssori futtato interfesz,
  * amely inicializalja és indítja el az alkalmazast.
  */
 @Bean
 public CommandLineRunner commandLineRunner() {
  return args -> {
   System.out.println("A program elindult.");
  };
 }
}
