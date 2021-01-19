package fr.training.spring.library.infrastructure;

import fr.training.spring.library.domain.Livre;
import fr.training.spring.library.domain.LivreRepository;
import fr.training.spring.library.domain.exception.LivreNotFoundException;
import fr.training.spring.library.infrastructure.entity.LivreEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LivreRepositoryImpl implements LivreRepository {
    @Autowired
    LivreDAO livreDAO;

    @Override
    public Livre findById(long idDeLivre) {
        LivreEntity livreEntity=livreDAO.findById(idDeLivre).orElseThrow(()->new LivreNotFoundException("Aucun livre n'existe pour cet identifiant" + idDeLivre,idDeLivre));

        return livreEntity.toLivre();
    }
}
