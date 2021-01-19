package fr.training.spring.library.domain;

import org.springframework.stereotype.Repository;

@Repository
public interface LivreRepository {

    Livre findById(long idDeLivre);
}
