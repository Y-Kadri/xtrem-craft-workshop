package money_problem.domain;

public class InvalidNumberException extends Exception {
    public InvalidNumberException(String mess) {
        super(mess);
    }
}
