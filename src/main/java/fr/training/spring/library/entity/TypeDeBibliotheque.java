package fr.training.spring.library.entity;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


public enum TypeDeBibliotheque {

    ASSOCIATIVE("Associative"),
    NATIONALE("Nationale"),
    PUBLIQUE("Publique"),
    SCOLAIRE("Scolaire"),
    UNIVERSITAIRE("Universitaire");

    TypeDeBibliotheque(String type) {
    }
}
