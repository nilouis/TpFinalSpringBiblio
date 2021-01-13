package fr.training.spring.library.infrastructure;

import fr.training.spring.library.entity.Bibliotheque;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract class BibliothequeDAO implements CrudRepository {

}
