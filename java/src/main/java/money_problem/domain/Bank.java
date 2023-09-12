package money_problem.domain;

import java.util.HashMap;
import java.util.Map;

public final class Bank {
    

    private final Map<String, Double> exchangeRates;

    private Bank(Map<String, Double> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }

    public static Bank createBank(Currency currency1, Currency currency2, double rate) {
        var bank = new Bank(new HashMap<>());
        bank.addExchangeRate(currency1, currency2, rate);
        return bank;
    }

    public void addExchangeRate(Currency currency1, Currency currency2, double rate) {
        exchangeRates.put(this.createStringForExchange(currency1, currency2), rate);
    }

    public double convert(double amount, Currency currency1, Currency currency2) throws MissingExchangeRateException {
        if (this.canConvert(currency1, currency2)) {
            throw new MissingExchangeRateException(currency1, currency2);
        }
        return currency1 == currency2
                ? amount
                : amount * exchangeRates.get(this.createStringForExchange(currency1, currency2));
    }

    private boolean canConvert(Currency currency1, Currency currency2) {
       return !(currency1 == currency2 || exchangeRates.containsKey(this.createStringForExchange(currency1, currency2)));
    }

    private String createStringForExchange(Currency currency1, Currency currency2) {
        return currency1 + "->" + currency2;
    }

}