package fr.training.spring.library.domain.exception;

public class LivreNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String msg = "";

    public LivreNotFoundException(String msg, long idDeLivre) {
        super(msg);
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}