package money_problem.domain;

public class ExchangeRate {

    Currency currency;
    Double rate;
    String stringForExchange;

    public ExchangeRate(Currency currency, Double rate, String stringForExchange) {
        this.currency = currency;
        this.rate = rate;
        this.stringForExchange = stringForExchange;
    }
}
