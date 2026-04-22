package br.ufrn.imd.repasse_v2;

import br.ufrn.imd.repasse_v2.model.Loja;
import br.ufrn.imd.repasse_v2.repository.LojaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RepasseV2Application {

    public static void main(String[] args) {
        SpringApplication.run(RepasseV2Application.class, args);
    }

    // Esse bloco cria uma loja automaticamente toda vez que eu der "Run"
    @Bean
    public CommandLineRunner demo(LojaRepository repository) {
        return (args) -> {
            Loja lojaDefault = new Loja();
            lojaDefault.setNome("Sua Loja de Repasse Natal");
            repository.save(lojaDefault);
            System.out.println(">>> LOJA AUTOMÁTICA CRIADA COM ID 1 <<<");
        };
    }
}