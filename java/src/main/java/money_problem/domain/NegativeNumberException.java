package money_problem.domain;

public class NegativeNumberException extends Exception {
    public NegativeNumberException() {
        super("Number negatif");
    }
}
