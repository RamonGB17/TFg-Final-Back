package org.example.triviadexbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TriviadexBackendApplication {

    static {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("✅ PostgreSQL driver cargado correctamente.");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ NO se encontró el driver de PostgreSQL.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(TriviadexBackendApplication.class, args);
    }
}
