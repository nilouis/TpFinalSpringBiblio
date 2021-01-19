package fr.training.spring.library.infrastructure;

import fr.training.spring.library.domain.Livre;
import fr.training.spring.library.infrastructure.entity.LivreEntity;
import org.springframework.data.repository.CrudRepository;

public interface LivreDAO extends CrudRepository<LivreEntity,Long>{

}
