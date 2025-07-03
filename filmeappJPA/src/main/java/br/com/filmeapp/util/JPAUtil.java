package br.com.filmeapp.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    ///private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("filmePU");

    private static final EntityManagerFactory emf;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("filmePU");
            System.out.println("✅ persistence.xml carregado com sucesso!");
        } catch (Exception e) {
            // Ajuda a entender o erro real!
            e.printStackTrace();
            throw new ExceptionInInitializerError("Erro ao criar EntityManagerFactory: " + e.getMessage());
        }
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
