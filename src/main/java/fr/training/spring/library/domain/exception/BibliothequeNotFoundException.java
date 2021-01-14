package fr.training.spring.library.domain.exception;

public class BibliothequeNotFoundException extends RuntimeException{


    private static final long serialVersionUID = 1L;

    public BibliothequeNotFoundException(String msg) {
        super(msg);
    }
}