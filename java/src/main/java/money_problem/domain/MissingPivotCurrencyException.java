package money_problem.domain;

public class MissingPivotCurrencyException extends RuntimeException {

    public MissingPivotCurrencyException() {
        super("Pivot currency is required");
    }
}