package money_problem.domain;

public class MissingExchangeRateException extends Exception {
    public MissingExchangeRateException(Currency currency1, Currency currency2) {
        super(String.format("You can't change %s->%s", currency1, currency2));
    }
}
